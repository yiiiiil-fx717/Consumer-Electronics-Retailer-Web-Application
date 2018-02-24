
import java.sql.*;
import javax.sql.*;
import java.util.*;

class product{
	private int    Id;
	private String Name;

	public product(){

	} 

	public product(int id, String name){
		this.Id = id;
		this.Name = name;
	}

	public int getId(){
		return this.Id;
	}

	public String getName(){
		return this.Name;
	}
}

public class AjaxUtility{

	private static StringBuffer gsb = null;
	public AjaxUtility(StringBuffer sb){
		gsb = sb;
	}

	public static HashMap<String, product> getData(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?useUnicode=true&characterEncoding=utf-8&useSSL=false" ,"root","root");
  		}catch(Exception e){
  			System.out.println("Fail connecting to database server...");
  			e.printStackTrace();
  		}

		HashMap<String, product> hsmp = new HashMap<String, product>();
		Statement st = null;
		ResultSet rs = null;
		product p;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM product");   //Select all column from table "product"
			while(rs.next()){
				int id      = rs.getInt("id");
				String name = rs.getString("name");
				p = new product(id,name);
				hsmp.put(String.valueOf(id),p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		Iterator iter = hsmp.entrySet().iterator();  
        while (iter.hasNext()) {  
            Map.Entry entry = (Map.Entry) iter.next();  
            Object key = entry.getKey();  
            Object value = entry.getValue();  
            System.out.println(key + "     " + value);  
  
        }  
		return hsmp;
	}

	//readData() function used to get the products starting with letter typed from hashmap into string buffer
	public StringBuffer readData(String searchId){
		HashMap<String,product> data;
		data=getData();
		Iterator it = data.entrySet().iterator(); 

		while (it.hasNext()){
			Map.Entry pi = (Map.Entry)it.next();
			product p=(product)pi.getValue();
		
			if(p.getName().toLowerCase().startsWith(searchId)){
				gsb.append("<product>");
				gsb.append("<id>" + p.getId() + "</id>"); 
				gsb.append("<productName>" + p.getName() + "</productName>"); 
				gsb.append("</product >");
			} 
		}
		return gsb; 
	}
	

	public static void main(String[] args) {
		getData();
	}
}
