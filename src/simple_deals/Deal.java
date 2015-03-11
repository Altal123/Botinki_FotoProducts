package simple_deals;

import java.util.Date;

public class Deal {

	private Date date;
	private Party seller;
	private Party buyer;
	private Product[] products; //храним массив продуктов, которые добавл€ютс€ в текущую сделку
	
	public Deal(Party seller, Party buyer, Product[] pr){  // конструктор
		date = new Date(); //формирует дату
		this.seller = seller;
		this.buyer = buyer;
		products = pr;
	}

	//--------------------------------------------геттеры дл€ полей класса------------

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
	public Product[] getProducts() {
		return products;
	}

//--------------------------------------------геттеры дл€ полей класса------------

	public double getSum(){          // выводит общую сумму всех товаров, суммиру€ товар в массиве produts
		
		double rez = 0;
		
		for (Product pr:products){
			
			rez += pr.getCost();
			
		}
		return rez;
	}
}


