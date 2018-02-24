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

public class displayNav {
	displayNav(PrintWriter out){
		out.println("<nav>");
     	out.println("<ul>");
     	out.println("<li><a href=\"index\">Home</a></li>");
     	out.println("<li class=\"start selected\"><a href=\"PhoneServlet\">Phones</a></li>");
     	out.println("<li><a href=\"LaptopServlet\">Laptops</a></li>");
     	out.println("<li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
     	out.println("<li><a href=\"SpeakersServlet\">Speakers</a></li>");
     	out.println("<li><a href=\"HeadphonesServlet\">Headphones</a></li>");
     	out.println("<li><a href=\"ExternalStorageServlet\">External Storage</a></li>");
     	out.println("<li class=\"end\"><a href=\"CartServlet\">Cart</a></li>");
     	out.println("</ul>");
    	out.println("</nav>");

    	out.println("<div id=\"body\">");
    	out.println("	<aside class=\"sidebar\">");
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
        out.println("                </ul>");
        out.println("            </li>");
        out.println("            <li>");
        out.println("            	<h4>Search</h4>");
        out.println("                <ul>");
        out.println("                	<li class=\"text\">");
        out.println("                        <form method=\"get\" class=\"searchform\" action=\"#\" >");
        out.println("                            <p>");
        out.println("                                <input type=\"text\" size=\"31\" value=\"\" name=\"s\" class=\"s\" />");
        out.println("                            </p>");
        out.println("                        </form>");	
    	out.println("					</li>");
    	out.println("				</ul>");
        out.println("            </li>");
        out.println("        </ul>");	
        out.println("    </aside>");
	}
}