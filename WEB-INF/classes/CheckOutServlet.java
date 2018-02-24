import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckOutServlet extends HttpServlet {
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

            out.println("<center>");
            out.println("<form action=\"ConfirmOrderServlet\">");
            out.println("Name: <br>");
            out.println("<input type=\"text\" name=\"name\">");
            out.println("<br>");
            out.println("Address: <br>");
            out.println("<input type=\"text\" name=\"address\">");
            out.println("<br>");
            out.println("Credit card: <br>");
            out.println("<input type=\"text\" name=\"creditcard\">");
            out.println("<br>");
            out.println("<br>");
            out.println("<a href=\"ConfirmOrderServlet\"><input type=\"submit\" value=\"Submit\"></a>");
            out.println("</form>");
            out.println("</center>");

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



