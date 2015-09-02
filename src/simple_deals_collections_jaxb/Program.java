package simple_deals_collections_jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.lang3.BooleanUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Program {
	
	//private static final int COUNT_DEALS = 2; //���-�� ������
	//private static final int MAX_PRODUCTS = 4; //���-�� ��������� � ����� ������!
	//private static final int MAX_KEYVALUES = 2; //���-�� ������ Key Values
	private int quantity;
	//Collection <Deal> deals = new ArrayList <Deal>();        //��������� ���� ������ � ���� ArrayList
	List <Product> allproducts = new LinkedList <Product>(); //���������/��������� ���� ��������� LinkedList
	static Deals deals = new Deals();
	
	public static void main(String[] args) {
		
		new Program().allActions();
	}

	private void allActions() {
		
		//input();
//		System.out.println("================================");
//		System.out.println("================================");
		jaxbMarshalling();
		//output();
	}
	
	private void input(){
	
		int tail = 1; //������� ������
		Deal d;
		deals.setDeals(new ArrayList <Deal>());
		
		while (tail != 0){
			
		System.out.println();	
		System.out.println("������ �" + (tail));
		d = inputDeal();   //����� ��������� ������
		//deals.add(d);      //��������� ������ � ��������� ������
		deals.getDeals().add(d); //��������� ������ � ��������� ������
		
		tail++;
		
		if (!continueInputQuestion("���� ������")) tail = 0; // �������� - ����� �� �� ������� ����� ������?
		
		}
		
	}
	
	private boolean continueInputQuestion(String string) {
		System.out.println("������ ��� " + string + "? (y - Yes, n - No)");
		String answer = keyboard(string, false);
		if (answer.equalsIgnoreCase("y")) return true;
		else return false;
		
	}

	private void output(){
		
		int i = 0;
		int numberDeals = 1;     //������� ��������� ������
		int numberProducts = 1;  //������� ��������� ���������
				
		for (Deal d:deals.getDeals()){
			
			System.out.println();
			System.out.println("========== ����� ������ �:" + numberDeals + " ============");
			numberDeals ++;
			System.out.println("Deal: " + d.getDate());
			System.out.println("----------------------------------");
			System.out.println("Buyers Name: " + d.getBuyer().getName());
			System.out.println("Buyers Address : " + d.getBuyer().getAddress());	
				
			for(Entry <String,String> entry:d.getBuyer().getKeyValues().entrySet()){
				System.out.println("Buyers Keys" + (i + 1) + ": " + entry.getKey());
				System.out.println("Buyers Values" + (i + 1) + ": " + entry.getValue());
				i++;
			}
			i = 0;
			
			System.out.println("-----");
			System.out.println("Sellers Name: " + d.getSeller().getName());
			System.out.println("Sellers Address : " + d.getSeller().getAddress());
			
			for(Entry <String,String> entry:d.getSeller().getKeyValues().entrySet()){
				System.out.println("Sellers Keys" + (i + 1) + ": " + entry.getKey());
				System.out.println("Sellers Values" + (i + 1) + ": " + entry.getValue());
				i++;
			}
					
			System.out.println(d.getBuyer().getName() + " buys in " + d.getSeller().getName() + ":");
			System.out.println();
			
			//����� ��������� ������� ������---------------------------------------------------------
			for(Entry <Product, Integer> entry:d.getProducts().entrySet()){
				
				Product product = entry.getKey();
				int quantity = entry.getValue();
				
				System.out.println("---------- ����� �������� �:" + numberProducts + " -----------");
				numberProducts ++;
				
				if (product.getType_of_product() == 1){
					System.out.println("�������/������� �������������.");
					System.out.println("���-�� ������������:  " + product.getMegapx());
					  if (product.isDigital()){
	                     System.out.println("��� �������������: ��������");
					  }
					  else{
						 System.out.println("��� �������������: ���������");
					  }
				}else{
					System.out.println("�������/������� �������.");
					System.out.println("������ �������:  " + product.getSize());
					System.out.println("���� �������:  " + product.getColor());  
				}
				System.out.println("Title:  " + product.getTitle() + "  Price:  " + product.getPrice() + "  Q-ty:  " + quantity + "  Amount: " + product.getCost(quantity));		
			}		
			
			System.out.println("----------------------------------");
		    System.out.println("����� ����� ������ (Total Amount): " + d.getSum());	    
		}
	}
	
	private Deal inputDeal(){
		
		Party buyer = inputParty("buyer"); // ������������ ������ �� ������, ������� �������� name, address, key[] � values[]
		Party seller = inputParty("seller");
		
		Deal d = new Deal(seller, buyer); //� ����� ������ 3 ������� - Party: buyer,seller,  � Product: ������ �������� Product
					
		while (true){
			
			Product pr = inputProduct();
		    d.getProducts().put(pr, quantity); // ������ �� ������ Product � ��� ���-�� ��������� � ��������� Map ������� ������
			allproducts.add(pr); //������ �� ������ Product ��������� � ����� ��������� Products (���� ������)
			System.out.println("----------------------------------");
			
			if(!continueInputQuestion("���� �������")) break; //����� � ����� ���� �� ����� ������ ����� ���������  
			
		}	 	
		
		return d;
	}
	
	private Party inputParty(String party){
		 
			String partyName = keyboard(party, true);
			String partyAddress = keyboard(party + "`s " + "address", true);
			
			Party parties = new Party();
			
			int i = 1;
			while (true){
				
				String keys = keyboard(party + "`s " + "key" + i, true);
				String values = keyboard(party + "`s " + "value" + i, true);
				parties.getKeyValues().put(keys, values);
                i++;							
				
				if (!continueInputQuestion("���� Key - Value")) break;
				
			}
				
			parties.setName(partyName);
			parties.setAddress(partyAddress);
						
			return parties;
	}
	
	private Product inputProduct(){
		
		int choiseProduct = 0;
		String typeProducts; 
		int previousProduct = 2; // �� ��������� �������� 2 - ������� ����� �������� ��-�����, ���� 1 - �� ������������ �������, ������� ��� ������ �����
		Product pr = null; //��������, ����� ��������� ���� �-�� pr.setTitle(title) �� �������� ������ � �������
			
		System.out.println("---->>>");
		System.out.println("���� ������ �������� ������� ������.");
		
		//���� ������ �������� - ������ ������� �������� ��� ����� ������
		if (!allproducts.isEmpty()){                                  //������� ��������� �� ������ �� ������ ���������
			
			System.out.println("����� �������������� ����� ��������� �������? �������: 1-��, 2-���");
			previousProduct = Integer.valueOf(keyboard("aa", false));
			
			//���� ������ ������������� ��������
			if (previousProduct == 1) {
				System.out.println("�������� �� ������ ����� ����� ���������� ��������:");
				ListIterator <Product> itr = allproducts.listIterator();
				
				while (itr.hasNext()){
					
					pr = itr.next();
					if (pr.getType_of_product() == 1) typeProducts = "�����������";
					else typeProducts = "�������";
					System.out.println("������� �" + (choiseProduct + 1) + ": " + typeProducts + "  Title: " + pr.getTitle() + "  Price: " + pr.getPrice());
					choiseProduct++;				
				}
				System.out.println("��� �����:");
				choiseProduct = Integer.valueOf(keyboard("aa", false));
				//�������� ��� ����� ������ ����� ��������� �������� ���������
				if (choiseProduct > allproducts.size() || choiseProduct <= 0) {
					
					System.err.println("����������� �������!");
					System.exit(-1);
				}
				
				//��������� ������ �� ����� ��������� �������
				pr = allproducts.get(choiseProduct - 1);
								
			} else if (previousProduct != 2) {
				
				System.err.println("����� ���������� ������ �� ���������!");
				System.exit(-1);
			}
				
		}

		   //���� ����� ������ ��������
		   if (previousProduct == 2){
						
	         System.out.print("������ �� �����/�������: -������� (������� 1) ��� -������� (������� 2)");
		     int type_of_product = Integer.valueOf(keyboard("aa", false)); //��� �������� - 1 - ������ ��� 2- ������� 
		
		       if (type_of_product == 1){
			
			FotoProduct fotoPr = new FotoProduct();
			String megapx = keyboard("���-�� ������������ ������������", true);
			int digital = Integer.valueOf(keyboard("��� ������������: 1 - ��������, 0- ���������", true));
			fotoPr.setMegapx(Double.valueOf(megapx));
			fotoPr.setDigital(BooleanUtils.toBoolean(digital));
			fotoPr.setType_of_product(1);
			
			pr = fotoPr;
			
	    	}else if (type_of_product == 2){
			
			BotinkiProduct botinkiPr = new BotinkiProduct();
			String size = keyboard("������ �������", true);
			String color = keyboard("���� �������", true);
			botinkiPr.setSize(Integer.valueOf(size));
			botinkiPr.setColor(color);
			botinkiPr.setType_of_product(2);
			
			pr = botinkiPr;
			
		    }else{
			System.err.println("����������� �������!");
			System.exit(-1);
		    }
		
		    String title = keyboard("title", true);
		    String priceStr = keyboard("price", true);
		    pr.setTitle(title);
		    pr.setPrice(Double.valueOf(priceStr));		
		
		}
		
		String quantityStr = keyboard("quantity", true);	
		quantity = Integer.valueOf(quantityStr);
	
		return pr;
		
	}
	
	private String keyboard(String message, boolean visible) { 
		
		if (visible) System.out.print("������� ���������� " + message + ": "); 
	     Scanner scan = new Scanner(System.in); 
	     String rez = scan.next(); 
	     scan.reset(); 
	     return rez; 
	}
	
	private void jaxbMarshalling() {
		
		deals.setDeals(new ArrayList <Deal>());
		
		//Party buyer
		
		String partyName = "Adidas";
		String partyAddress = "Adidas Address 1";
		
		Party buyer = new Party();
			
		String keys = "11";
		String values = "22";
		String keys2 = "33";
		String values2 = "44";
		
		buyer.getKeyValues().put(keys, values);
		buyer.getKeyValues().put(keys2, values2);        					
			
		buyer.setName(partyName);
		buyer.setAddress(partyAddress);
		
		//Party seller
		
		String partyName2 = "Adidas";
		String partyAddress2 = "Adidas Address 1";
		
		Party seller = new Party();
			
		String keys3 = "11";
		String values3 = "22";
		String keys4 = "33";
		String values4 = "44";
		
		seller.getKeyValues().put(keys3, values3);
		seller.getKeyValues().put(keys4, values4);        					
			
		seller.setName(partyName2);
		seller.setAddress(partyAddress2);
		
		Deal d = new Deal(seller, buyer); //� ����� ������ 3 ������� - Party: buyer,seller,  � Product: ������ �������� Product
		
		//Product information
		
		FotoProduct fotoPr = new FotoProduct();
		
		fotoPr.setMegapx(Double.valueOf("16"));
		fotoPr.setDigital(BooleanUtils.toBoolean(1));
		fotoPr.setType_of_product(1);
		
		Product pr = fotoPr;
		
		String title = "�������";
	    String priceStr = "650";
	    pr.setTitle(title);
	    pr.setPrice(Double.valueOf(priceStr));		
	
	    String quantityStr = "3";	
	    quantity = Integer.valueOf(quantityStr);
			
	    d.getProducts().put(pr, quantity); // ������ �� ������ Product � ��� ���-�� ��������� � ��������� Map ������� ������
	
	    deals.getDeals().add(d); //��������� ������ � ��������� ������
		
		try {
		File file = new File("d:\\3\\Product.xml");
		
		if (file.exists()) file.delete();
		
		file.createNewFile();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Deals.class);
		
		Marshaller mar = jaxbContext.createMarshaller();
		
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		mar.marshal(deals, System.out);
		//mar.marshal(deals, file);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
