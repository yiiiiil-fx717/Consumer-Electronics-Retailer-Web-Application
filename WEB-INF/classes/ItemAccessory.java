


public class ItemAccessory implements java.io.Serializable{
	private String name;
	private double price;
	private String image;
	private String color;
    private double discount;
	
	public ItemAccessory(String name, double price, String image, String retailer,String color,double discount){
		this.name     = name;
		this.price    = price;
		this.image    = image;
		this.color    = color;
		this.discount = discount;
	}
		
	public ItemAccessory() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String condition) {
		this.color = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
