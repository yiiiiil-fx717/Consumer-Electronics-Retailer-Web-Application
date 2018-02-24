

public class product{
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