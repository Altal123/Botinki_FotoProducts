package simple_deals_collections_jaxb;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Deal {

	private Date date;
	private Party seller;
	private Party buyer;
	private int quantity;
	//private Product[] products; //������ ������ ���������, ������� ����������� � ������� ������
	Map <Product, Integer> products = new LinkedHashMap<Product, Integer>(); //��������� ���������
	
	public Deal(Party seller, Party buyer){                   // �����������
		date = new Date(); //��������� ������� ���� ������
		this.seller = seller;
		this.buyer = buyer;
	}

	//--------------------------------------------������� ��� ����� ������------------

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

//--------------------------------------------������� ��� ����� ������------------

	public double getSum(){          // ������� ����� ����� ���� ������� �� 1 ������
		
		double rez = 0;
				
		for(Entry <Product, Integer> entry:products.entrySet()){
			
			quantity = entry.getValue();      //�������� ���-�� ������ ������� �������� 
			rez += entry.getKey().getCost(quantity);  
						
		}
		return rez;
	}
}


