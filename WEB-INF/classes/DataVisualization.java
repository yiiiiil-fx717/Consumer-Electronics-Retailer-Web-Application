import java.io.*; 
import java.util.*;
import java.lang.String.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

import java.sql.*;
import javax.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataVisualization extends HttpServlet {
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

        String jsHeader =  "google.charts.load('current', {packages:[\"corechart\"]});\n" +
            "        google.charts.setOnLoadCallback(drawChart);\n" +
            "        function drawChart() {\n" +
            "            var data = google.visualization.DataTable()\n" +
            "                data.addColumn('string', 'Product')\n" +
            "                data.addColumn('number', 'Stock')\n" +  
            "                data.addRows([" +
            "                   ['iphone', 10]" +
            "                 ]);" +

            "            var options = {'title':\"chart\",\n" +
            "                width:600,\n" +
            "                height:600};\n" +

            "            var chart = new google.visualization.BarChart(document.getElementById('chart_div'));\n" +
            "            chart.draw(data, options);\n" +
            "        }\n";

        
        

        PrintWriter out = response.getWriter();
        out.println("<!doctype html>");  //
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<title>SmartPortables</title>");
        out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");

        out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");   //
        out.println("<script type=\"text/javascript\">"+ jsHeader +"</script>");   
        /*out.println("<div id=\"barchart_values\" style=\"width: 900px; height: 300px;\"></div>");
        List<product> lp = null;
        StringBuffer dataTrs = new StringBuffer();
        for (product data : lp) {
            dataTrs.append("['" + data.getName() + "', " + data.getStock() + "],");
            jsHeader.replace("#product#", data.getName());
            jsHeader.replace("#stock#", String.valueOf(data.getStock()));
        }
        
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?useUnicode=true&characterEncoding=utf-8&useSSL=false" ,"root","root");
        }catch(Exception e){
            System.out.println("Fail connecting to database server...");
            e.printStackTrace();
        }

        HashMap<Integer, product> hsmp = new HashMap<Integer, product>();
        Statement st = null;
        ResultSet rs = null;
        product p;
        try{
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM product");   //Select all column from table "product"
            while(rs.next()){
                int id      = rs.getInt("id");
                String name = rs.getString("name");
                int price   = rs.getInt("Price");
                int stock   = rs.getInt("stock");
                String rebate  = rs.getString("rebate");
                p = new product(id,name,price,stock,rebate);
                hsmp.put(id,p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        for(int i: hsmp.keySet()){
            product pr = hsmp.get(i);
                jsHeader.replace("#product#", pr.getName());
                jsHeader.replace("#stock#", String.valueOf(pr.getStock()));
        }
*/
        out.println("</head>");

        //page body
        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("    <header>");
        out.println("       <h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
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

        //Main content area
        out.println("    <div id=\"body\">");
        out.println("       <section id=\"content\">");
        
        
        out.println("<div id=\"chart_div\" style=\"width: 900px; height: 300px;\"></div>");








        
        out.println("        </section>");

        //left navigation bar        
        {
            out.println("    <aside class=\"sidebar\">");
            out.println("        <ul>");
            out.println("           <li>");
            out.println("                <h4>Inventory Report</h4>");
            out.println("                <ul>");
            out.println("                    <li><a href=\"AllProductTableServlet\">All Product</a></li>");
            out.println("                    <li><a href=\"###\">Bar Chart</a></li>");
            out.println("                    <li><a href=\"OnSaleProductServlet\">On-Sale Product</a></li>");
            out.println("                    <li><a href=\"ProductWithRebate\">Products with Rebate</a></li>");
            out.println("                </ul>");
            out.println("            </li>");
            out.println("        </ul>");    
            out.println("    </aside>");
        }

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