import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class WriteReviewServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
        Item item = new Item();

    	PrintWriter out = response.getWriter();

    	Integer itemNum = (Integer) session.getAttribute("itemNum");
	    if(null == itemNum) {
	        itemNum = 0;
	        session.setAttribute("itemNum" , itemNum);
		}
        String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");

        if(null != username && !(usertype.equalsIgnoreCase("Manager"))) return;

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

        out.println("<center>");
        out.println("	<form method=\"post\" action=\"/HW1/MongoDBDataStoreUtilities\">");  
        out.println("		<h4>Set user ID and password to create account</h4>");
        out.println("			<table cellpadding=\'2\' cellspacing=\"1\">");
        out.println("				<tr>");
        out.println("					<td>Product Name</td>");
        out.println("					<td><input type=\"TEXT\" size=\"15\" name=\"productname\"></input></td>");
        out.println("				</tr>");

        out.println("				<tr>");
        out.println("					<td>Product Category</td>");
        out.println("					<td><input type=\"TEXT\" size=\"15\" name=\"productcategory\"></td>");
        out.println("				</tr>");

        out.println("               <tr>");
        out.println("                   <td>Product Price</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productprice\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>"); 
        out.println("                   <td>Retailer Name</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"retailername\" value=\"SmartPortables\" readonly=\"readonly\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Retailer Zipcode</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"retialerzipcode\" value=\"60616\" readonly=\"readonly\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Retailer City</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"retailercity\" value=\"Chicago\" readonly=\"readonly\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Retailer State</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"retailerstate\" value=\"Illinois\" readonly=\"readonly\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Product On-Sale</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productonsale\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Manufacturer Name</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"manufacturername\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Manufacturer Rebate</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"manufacturerrebate\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>User Name</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"username\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>User Age</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"userage\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>User Gender (Male/Female)</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"usergender\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>User Occupation</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"useroccupation\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Review Rate (1-5)</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"reviewrate\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Review Date (yyyymmdd)</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"reviewdate\"></input></td>");
        out.println("               </tr>");

        out.println("               <tr>");
        out.println("                   <td>Review Text</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"reviewtext\"></input></td>");
        out.println("               </tr>");
        
        out.println("               <tr>");
        out.println("                   <td colspan='2'>");
        out.println("                       <center><input type=\"submit\" value=\"Submit Review\"></center>");
        out.println("                   </td>");
        out.println("               </tr>");
        out.println("			</table>");
        out.println("	</form>");
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



