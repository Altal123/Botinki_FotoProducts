package simple_deals_collections_jaxb;

import javax.xml.bind.annotation.XmlElement;

public abstract class Product {
	
	public static final int DEF_DISCOUNT = 10; // ���������, ������� ������ �� ����� ���-��� > 10
	private String title;
	private double price;
	private int type_of_product; //��� �������� - 1 - ������ ��� 2- �������
	
    //--------------------------------------------������� � ������� ��� ����� ������------------	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/** 
	 * @param title the title to set
	 */
	@XmlElement
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
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the type_of_product
	 */
	public int getType_of_product() {
		return type_of_product;
	}
	/**
	 * @param type_of_product the type_of_product to set
	 */
	@XmlElement
	public void setType_of_product(int type_of_product) {
		this.type_of_product = type_of_product;
	}

	//--------------------------------------------������� � ������� ��� ����� ������------------
	
	protected int getDiscount(int quantity){      //���� ������ ��� ���-�� ������ > 10 
		
		if (quantity > 10) return DEF_DISCOUNT;
		else return 0;
		
	}

    public double getCost(int quantity){        //���������� ��������� ������
    	
    	double realCost = quantity * price; 
    	
    	return realCost - realCost * getDiscount(quantity)/100;
    	
    }
    
	public double getMegapx() {               //��������
		// TODO Auto-generated method stub
		return 1;
	}
	public boolean isDigital() {              //��������
		// TODO Auto-generated method stub
		return false;
	}
	public int getSize() {                    //��������
		// TODO Auto-generated method stub
		return 1;
	}
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}