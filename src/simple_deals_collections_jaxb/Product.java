package simple_deals_collections_jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType (XmlAccessType.PROPERTY)
public abstract class Product {
	
	public static final int DEF_DISCOUNT = 10; // константа, процент скидки на товар кол-вом > 10
	
	private String title;
	private double price;
	private int type_of_product; //тип продукта - 1 - фотики или 2- ботинки
	
    //--------------------------------------------геттеры и сеттеры для полей класса------------	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getType_of_product() {
		return type_of_product;
	}

	public void setType_of_product(int type_of_product) {
		this.type_of_product = type_of_product;
	}

	//--------------------------------------------------------
	
	protected int getDiscount(int quantity){      //учет скидки при кол-ве товара > 10 
		
		if (quantity > 10) return DEF_DISCOUNT;
		else return 0;
		
	}

    public double getCost(int quantity){        //возвращает стоимость товара
    	
    	double realCost = quantity * price; 
    	
    	return realCost - realCost * getDiscount(quantity)/100;
    	
    }
    
	public double getMegapx() {               //пустышка
		// TODO Auto-generated method stub
		return 1;
	}
	public boolean isDigital() {              //пустышка
		// TODO Auto-generated method stub
		return false;
	}
	public int getSize() {                    //пустышка
		// TODO Auto-generated method stub
		return 1;
	}
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}