import java.io.IOException;
import java.util.Iterator;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * receive the input data from form
 */
public class StoreProductServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    private XmlSaveToHashmap xMLHashmapProduct = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");//for read

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        //String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");

        if(null != usertype && !(usertype.equalsIgnoreCase("Manager"))) return;

        String type = request.getParameter("type");
        if(null == type || type.equalsIgnoreCase("Select") || type.equalsIgnoreCase("Edit")){      //edit can use add function
            type = "Add";
        }
        
        String productid         = request.getParameter("productid");
        String productimage      = request.getParameter("productimage");
        String productname       = request.getParameter("productname");
        String productcolor      = request.getParameter("productcolor");
        String productprice      = request.getParameter("productprice");
        String productaccessory1 = request.getParameter("productaccessory1");
        String productaccessory2 = request.getParameter("productaccessory2");
        String productaccessory3 = request.getParameter("productaccessory3");
        String productaccessory4 = request.getParameter("productaccessory4");
        String productaccessory5 = request.getParameter("productaccessory5");
        String productaccessory6 = request.getParameter("productaccessory6");
        String productaccessory7 = request.getParameter("productaccessory7");

        if(productid != null && productid.length() != 0) {
            productid = productid.trim();
        }
        if(productimage != null && productimage.length() != 0) {
            productimage = productimage.trim();
        }
        if(productname != null && productname.length() != 0) {
            productname = productname.trim();
        }
        if(productcolor != null && productcolor.length() != 0) {
            productcolor = productcolor.trim();
        }
        if(productprice != null && productprice.length() != 0) {
            productprice = productprice.trim();
        }
        if(productaccessory1 != null && productaccessory1.length() != 0) {
            productaccessory1 = productaccessory1.trim();
        }
        if(productaccessory2 != null && productaccessory2.length() != 0) {
            productaccessory2 = productaccessory2.trim();
        }
        if(productaccessory3 != null && productaccessory3.length() != 0) {
            productaccessory3 = productaccessory3.trim();
        }
        if(productaccessory4 != null && productaccessory4.length() != 0) {
            productaccessory4 = productaccessory4.trim();
        }
        if(productaccessory5 != null && productaccessory5.length() != 0) {
            productaccessory5 = productaccessory5.trim();
        }
        if(productaccessory6 != null && productaccessory6.length() != 0) {
            productaccessory6 = productaccessory6.trim();
        }
        if(productaccessory7 != null && productaccessory7.length() != 0) {
            productaccessory7 = productaccessory7.trim();
        }
        


        if(type.equalsIgnoreCase("Add")){
            if(productid != null && productimage != null && productname != null && 
                productcolor != null && productprice != null && productaccessory1 != null) {

                HashMap<String,Item> currentHashMap = xMLHashmapProduct.itemHashmap;
                HashMap<String,Accessory> accHashmap = xMLHashmapProduct.accHashmap;

                List<String> tmpList = new ArrayList<String>();
                if(productaccessory1 != null) tmpList.add(productaccessory1);
                if(productaccessory2 != null) tmpList.add(productaccessory2);
                if(productaccessory3 != null) tmpList.add(productaccessory3);
                if(productaccessory4 != null) tmpList.add(productaccessory4);
                if(productaccessory5 != null) tmpList.add(productaccessory5);
                if(productaccessory6 != null) tmpList.add(productaccessory6);
                if(productaccessory7 != null) tmpList.add(productaccessory7);

                Item item = new Item(productname,productid,productimage,productcolor,Integer.parseInt(productprice), tmpList);

                currentHashMap.put(productid,item);

                new XmlSaveToHashmap(currentHashMap, accHashmap);//save
                //success
                response.sendRedirect("/HW1/ManagerFunctionServlet");
            } else {
                showPage(response, "update Failure!  You must supply all the fields");
            }
        }
        else if(type.equalsIgnoreCase("Remove")){
            if(productid != null){
                HashMap<String,Item> currentHashMap = xMLHashmapProduct.itemHashmap;

                currentHashMap.remove(productid);

                new XmlSaveToHashmap(currentHashMap,xMLHashmapProduct.accHashmap);//save
                //success
                response.sendRedirect("/HW1/ManagerFunctionServlet");
            }else{
                showPage(response, "remove Failure!  You must supply a product id");
            }
        }
    }

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Create Account Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}