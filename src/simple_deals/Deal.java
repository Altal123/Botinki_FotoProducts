package simple_deals;

import java.util.Date;

public class Deal {

	private Date date;
	private Party seller;
	private Party buyer;
	private Product[] products; //������ ������ ���������, ������� ����������� � ������� ������
	
	public Deal(Party seller, Party buyer, Product[] pr){  // �����������
		date = new Date(); //��������� ����
		this.seller = seller;
		this.buyer = buyer;
		products = pr;
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
	public Product[] getProducts() {
		return products;
	}

//--------------------------------------------������� ��� ����� ������------------

	public double getSum(){          // ������� ����� ����� ���� �������, �������� ����� � ������� produts
		
		double rez = 0;
		
		for (Product pr:products){
			
			rez += pr.getCost();
			
		}
		return rez;
	}
}


