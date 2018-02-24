import java.io.*; 

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;
import java.util.*;

import java.sql.*;
import javax.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoCompleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			StringBuffer sb = new StringBuffer();
			boolean namesAdded = false;
			String action = request.getParameter("action");
			String searchId = request.getParameter("searchId");
			if(action.equals("complete")){
				if(!searchId.equals("")){
					AjaxUtility a = new AjaxUtility(sb);
					sb = a.readData(searchId);
					if(sb != null || !sb.equals("")){
						namesAdded = true;
					}

					if(namesAdded){
						response.setContentType("text/xml");
						response.getWriter().write("<products>" + sb.toString() + "</products>");
					}
				}
			}
		}catch(Exception e){

		}
	}
}