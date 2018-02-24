import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class ManagerFunctionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);

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
        out.println("	<form method=\"post\" action=\"/HW1/ManageProductDatabaseServlet\">");
        out.println("		<h4>Product Management</h4>");
        out.println("			<table cellpadding=\'2\' cellspacing=\"1\">");
        out.println("				<tr>");
        out.println("					<td>Product ID</td>");
        out.println("					<td><input type=\"TEXT\" size=\"15\" name=\"productid\"></input></td>");
        out.println("				</tr>");
        out.println("				<tr>");
        out.println("					<td>Image</td>");
        out.println("					<td><input type=\"TEXT\" size=\"15\" name=\"productimage\"></td>");
        out.println("				</tr>");
        out.println("               <tr>");
        out.println("                   <td>Name</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productname\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Color</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productcolor\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Price</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productprice\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Stock</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productstock\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Rebate</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productrebate\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 1</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory1\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 2</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory2\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 3</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory3\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 4</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory4\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 5</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory5\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 6</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory6\"></input></td>");
        out.println("               </tr>");
        out.println("               <tr>");
        out.println("                   <td>Accessory 7</td>");
        out.println("                   <td><input type=\"TEXT\" size=\"15\" name=\"productaccessory7\"></input></td>");
        out.println("               </tr>");
        

        out.println("               <tr>");
        out.println("                   <td colspan='2'>");
        out.println("                       <center>");
        out.println("                           <select name=\"type\">");
        out.println("                               <option value=\"Select\">Select manage type</option>");
        out.println("                               <option value=\"Add\">Add Product</option>");
        out.println("                               <option value=\"Remove\">Remove Product</option>");
        out.println("                               <option value=\"Edit\">Edit Product</option>");
        out.println("                           </select>");
        out.println("                       </center>");
        out.println("                   </td>");
        out.println("               </tr>");
        
        out.println("               <tr>");
        out.println("                   <td colspan='2'>");
        out.println("                       <center><input type=\"submit\" value=\"Update Product\"></center>");
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



