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
public class RemoveProductServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    private XmlSaveToHashmap xMLHashmapProduct = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");//for read

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        //String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(null == cart) showPage(response, "cart is empty");
        ShoppingCartAcc cartAcc = (ShoppingCartAcc) session.getAttribute("cartAcc");
        if(null == cartAcc) showPage(response, "cartAcc is empty");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        
        if(null != id){
            cart.removeItem(xMLHashmapProduct.getItem(id));
            session.setAttribute("cart" , cart);
        }else if(null != name){
            cartAcc.removeAccessory(xMLHashmapProduct.getAccessory(name));
            session.setAttribute("cartAcc" , cartAcc);
        }

        response.sendRedirect("/HW1/CartServlet");
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