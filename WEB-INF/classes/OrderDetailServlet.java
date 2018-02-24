import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OrderDetailServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemOrder> itemsList;// = appendToFile.getItemsList();
        List<AccessoryOrder> accsList;// = appendToFile.getAccsList();

    	HttpSession session = request.getSession(true);

    	PrintWriter out = response.getWriter();

    	Integer itemNum = (Integer) session.getAttribute("itemNum");
	    if(null == itemNum) {
	        itemNum = 0;
	        session.setAttribute("itemNum" , itemNum);
		}
        String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");

        String usernameToManage = request.getParameter("name");
        String orderNumber = request.getParameter("number");
        String type = request.getParameter("type");

        if(usernameToManage == null || orderNumber==null) return;
        //if(usertype != "Salesman"){
        //   if(username != usernameToManage) return;
        //}

        String path = "C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\" + usernameToManage;
        String filename = orderNumber + ".xml";

        if(type.equalsIgnoreCase("Cancel")){
            File file = new File(path + "\\" + filename);
            if(file.exists()&&file.isFile()) file.delete();

            response.sendRedirect("/HW1/ViewOrderServlet");
        }

        

    	out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>SmartPortables</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles_product_list.css\" type=\"text/css\" />");
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
            out.println("        <li><a href=\"index\">Home</a></li>");
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

        out.println("<div id=\"body\">");
        //======================
        AppendToFile appendToFile = new AppendToFile(path, filename);
        itemsList = appendToFile.getItemsList();
        accsList = appendToFile.getAccsList();

        out.println("   <section class=\"cart-list\">");
        for(int i=0; i<itemsList.size(); i++) {
            ItemOrder order = (ItemOrder)itemsList.get(i);
            Item item = order.getItem();
            //if (order.getItem().getId().equals(item.getId()))
            {
                out.println("        <div class=\"product\">");
                out.println("            <ul>");
                out.println("                <li><img class=\"content-image\" src=\"images/" + item.image + ".png\" alt=" + item.name + "/></li>");
                out.println("                <li>");
                out.println("                    <ul>");
                out.println("                        <li><b>item #" + item.id + "</b></li>");
                out.println("                        <li><b>$ " + item.price + "</b></li>");
                out.println("                        <li>name:  " + item.name);
                out.println("                        <li>color: " + item.color);
                out.println("                        <li>number:" + order.getNumItems());
                out.println("                    </ul>");
                out.println("                </li>");
                out.println("            </ul>");
                out.println("        </div>");
              
            }
        }
        for(int i=0; i<accsList.size(); i++) {
            AccessoryOrder order = (AccessoryOrder)accsList.get(i);
            Accessory accessory = order.getAccessory();
            //if (order.getItem().getId().equals(item.getId()))
            {
                out.println("        <div class=\"product\">");
                out.println("            <ul>");
                out.println("                <li><img class=\"content-image\" src=\"images/" + accessory.image + ".png\" alt=" + accessory.name + "/></li>");
                out.println("                <li>");
                out.println("                    <ul>");
                out.println("                        <li><b>accessory #" + accessory.id + "</b></li>");
                out.println("                        <li><b>$ " + accessory.price + "</b></li>");
                out.println("                        <li>name:  " + accessory.name);
                out.println("                        <li>number:" + order.getNumAccessories());
                out.println("                    </ul>");
                out.println("                </li>");
                out.println("            </ul>");
                out.println("        </div>");
              
            }
        }
        out.println("</section>");
        //======================

        out.println("    <div class=\"clear\"></div>");

        out.println("    </div>");
        
        out.println("    <footer>");
        out.println("        <div class=\"footer-content\">");
        out.println("           <h4>My SmartPortables</h4>");
        out.println("            <ul>");

        if(null == username){
            out.println("            <li><a href=\"LoginServlet\">Login</a></li>");
            out.println("            <li><a href=\"CreateAccountServlet\">Create account</a></li>");
        }else{
            out.println("            <li><a href=\"ViewOrderServlet\">View order</a></li>");
            if(usertype.equalsIgnoreCase("Salesman")){
                out.println("        <li><a href=\"CreateAccountServlet\">Create Customer Account</a></li>");
                out.println("        <li><a href=\"#\">Manage orders</a></li>");
            }
            if(usertype.equalsIgnoreCase("Manager")){
                out.println("        <li><a href=\"ManagerFunctionServlet\">Manage Products</a></li>");
                out.println("        <li><a href=\"InventoryReportServlet\">Inventory Report</a></li>");
                out.println("        <li><a href=\"SalesReportServlet\">Sales Report</a></li>");
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