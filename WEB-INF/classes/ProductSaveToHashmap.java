
import java.sql.*;
import javax.sql.*;
import java.util.*;

class product{
	private int    Id;
	private String Name;
	private int    Price;
	private int    Stock;
	private String Rebate;

	public product(){ 

	}

	public product(int id, String name){
		this.Id = id;
		this.Name = name;
	}

	public product(int id, String name, int price, int stock, String rebate){
		this.Id = id;
		this.Name = name;
		this.Price = price;
		this.Stock = stock;
		this.Rebate = rebate;
	}

	public int getId(){
		return this.Id;
	}

	public String getName(){
		return this.Name;
	}

	public int getPrice(){
		return this.Price;
	}

	public int getStock(){
		return this.Stock;
	}

	public String getRebate(){
		return this.Rebate;
	}
}



public class ProductSaveToHashmap{

	//Method used to save data to hashmap from mysql database
	public static HashMap<Integer, product> saveToHashMap(){
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
				System.out.println(pr.getId() + "    " + pr.getName() + "    " + pr.getPrice() + "    " + pr.getStock() + "    " + pr.getRebate());
		}
		return hsmp;
	}

	public static void main(String[] args) {
		saveToHashMap();
	}
}
