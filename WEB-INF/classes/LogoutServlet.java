import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        XMLHashmapUser xMLHashmapUser = new XMLHashmapUser("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\User.xml");

        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(null == cart) {
            cart = new ShoppingCart();
            session.setAttribute("cart" , cart);
        }
        ShoppingCartAcc cartAcc = (ShoppingCartAcc) session.getAttribute("cartAcc");
        if(null == cartAcc) {
            cartAcc = new ShoppingCartAcc();
            session.setAttribute("cartAcc" , cartAcc);
        }
        session.setAttribute("username" , null);
        session.setAttribute("usertype" , null);

        //save this user's cart

        //end session
        {
        session.setAttribute("username", null);
		session.setAttribute("cart" , null);
        session.setAttribute("cartAcc" , null);
		session.setAttribute("itemNum" , 0);
       	response.sendRedirect("/HW1/index");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
