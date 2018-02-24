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

public class ExternalStorageOpenProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        XmlSaveToHashmap externalstorageHashmap = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");

    	String id = request.getParameter("id");
        Item item = externalstorageHashmap.getItem(id);

        HttpSession session = request.getSession(true);
        Integer itemNum = (Integer) session.getAttribute("itemNum");
        if(null == itemNum) {
            itemNum = 0;
            session.setAttribute("itemNum" , itemNum);
        }else{
            //for test
            //itemNum = itemNum + 1;
            //session.setAttribute("itemNum" , itemNum);
        }

        PrintWriter out = response.getWriter();

        out.println("<html>");

        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
        out.println("<title> SmartPortables </title>");
        out.println("<link rel=\"stylesheet\" href=\"styles_product_list.css\" type=\"text/css\"/>");
        out.println("</head>");

        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("<header>");
        out.println("<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
        out.println("</header>");

     	//nav
        {
            out.println("<nav>");
            out.println("    <ul>");
            out.println("        <li><a href=\"index\">Home</a></li>");
            out.println("        <li class=\"start selected\"><a href=\"PhoneServlet\">Phones</a></li>");
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

    	//sidebar
        {
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
        }

    	out.println("	<section id=\"content\">");

        out.println("		<br />");
        out.println("		item #" + item.id +":");
        out.println("		<br />");
        out.println("		image: " + item.image);
        out.println("		<br />");
        out.println("		name:  " + item.name);
        out.println("		<br />");
        out.println("		color: " + item.color);
        out.println("		<br />");
        out.println("		price: " + item.price);
        out.println("		<br />");
        for (String accessory: item.accessories) {
            out.println("		accessory: " + accessory);
        	out.println("		<br />");
        }
    	out.println("	</section>");
    	out.println("	<div class=\"clear\"></div>");

        out.println("</div>");
        
        out.println("</body>");
        out.println("</html>");
    }
}








