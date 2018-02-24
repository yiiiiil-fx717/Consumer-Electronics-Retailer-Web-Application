
import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import java.util.*;

//class defines all the methods related with mongodb
public class MongoDBDataStoreUtilities extends HttpServlet{         
 	private static final long serialVersionUID = 1L;
   
	//static DBCollection productReviews;    //?????????

	//=================================Connect to Mongodb server successfully=================================================
	public static void getConnection(){   //connecting success tested
		try{
			MongoClient mongoClient = new MongoClient("localhost", 21017);               ////connect to mongodb service
			MongoDatabase mongoDatabase = mongoClient.getDatabase("ProductReviews");     //insert database name here
			//System.out.println("Successfully connected to MongoDB...");

			//productReviews = db.getCollection("collection name");      //insert collection name here
		}catch(Exception e){
			System.err.println("MongoDB server is not up and running...");
		}
	}

	//============Collection "myReviews" created in "ProductReviews" database "ProductReviews" manually in cmd=================

	//method to get parameters passed from form filled by customer in WriteReviewServlet file
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException{
    	HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(null == cart) {
            cart = new ShoppingCart();
            session.setAttribute("cart" , cart);
        }
        session.setAttribute("username" , null);
        //getsession on this page above

        response.setCharacterEncoding("GBK");
        String productname            = request.getParameter("productname");
        String productcategory        = request.getParameter("productcategory");
        String productprice           = request.getParameter("productprice");
        //String retailername           = request.getParameter("retailername");
        //String retailerzipcode        = request.getParameter("retailerzipcode");
        //String retailercity           = request.getParameter("retailercity");
        //String retailerstate          = request.getParameter("retailerstate");
        String productonsale          = request.getParameter("productonsale");
        String manufacturername       = request.getParameter("manufacturername");
        String manufacturerrebate     = request.getParameter("manufacturerrebate");
        String username      		  = request.getParameter("username");
        String userage 				  = request.getParameter("userage");
        String usergender 			  = request.getParameter("usergender");
        String useroccupation 	      = request.getParameter("useroccupation");
        String reviewrate        	  = request.getParameter("reviewrate");
        String reviewdate             = request.getParameter("reviewdate");
        String reviewtext             = request.getParameter("reviewtext");

        response.sendRedirect("/HW1/SubmitReviewSuccessServlet");
        insertReview(productname,productcategory,productprice,productonsale,manufacturername,manufacturerrebate,username,userage,usergender,useroccupation,reviewrate,reviewdate,reviewtext);
    	
    }


	//=============================Store review from form to mongdodb=================================
	public static void insertReview(String productname,String productcategory, String productprice, 
						//String retailername, String retailerzipcode, String retailercity, String retailerstate, 
						String productonsale, String manufacturername, String manufacturerrebate, String username, String userage,
						String usergender, String useroccupation, String reviewrate,String reviewdate,String reviewtext){
		try{   
        	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );                   //Connect to service
         	MongoDatabase mongoDatabase = mongoClient.getDatabase("ProductReviews");            //Get database
         	MongoCollection<Document> collection = mongoDatabase.getCollection("myReviews");    //Get collection

			Document document = new Document("title",               "myReviews")
									 .append("productName",         productname)
									 .append("productCategory", 	productcategory)
									 .append("productPrice",        productprice)
									 .append("retailerName",        "SmartPortables")
									 .append("retailerZipcode",     "60616")
									 .append("retailerCity",        "Chicago")
									 .append("retailerState",       "Illinois")
									 .append("productOnSale",       productonsale)
									 .append("manufacturerName",    manufacturername)
									 .append("manufacturerRebate",  manufacturerrebate)
									 .append("userName",            username)
									 .append("userAge",             userage)
									 .append("userGender",          usergender)
									 .append("userOccupation",      useroccupation)
									 .append("reviewRate",          reviewrate)
									 .append("reviewDate",          reviewdate)
									 .append("reviewText",          reviewtext);

			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertOne(document);
		}catch(Exception e){
			System.out.println("Connection wrong...");
		}
	}


	//========================Read reviews from Mongodb===============================
	public static void readReviews(){
		try{
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			System.out.println("Connected to server...");
			DB db = mongoClient.getDB("test");
			System.out.println("Connected to database...");
			//MongoCollection<Document> collection = mongoDatabase.getCollection("test");\
			DBCollection call = db.getCollection("test");
			DBCursor cursor = call.find();
			System.out.println("Connected to collection...");

			while(cursor.hasNext()){
				int i = 1;
				System.out.println(cursor.next());
				i++;
			}
		
		}catch(Exception e){

		}
	}
/*
	
	//Read review from hashmap	
	public void storeReview(String productname,String username, String reviewrate,String reviewdate,String reviewtext){   
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}catch(Exception e){

		}

		if(!reviews.containsKey(productname)){
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr); 
		}

		ArrayList<Review> listReview = reviews.get(productname);
		Review review = new Review(productname,username,reviewrate,reviewdate,reviewtext); 
		listReview.add(review);
		
		try{
			MongoDBDataStoreUtilities.insertReview(productname,username,reviewrate,reviewdate,reviewtext);
		}catch(Exception e){
		
		}	
	}

	
	//Save review into hashmap
	public static HashMap<String, ArrayList<Review> selectReview(){
		getConnection();
		HashMap<String, ArrayList<Review>> reviewHashmap = new HashMap<String, ArrayList<Review>>();
		DBCursor cursor = myReviews.find();       //productReviews.find()
		
		while (cursor.hasNext()){
			BasicDBObject obj = (BasicDBObject) cursor.next();

			if(! reviewHashmap.containsKey(obj.getString("productName"))){
				ArrayList<Review> arr = new ArrayList<Review>();
				reviewHashmap.put(obj.getString("productName"), arr); 
			}
			ArrayList<Review> listReview = reviewHashmap.get(obj.getString("productName"));
			Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("reviewRate"),obj.getString("reviewDate"),obj.getString("reviewText"));
			listReview.add(review);
		}
		return reviewHashmap;
	}
	*/


	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}