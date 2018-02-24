
import java.sql.*;
import javax.sql.*;
import java.util.*;

class ordereditem{
	private String OrderId;
	private int    ItemId;
	private String ItemName;
	private int    ItemPrice;
	private int    SoldNum;

	public ordereditem(){

	}

	public ordereditem(String orderid, int itemid, String itemname, int itemprice, int soldnum){
		this.OrderId = orderid;
		this.ItemId = itemid;
		this.ItemName = itemname;
		this.ItemPrice = itemprice;
		this.SoldNum = soldnum;
	}

	public String getOrderId(){
		return this.OrderId;
	}

	public int getItemId(){
		return this.ItemId;
	}

	public String getItemName(){
		return this.ItemName;
	}

	public int getItemPrice(){
		return this.ItemPrice;
	}

	public int getSoldNum(){
		return this.SoldNum;
	}
}


public class OrderedProductSaveToHashmap{
	//Retrieve data from ordereditem table to hashmap
	public static HashMap<Integer, ordereditem> saveOrderedItemToHP(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderinfodb?useUnicode=true&characterEncoding=utf-8&useSSL=false" ,"root","root");
  		}catch(Exception e){
  			System.out.println("Fail connecting to database server...");
  			e.printStackTrace();
  		}

		HashMap<Integer, ordereditem> map = new HashMap<Integer, ordereditem>();
		Statement st = null;
		ResultSet rs = null;
		ordereditem p;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM ordereditem");  
			while(rs.next()){
				String orderid      = rs.getString("orderid");
				int itemid          = rs.getInt("itemid");
				String itemname     = rs.getString("itemname");
				int itemprice       = rs.getInt("itemprice");
				int soldnum         = rs.getInt("soldnum");
				p = new ordereditem(orderid, itemid, itemname, itemprice, soldnum);
				map.put(itemid,p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		//Print data retrieved from table
		for(int i: map.keySet()){
			ordereditem pr = map.get(i);

				System.out.println(pr.getOrderId() + "     " + pr.getItemId() + "     " + pr.getItemName() + "          " + pr.getItemPrice() + "    " + pr.getSoldNum());
		}
		return map;
	}

	public static void main(String[] args) {
		saveOrderedItemToHP();
	}
}