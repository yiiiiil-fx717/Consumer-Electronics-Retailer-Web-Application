
import java.sql.*;
import javax.sql.*;
import java.util.*;

class ordered{
	private String OrderId;
	private String ItemId;
	private String ItemName;
	private int    ItemPrice;
	private int    SoldNum;

	public ordered(){

	}

	public ordered(String itemname, int itemprice, int soldnum){
		this.ItemName   = itemname;
		this.ItemPrice 	= itemprice;
		this.SoldNum    = soldnum;
	}

	public String getOrderId(){
		return this.OrderId;
	}

	public String getItemId(){
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



public class OrderedItem{
	public static void soldTable(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderinfodb?useUnicode=true&characterEncoding=utf-8&useSSL=false" ,"root","root");
        }catch(Exception e){
           
        }

        HashMap<Integer, ordered> hsmp = new HashMap<Integer, ordered>();
        Statement st = null;
        ResultSet rs = null;
        ordered p;
        try{
            st = conn.createStatement();
            //String getSoldNum = "select itemname,itemprice,sum(soldnum) as soldnum from ordereditem group by itemid;";

            rs = st.executeQuery("select itemname,itemprice,sum(soldnum) as soldnum from ordereditem group by itemid;");
            while(rs.next()){
                //String orderid  = rs.getString("orderid");
                //String itemid   = rs.getString("itemid");
                String itemname = rs.getString("itemname");
                int itemprice   = rs.getInt("itemprice");
                int soldnum     = rs.getInt("soldnum");
                p = new ordered(itemname, itemprice, soldnum);
                hsmp.put(itemprice,p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        for(int i: hsmp.keySet()){
			ordered pr = hsmp.get(i);
				System.out.println(pr.getItemName() + "      " + pr.getItemPrice() + "      " + pr.getSoldNum());
		}
    }
}