import java.sql.*;
import javax.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

//verify user id when login
   	public class UserVerify {
    	User user = null;
    	private String SQL = ""; 
    	Connection conn = null;
    	public User login(String userid, String password){
        	SQL = "select * from registration where username = ? and password = ?";
        	PreparedStatement pstmt = null;
        	try {
            	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfodb","root","root");
            	pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            	pstmt.setString(1, userid);
            	pstmt.setString(2, password);
	            ResultSet rSet = (ResultSet) pstmt.executeQuery();
	            //determine if the result set is valid(null or not null)
	            if(rSet.next()){
	                user = new User();
	                user.setUserId(rSet.getString("username"));
	                user.setPassword(rSet.getString("password"));
	            }
	            //conn.close();
	            //pstmt.close();
        	} catch (SQLException e) {
            	e.printStackTrace();
        	}
        	//finally{
            //	conn.close();
        	//}
        	return user;
    	}

	}