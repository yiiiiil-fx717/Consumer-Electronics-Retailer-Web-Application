/*
 * LoginServlet.java
 *
 */
 

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogintoServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
   
    //private XMLHashmapUser xMLHashmapUser = new XMLHashmapUser("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\User.xml");
    //HashMap<String,User> currentHashMap = xMLHashmapUser.usersHashMap;

    //public void init() {
        //xMLHashmapUser = new XMLHashmapUser("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\User.xml");
        //users.put("test", "TEST");
    //}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        XMLHashmapUser xMLHashmapUser = new XMLHashmapUser("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\User.xml");

        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(null == cart) {
            cart = new ShoppingCart();
            session.setAttribute("cart" , cart);
        }
        session.setAttribute("username" , null);

        //==================================================================================================
        response.setCharacterEncoding("GBK");
        //get username and password
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        
        UserVerify vf = new UserVerify();

        User user = vf.login(userid,password);
        
        if(user != null && password != null){
            session.setAttribute("username" , userid);
            session.setAttribute("usertype" , xMLHashmapUser.getUser(userid).getType());
            response.sendRedirect("/HW1/index");
        }else{
            response.sendRedirect("/HW1/LoginFailPage");
            //showPage(response, "Login Failure! \n Password incorrect or User doesn't exist");
            //response.setContentType("text/html;charset=UTF-8");
            //response.setHeader("refresh", "3;url = /HW1/index");
            }

        }
        //==================================================================================================


/*
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(type =="Select" || type == null){
            type = "Customer";
        }

        if(userid != null && password != null) {
            if(xMLHashmapUser.getUser(userid) == null){
                showPage(response, "Login Failure! No this user");
            }else{
                String realpassword = xMLHashmapUser.getUser(userid).getPassword();
                if(realpassword != null &&
                    realpassword.equals(password)) {
                    session.setAttribute("username" , userid);
                    session.setAttribute("usertype" , xMLHashmapUser.getUser(userid).getType());

                    //===================================test
                    //cart.combineWith(cart);//test
                    int itemNum = cart.getTotalNum();
                    session.setAttribute("cart" , cart);
                    session.setAttribute("itemNum" , itemNum);
                    //=======================================


                    response.sendRedirect("/HW1/index");
                    //showPage(response, "Login Success!");

                } else {
                    showPage(response, "Login Failure! password is incorrect");
                }
            }
        } else {
            showPage(response, "Login Failure!  You must supply a username and password");
        }

        */
    


    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}