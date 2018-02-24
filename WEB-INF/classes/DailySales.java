
import java.sql.*;
import javax.sql.*;
import java.util.*;

class DailySales{
	private String CreditCardNo;
	private String Date;
	private int    Sales;

	public DailySales(){

	}

	public DailySales(String creditcardno, String date, int sales){
		this.CreditCardNo = creditcardno;
		this.Date 	  = date;
		this.Sales    = sales;
	}

	public String getCreditCardNo(){
		return this.CreditCardNo;
	}

	public String getDate(){
		return this.Date;
	}

	public int getSales(){
		return this.Sales;
	}
}