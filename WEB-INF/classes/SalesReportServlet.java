import java.io.*; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesReportServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
	    Integer itemNum = (Integer) session.getAttribute("itemNum");
	    if(null == itemNum) {
	        itemNum = 0;
	        session.setAttribute("itemNum" , itemNum);
		}
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
            out.println("        <li class=\"start selected\"><a href=\"index\"><span class=\"glyphicon glyphicon-home\">Home</span></a></li>");
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

        
		//out.println("	<img class=\"header-image\" src=\"images/image1.png\" alt=\"Buildings\" />");

		out.println("    <div id=\"body\">");
		out.println("		<section id=\"content\">");
		
		
        


		
		out.println("        </section>");

		//sidebar
        {
            out.println("    <aside class=\"sidebar\">");
            out.println("        <ul>");
            out.println("           <li>");
            out.println("                <h4>Inventory Report</h4>");
            out.println("                <ul>");
            out.println("                    <li><a href=\"SoldProductTableServlet\">Sold Product</a></li>");
            out.println("                    <li><a href=\"###\">Bar Chart</a></li>");
            out.println("                    <li><a href=\"DailyTransactionTableServlet\">Daily Sales</a></li>");
            out.println("                </ul>");
            out.println("            </li>");

            

            out.println("        </ul>");    
            out.println("    </aside>");
        }

		out.println("    <div class=\"clear\"></div>");
		out.println("    </div>");


		out.println("    <footer>");
		out.println("        <div class=\"footer-content\">");
		out.println("        	<h4>My SmartPortables</h4>");
		out.println("            <ul>");
		if(null == username){
            out.println("            <li><a href=\"LoginServlet\">Login</a></li>");
            out.println("            <li><a href=\"CreateAccountServlet\">Create account</a></li>");
        }else{
            out.println("            <li><a href=\"ViewOrderServlet\">View order</a></li>");
            if(usertype.equalsIgnoreCase("Salesman")){
                out.println("        <li><a href=\"CreateAccountServlet\">Create Customer Account</a></li>");
                //out.println("        <li><a href=\"#\">Manage orders</a></li>");
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

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}