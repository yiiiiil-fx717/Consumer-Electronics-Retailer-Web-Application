
class Review{
	public String productName;
	public String userName;
	public String reviewRate;
	public String reviewDate;
	public String reviewText;

	Review(){

	}

	Review(String productName, String userName, String reviewRate, String reviewDate, String reviewText){
		this.productName = productName;
		this.userName = userName;
		this.reviewRate = reviewRate;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}


}