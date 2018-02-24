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

public class DealMatchesUtilities extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		/*
		try{
			out.print("<div id='content'>");
			out.print("<div class='post'>");
			out.print("<h2 class='title'>");
			out.print("<a href='#'>Welcome to GameSpeed </a></h2>");
			out.print("<div class='entry'>");
			out.print("<br> <br>");
			out.print("<h2>The world trusts us to deliver SPEEDY service for video-gaming fans</h2>"); 
			out.print("<br> <br>");
			out.print("<h1>We beat our competitors in all aspects. Price-Match Guaranteed</h2>"); 
			
		}catch(Exception e){

		}
		*/

		HashMap<String,Integer> selectedproducts=new HashMap<String,Integer>();
		HashMap<String,Integer> productmap = MySQLDataStoreUtilities.getData();

		String line = null;
		for(Map.Entry<String, Integer> entry : productmap.entrySet()){
			if(selectedproducts.size() < 2 && !selectedproducts.containsKey(entry.getKey())){
				FileReader fr = new FileReader("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\DealMatches.txt");
				BufferedReader reader = new BufferedReader(fr);

				line = reader.readLine(); 
				if(line == null){ 
					out.print("<h2 align='center'>No Offers Found</h2>");
					break;
				}else{ 
					do{
				    	if(line.contains(entry.getKey())) {
							out.print("<h2>"+line+"</h2>");
							out.print("<br>"); 
							selectedproducts.put(entry.getKey(),entry.getValue()); 
							break;
						}
					}while((line = reader.readLine()) != null);
				}
			}
		}
	}
}