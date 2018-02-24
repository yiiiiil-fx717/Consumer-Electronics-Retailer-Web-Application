import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;

import java.sql.*;
import javax.sql.*;

public class ConfirmOrderServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);

    	Integer itemNum = (Integer) session.getAttribute("itemNum");
	    if(null == itemNum) {
	        itemNum = 0;
	        session.setAttribute("itemNum" , itemNum);
		}
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        ShoppingCartAcc cartAcc = (ShoppingCartAcc) session.getAttribute("cartAcc");

        String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");
        //String usertype = (String) session.getAttribute("usertype");

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String creditcard = request.getParameter("creditcard");

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR); 
        int month = c.get(Calendar.MONTH)+1; 
        int date = c.get(Calendar.DATE); 
        int hour = c.get(Calendar.HOUR_OF_DAY); 
        int minute = c.get(Calendar.MINUTE); 
        int second = c.get(Calendar.SECOND);
        Random rand = new Random();

        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currdate = format.format(d);    //the current date, currdate
     
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 15);        
        d = ca.getTime();
        String enddate = format.format(d);         //the date after increase

        Item item = null;
        Accessory accessory = null;

        ArrayList itemsOrdered = (ArrayList)cart.getItemsOrdered();
        ArrayList accsOrdered  = (ArrayList)cartAcc.getAccsOrdered();


        PrintWriter out = response.getWriter();

    	out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>SmartPortables</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		out.println("</head>");

		out.println("<body>");
		out.println("<div id=\"container\">");
		out.println("    <header>");
		out.println("    	<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
		out.println("    </header>");

		//nav
        {
            out.println("<nav>");
            out.println("    <ul>");
            out.println("        <li class=\"start selected\"><a href=\"index\">Home</a></li>");
            out.println("        <li><a href=\"PhoneServlet\">Phones</a></li>");
            out.println("        <li><a href=\"LaptopServlet\">Laptops</a></li>");
            out.println("        <li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
            out.println("        <li><a href=\"SpeakersServlet\">Speakers</a></li>");
            out.println("        <li><a href=\"HeadphonesServlet\">Headphones</a></li>");
            out.println("        <li><a href=\"ExternalStorageServlet\">External Storage</a></li>");
            out.println("        <li class=\"end\"><a href=\"CartServlet\">Cart(" + itemNum + ")</a></li>");
            out.println("    </ul>");
            out.println("</nav>");
        }

        out.println("    <aside class=\"sidebar\">");
            out.println("        <ul>");
            out.println("           <li>");
            out.println("                <h4>Categories</h4>");
            out.println("                <ul>");
            out.println("                    <li><a href=\"index.html\">Home Page</a></li>");
            out.println("                    <li><a href=\"PhoneServlet\">Phones</a></li>");
            out.println("                    <li><a href=\"LaptopServlet\">Laptops</a></li>");
            out.println("                    <li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
            out.println("                    <li><a href=\"SpeakersServlet\">Speakers</a></li>");
            out.println("                    <li><a href=\"HeadphonesServlet\">Headphones</a></li>");
            out.println("                    <li><a href=\"ExternalStorageServlet\">External storage</a></li>");
            out.println("                    <li><a href=\"AccessariesServlet\">Accessaries</a></li>");
            out.println("                </ul>");
            out.println("            </li>");
            out.println("            <li>");
            out.println("                <h4>Search</h4>");
            out.println("                <ul>");
            out.println("                    <li class=\"text\">");
            out.println("                        <form method=\"get\" class=\"searchform\" action=\"#\" >");
            out.println("                            <p>");
            out.println("                                <input type=\"text\" size=\"26\" value=\"\" name=\"s\" class=\"s\" />");
            out.println("                            </p>");
            out.println("                        </form>");    
            out.println("                    </li>");
            out.println("                </ul>");
            out.println("            </li>");
            out.println("        </ul>");    
            out.println("    </aside>");

            //==============================================================   
            //randomly created ordernum
            String orderNum = Integer.toString(year) + Integer.toString(month) + Integer.toString(date) + Integer.toString(rand.nextInt(100));
            out.println("<h3>The confirmation number of your order is: " + orderNum +" </h3>");
            out.println("<h3>The delievery date is: " + enddate);

            //write order information into orderinfo table
            int sales = 1;
            String orderDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(date);
            int totalprice = cart.getTotalValue() + cartAcc.getTotalValue();  
            MySQLDataStoreUtilities.insertOrder(orderNum, name, totalprice, address, creditcard, enddate, sales, orderDate);
        
        //ordered product
            for(int j=0; j<itemsOrdered.size(); j++) {
                ItemOrder orderitem = (ItemOrder)itemsOrdered.get(j);
                item = orderitem.getItem();
                {
                    MySQLDataStoreUtilities.insertOrderedItem(orderNum, item.id, item.name, item.price, cart.getItemNum(item));
                    MySQLDataStoreUtilities.updateStock(cart.getItemNum(item), item.id);
                }
            }

        //ordered accessary
            for(int i=0; i<accsOrdered.size(); i++) {
                AccessoryOrder order = (AccessoryOrder)accsOrdered.get(i);
                accessory = order.getAccessory();
                {
                    MySQLDataStoreUtilities.insertOrderedItem(orderNum, accessory.id, accessory.name, accessory.price, cartAcc.getAccessoryNum(accessory));
                    MySQLDataStoreUtilities.updateStock(cartAcc.getAccessoryNum(accessory), accessory.id);
                }
            }

            //store order information into class folder named by username
            String path = "C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\" + username;
            String filename = orderNum + ".xml";
            new AppendToFile(path, filename, cart,cartAcc,year,month,date,enddate,name,address,creditcard);

            //==============================================================

            {
                session.setAttribute("cart" , null);
                session.setAttribute("cartAcc" , null);
                session.setAttribute("itemNum" , 0);
            }//empty the cart

        out.println("    <div class=\"clear\"></div>");

        out.println("           <div class=\"clear\"></div>");
        
        out.println("    <footer>");
        out.println("        <div class=\"footer-content\">");
        out.println("           <h4>My SmartPortables</h4>");
        out.println("            <ul>");
        if(null == username){
            out.println("            <li><a href=\"LoginServlet\">Login</a></li>");
            out.println("            <li><a href=\"CreateAccountServlet\">Create account</a></li>");
        }else{
            out.println("            <li><a href=\"#\">View order</a></li>");
            if(usertype.equalsIgnoreCase("Salesman")){
                out.println("        <li><a href=\"CreateAccountServlet\">Create Customer Account</a></li>");
                out.println("        <li><a href=\"#\">Manage orders</a></li>");
            }
            if(usertype.equalsIgnoreCase("Manager")){
                out.println("        <li><a href=\"ManagerFunctionServlet\">Manage Products</a></li>");
            }
            out.println("            <li><a href=\"LogoutServlet\">Log out</a></li>");
        }
        out.println("            </ul>");
        out.println("            <div class=\"clear\"></div>");
        out.println("        </div>");       
        out.println("    </footer>");
        
        out.println("       </div>");
        out.println("   </div>");
    	
		
		out.println("			</body>");
		out.println("		</html>");
	
    }

}


