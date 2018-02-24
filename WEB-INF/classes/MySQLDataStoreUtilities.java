import java.sql.*;
import javax.sql.*;
import java.util.*;

 
public class MySQLDataStoreUtilities {

	//Connection conn = null;

	//========================connect mysql database to servlet===================================
	public static void getConnection(){

		try{       //register JDBC driver
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderinfodb?useUnicode=true&characterEncoding=utf-8&useSSL=false" ,"root","root");
  		}catch(Exception e){
  			System.out.println("Fail connecting to database server...");
  			e.printStackTrace();
  		}
	}
	
	public static void closeJDBC(Connection conn){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	//===================================CONNECT SERVLET WITH MYSQL DATABASE AND PASS VALUES TO DATABASE=====================================
	//store register user info into registration table
	public static void insertUser(String username,String password,String repassword, String usertype){    //database stores user information
		Connection conn;

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfodb","root","root");

			String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,password,repassword,usertype)"   //insert into Registration table
              													+ "VALUES (?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,username); 
				pst.setString(2,password);
				pst.setString(3,repassword);
				pst.setString(4,usertype); 
				pst.execute();
    	}catch(Exception e){

    	}
    }

    //store placed order information into mysql table orderinfo
    public static void insertOrder(String orderid,String username,int totalprice,String address,String creditnum,String deliverydate, int sales, String date){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderinfodb","root","root");
			String insertIntoCustomerOrderQuery = "INSERT INTO orderinfo(orderid,username,totalprice,address,creditnum,deliverydate, sales, date)" + "VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
				pst.setString(1,orderid); 
				pst.setString(2,username); 
				pst.setInt(3,totalprice);
				pst.setString(4,address);
				pst.setString(5,creditnum);
				pst.setString(6,deliverydate);
				pst.setInt(7,sales);
				pst.setString(8,date);
				pst.execute();
		}catch(Exception e){

		}
	}

	public static void insertOrderedItem(String orderid,String itemid,String itemname, int itemprice, int soldnum){   
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderinfodb","root","root");    
			String insertIntoCustomerRegisterQuery = "INSERT INTO ordereditem(orderid, itemid, itemname, itemprice, soldnum)"  
              													+ "VALUES (?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,orderid); 
				pst.setString(2,itemid);
				pst.setString(3,itemname);
				pst.setInt(4,itemprice);
				pst.setInt(5,soldnum);
				pst.execute();
    	}catch(Exception e){

    	}
    }

    public static void updateStock(int soldnum, String itemid){
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","root");
			String updataItemStock = "UPDATE product set stock= stock-? WHERE id= ?;";
			PreparedStatement pst = conn.prepareStatement(updataItemStock);
				pst.setInt(1,soldnum);
				pst.setString(2,itemid);
				pst.execute();
			System.out.println("success");
    	}catch(Exception e){

    	}
    }

    //test database, import table
    public static void addProduct(int id, String image, String name, String color, int price, int stock, String rebate, String acc1, String acc2,
    								String acc3, String acc4, String acc5, String acc6, String acc7){
    	try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");    
			String insertnewproduct = "INSERT INTO import(id, image, name, Color, Price, stock, rebate, accessory1, accessory2, accessory3, accessory4, accessory5, accessory6, accessory7)"  
              													+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertnewproduct);
				pst.setInt(1,id); 
				pst.setString(2,image);
				pst.setString(3,name);
				pst.setString(4,color);
				pst.setInt(5,price);
				pst.setInt(6,stock);
				pst.setString(7,rebate);
				pst.setString(8,acc1);
				pst.setString(9,acc2);
				pst.setString(10,acc3);
				pst.setString(11,acc4);
				pst.setString(12,acc5);
				pst.setString(13,acc6);
				pst.setString(14,acc7);
				pst.execute();
    	}catch(Exception e){

    	}
    }

    public static void deleteProduct(int id){
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","root");
			String deleteproduct = "delete from import where id=?;";
			PreparedStatement pst = conn.prepareStatement(deleteproduct);
				pst.setInt(1,id);
				pst.execute();
    	}catch(Exception e){

    	}
    }

    public static HashMap<String, Integer> getData(){
    	Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false" ,"root","root");
  		}catch(Exception e){
  			System.out.println("Fail connecting to database server...");
  			e.printStackTrace();
  		}

  		HashMap<String, Integer> productMap = new HashMap<String, Integer>();
		Statement st = null;
		ResultSet rs = null;
		product p; 
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM import");   //Select all column from table "product"
			while(rs.next()){
				int id      = rs.getInt("id");
				String name = rs.getString("name");
				int price   = rs.getInt("Price");
				int stock   = rs.getInt("stock");
				String rebate  = rs.getString("rebate");
				p = new product(id,name,price,stock,rebate);
				productMap.put(name, id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		Iterator iter = productMap.entrySet().iterator();  
		while (iter.hasNext()) {  
            Map.Entry entry = (Map.Entry) iter.next();  
            Object key = entry.getKey();  
            Object value = entry.getValue();  
            System.out.println(key + "      " + value);
        }
        return productMap;  
    }

    public static void main(String[] args) {
    	getData();
    }
}
