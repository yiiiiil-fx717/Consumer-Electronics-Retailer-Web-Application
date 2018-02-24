import java.io.*; 
import java.util.*;

import java.sql.*;
import javax.sql.*;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class index extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		XmlSaveToHashmap productHashmap = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");

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

		out.println("<body>");   //Load init function when page load
		out.println("<script type=\"text/javascript\" src=\"AutoComplete.js\"></script>");   //include js file in html
		out.println("<div id=\"container\">");
		out.println("    <header>");
		out.println("    	<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
		out.println("    </header>");

		//nav              <i class="glyphicon glyphicon-home"></i>
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

		out.println("	<img class=\"header-image\" src=\"images/image1.png\" alt=\"Buildings\" />");
		out.println("    <div id=\"body\">");
		out.println("		<section id=\"content\">");
		out.println("		    <article>");
		out.println("				<h2>Introduction to SmartPortables</h2>");
		out.println("				<br>");
		out.println("				<p>Welcome to SmartPortables, our company located in Chicago. For more information, please check our website or contact our specialists. We hope you have fun.</p>");	
		out.println("			</article>");

//deal match function start
{	
		out.println("			<article>");
		out.println("				<h3 style=\"color:black\">Deal Matches</h3>");

		HashMap<String,Integer> selectedproducts = new HashMap<String,Integer>();
		HashMap<String,Integer> productmap = MySQLDataStoreUtilities.getData();
		String line = null;
		int[] dealNumber = new int[2];
		int j = 0;

		for(Map.Entry<String, Integer> entry : productmap.entrySet()){
			if(selectedproducts.size() < 2 && !selectedproducts.containsKey(entry.getKey())){
				FileReader fr = new FileReader("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\DealMatches.txt");
				BufferedReader reader = new BufferedReader(fr);
				line = reader.readLine(); 
				if(line == null){ 
					out.print("<h2 align=\"center\">No Offers Found</h2>");
					break;
				}else{ 
					do{
				    	if(line.contains(entry.getKey())) {
							out.print("<h2 style=\"font-size:17px\">" + line + "</h2>");
							out.print("<br>"); 
							selectedproducts.put(entry.getKey(), entry.getValue()); 
							dealNumber[j] = entry.getValue();
							j++;
						}
					}while((line = reader.readLine()) != null);
				}
			}
		}
		out.println("</article>");

		out.println("<h3 style=\"color:black\">Deal Matches</h3>");
		for(int i = 0; i < dealNumber.length; i++){
			Item item1 = productHashmap.getItem(String.valueOf(dealNumber[i]));
            out.println("        <div class=\"product\">");
            out.println("            <ul>");
            out.println("                <li><b>" + item1.name + "</b></li>");
            out.println("                <li><b>$ " + item1.price + "</b></li>");
            out.println("                <li><a href=\"ProductDetailServlet?id=" + i + "\"><img class=\"content-image\" src=\"images/" + item1.image + ".png\" alt=" + item1.name + "/></a></li>");
            out.println("                <li><a href=\"CartServlet?id=" + (String.valueOf(dealNumber[i])) + "\" class=\"button \">Buy Now</a></li>");
            out.println("                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
            out.println("                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
            out.println("            </ul>");
            out.println("        </div>");
		}

}//deal match function end
/*
		out.println("			<article class=\"expanded\">");
		out.println("	            <h2>Our Product Snippet</h2>");
		out.println("	            <br>");
		out.println("	            <div class=\"row\">");
		out.println("	 		   		<img class=\"content-image\" src=\"images/image2.png\" alt=\"Laptops\" />");
		out.println("	 		   		<img class=\"content-image\" src=\"images/image3.png\" alt=\"Watches\" />");
		out.println("	 		   		<img class=\"content-image\" src=\"images/image4.png\" alt=\"Phones\" />");
		out.println("				</div>");
		out.println("			</article>");
*/
		out.println("        </section>");
		        
		//sidebar
        {
            out.println("    <aside class=\"sidebar\">");
            out.println("        <ul>");
            out.println("           <li>");
            out.println("                <h4>Categories</h4>");
            out.println("                <ul>");
            out.println("                    <li><a href=\"index\">Home Page</a></li>");
            out.println("                    <li><a href=\"PhoneServlet\">Phones</a></li>");
            out.println("                    <li><a href=\"LaptopServlet\">Laptops</a></li>");
            out.println("                    <li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
            out.println("                    <li><a href=\"SpeakersServlet\">Speakers</a></li>");
            out.println("                    <li><a href=\"HeadphonesServlet\">Headphones</a></li>");
            out.println("                    <li><a href=\"ExternalStorageServlet\">External storage</a></li>");
            out.println("                    <li><a href=\"AccessariesServlet\">Accessaries</a></li>");
            out.println("                    <li><a href=\"TrendingServlet\">Trending</a></li>");
            out.println("                </ul>");
            out.println("            </li>");

            //Search bar****************************************************
            out.println("            <li>");
            out.println("                <h4>Search</h4>");
            out.println("                <ul>");
            out.println("                    <li class=\"text\">");
            out.println("                        <div name=\"autofillform\">");
            out.println("                                <input type=\"text\" name=\"searchId\" size=\"37\" value=\"\" class=\"input\" id=\"searchId\" placeholder=\"Search here...\"/>");
            out.println("							<div id=\"auto-row\">");
            out.println("								<table id=\"complete-table\" class=\"gridtable\" tyle=\"position: absolute; width: 315px;\"></table>");
            out.println("							</div>");
            out.println("						 </div>");  
            out.println("                    </li>");
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
                out.println("		 <li><a href=\"InventoryReportServlet\">Inventory Report</a></li>");
                out.println("		 <li><a href=\"SalesReportServlet\">Sales Report</a></li>");
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