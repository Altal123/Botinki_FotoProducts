package simple_deals;

public abstract class Product {
	
	public static final int DEF_DISCOUNT = 10; // константа, процент скидки на товар кол-вом > 10
	private String title;
	private double price;
	private int quantity;
	
    //--------------------------------------------геттеры и сеттеры для полей класса------------	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/** 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//--------------------------------------------геттеры и сеттеры для полей класса------------
	
	protected int getDiscount(){
		
		if (quantity > 10) return DEF_DISCOUNT;
		else return 0;
		
	}

    public double getCost(){        //возвращает стоимость товара
    	
    	double realCost = quantity * price; 
    	
    	return realCost - realCost * getDiscount()/100;
    	
    }
}