package simple_deals;

import java.util.Scanner;

public class Program {
	
	private static final int COUNT_DEALS = 2; //���-�� ������
	private static final int MAX_PRODUCTS = 1; //���-�� ���������
	private int type_of_product; //��� �������� - 1 - ������ ��� 2- �������
	private Deal[] deals = new Deal[COUNT_DEALS];   //� ������� deals �������� ������ ������ - ������� Party � Product

	public static void main(String[] args) {
		new Program().allActions();
	}

	private void allActions() {
		input();
		System.out.println("================================");
		output();
	}
	
	private void input(){
	
		int tail = 0;
		Deal d;
		
		while (tail < deals.length){
			System.out.println("������ �" + (tail + 1));
		d = inputDeal();   //����� ��������� ������
		deals[tail] = d;
		tail++;
		}
		
	}
	
	private void output(){
				
		for (Deal d:deals){
			System.out.println("Deal " + d.getDate());
			System.out.println("----------------------------------");
			System.out.println("Buyers Name: " + d.getBuyer().getName());
			System.out.println("Buyers Address : " + d.getBuyer().getAddress());
			
			for (int i = 0; i < d.getBuyer().getKeys().length; i++){
				System.out.println("Buyers Keys" + (i + 1) + ": " + d.getBuyer().getKeys()[i]);
				System.out.println("Buyers Values" + (i + 1) + ": " + d.getBuyer().getValues()[i]);
			}
			
			System.out.println("----------------------------------");
			System.out.println("Sellers Name: " + d.getSeller().getName());
			System.out.println("Sellers Address : " + d.getSeller().getAddress());
			for (int i = 0; i < d.getSeller().getKeys().length; i++){
				System.out.println("Sellers Keys" + (i + 1) + ": " + d.getSeller().getKeys()[i]);
				System.out.println("Sellers Values" + (i + 1) + ": " + d.getSeller().getValues()[i]);
			}
			
			System.out.println("==================================");
			System.out.println(d.getBuyer().getName() + " buys in " + d.getSeller().getName() + ":");
			
			if (type_of_product == 1){
				System.out.println("�������/������� �������������.");
				System.out.println("���-�� ������������:  " + d.getProducts());
			}
						
			for (Product pr: d.getProducts()){
				System.out.println("Title:  " + pr.getTitle() + "  Price:  " + pr.getPrice() + "  Q-ty:  " + pr.getQuantity());
			}
		    System.out.println("Summa: " + d.getSum());	    
		}
	}
	
	private Deal inputDeal(){
		
		Party buyer = inputParty("buyer"); // ������������ ������, ������� �������� name, address, key[] � values[]
		Party seller = inputParty("seller");
		
		System.out.print("������ �� �����/�������: -������� (������� 1) ��� -������� (������� 2)");
		type_of_product = Integer.valueOf(keyboard("aa", false)); 
				
		Product[] pr = new Product[MAX_PRODUCTS];// ������ �������� Product
		
		for (int i = 0; i < pr.length ; i++){
			pr[i] = inputProduct(); //���������� ��� ������ ������ FotoProduct ��� ������ ������ BotinkiProduct
			}
				
		Deal d = new Deal(seller, buyer, pr); //� ����� ������ 3 ������� - Party: buyer,seller,  � Product: ������ �������� Product
		return d;
	}
	
	private Party inputParty(String party){
		 
			String partyName = keyboard(party, true);
			String partyAddress = keyboard(party + "`s " + "address:", true);
			
			String[] keys = new String[2];
			String[] values = new String[2];
			
			for (int i = 0; i < keys.length; i++){
				
				keys[i] = keyboard(party + "`s " + "key" + (i + 1), true);
				values[i] = keyboard(party + "`s " + "value" + (i + 1), true);
			}
			
			Party parties = new Party();
			parties.setName(partyName);
			parties.setAddress(partyAddress);
			parties.setKeys(keys);
			parties.setValues(values);
						
			return parties;
	}
	
	private Product inputProduct(){
					
		Product pr = null; //��������, ����� ��������� ���� ��� pr.setTitle(title) �� �������� ������ � �������
		
		if (type_of_product == 1){
			
			FotoProduct fotoPr = new FotoProduct();
			String megapx = keyboard("���-�� ������������ ������������", true);
			String digital = keyboard("��� ������������: 1 - ��������, 0- ���������", true);
			fotoPr.setMegapx(Double.valueOf(megapx));
			fotoPr.setDigital(Boolean.valueOf(digital));
			
			pr = fotoPr;
			
		}else if (type_of_product == 2){
			
			BotinkiProduct botinkiPr = new BotinkiProduct();
			String size = keyboard("������ �������", true);
			String color = keyboard("���� �������", true);
			botinkiPr.setSize(Integer.valueOf(size));
			botinkiPr.setColor(color);
			
			pr = botinkiPr;
			
		}else{
			System.err.println("����������� �������!");
			System.exit(-1);
		}
		
		String title = keyboard("title", true);
		String priceStr = keyboard("price", true);
		String quantityStr = keyboard("quantity", true);
		
		//Product pr = new Product(); // ���� �� ��������, ������� ����������� � ������� �������� Product � inputDeal()
		pr.setTitle(title);
		pr.setPrice(Double.valueOf(priceStr));
		pr.setQuantity(Integer.valueOf(quantityStr));
	
		return pr;
		
	}
	
	private String keyboard(String message, boolean visible) { 
		
		if (visible) System.out.print("������� ���������� " + message + ": "); 
	     Scanner scan = new Scanner(System.in); 
	     String rez = scan.next(); 
	     scan.reset(); 
	     return rez; 
	}

}
