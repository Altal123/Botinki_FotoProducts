package simple_deals_collections_jaxb;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deal")
@XmlAccessorType (XmlAccessType.FIELD)
public class Deal {

	private Date date;
	private int quantity;
	
	@XmlElement(name = "party")	
	private Party seller;
	private Party buyer;
	
//	@XmlElementWrapper(name = "products")
//	@XmlElement
	@XmlElement(name = "product")
	Map <Product, Integer> products = new LinkedHashMap<Product, Integer>(); //коллекция продуктов
	
	public Deal(Party seller, Party buyer){                   // конструктор
		date = new Date(); //формируем текущую дату сделки
		this.seller = seller;
		this.buyer = buyer;
	}

	//--------------------------------------------геттеры для полей класса------------

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}



	/**
	 * @return the seller
	 */
	public Party getSeller() {
		return seller;
	}



	/**
	 * @return the buyer
	 */
	public Party getBuyer() {
		return buyer;
	}



	/**
	 * @return the products
	 */
	public Map<Product, Integer> getProducts() {
		return products;
	}

//--------------------------------------------геттеры для полей класса------------

	public double getSum(){          // выводит общую сумму всех товаров за 1 сделку
		
		double rez = 0;
				
		for(Entry <Product, Integer> entry:products.entrySet()){
			
			quantity = entry.getValue();      //получаем кол-во товара каждого продукта 
			rez += entry.getKey().getCost(quantity);  
						
		}
		return rez;
	}
}


