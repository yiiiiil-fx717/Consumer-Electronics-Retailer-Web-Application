import java.io.IOException;
import java.util.Iterator;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * receive the input data from form
 */
public class StoreUserServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    private XMLHashmapUser xMLHashmapUser = new XMLHashmapUser("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\User.xml");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        String usertype = (String) session.getAttribute("usertype");

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
            HashMap<String,User> currentHashMap = xMLHashmapUser.usersHashMap;

            //=================connect to mysql database and store user information into userinfodb=================
            MySQLDataStoreUtilities.getConnection();
            MySQLDataStoreUtilities.insertUser(userid, password, password, type);

            //=================================================================================================

            if(null != currentHashMap.get(userid)){
                if(null != usertype && usertype.equalsIgnoreCase("Salesman")){
                    //edit a user
                    User user = new User(userid,password,type);
                    currentHashMap.put(userid,user);
                    new XMLHashmapUser(currentHashMap);//save
                    //success
                    response.sendRedirect("CreateAccountServlet");
                }else{
                    showPage(response, "create Failure!  user exist");
                }
            }else{
                User user = new User(userid,password,type);
                currentHashMap.put(userid,user);
                new XMLHashmapUser(currentHashMap);//save
                //success
                if(usertype != null && usertype.equalsIgnoreCase("Salesman")){ //after add a customer account, stay on this page if Salesman want to add more
                    response.sendRedirect("CreateAccountServlet");
                }else{
                    response.sendRedirect("index");
                }   
            }

        } else {
            showPage(response, "create Failure!  You must supply a username and password");
        }
    }


    //==========================================================================
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