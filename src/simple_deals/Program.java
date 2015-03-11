package simple_deals;

import java.util.Scanner;

public class Program {
	
	private static final int COUNT_DEALS = 2; //кол-во сделок
	private static final int MAX_PRODUCTS = 1; //кол-во продуктов
	private int type_of_product; //тип продукта - 1 - фотики или 2- ботинки
	private Deal[] deals = new Deal[COUNT_DEALS];   //в обьекте deals хранится массив сделок - обьекты Party и Product

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
			System.out.println("Сделка №" + (tail + 1));
		d = inputDeal();   //вызов очередной сделки
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
				System.out.println("Покупка/Продажа Фотоаппаратов.");
				System.out.println("Кол-во мегапикселей:  " + d.getProducts());
			}
						
			for (Product pr: d.getProducts()){
				System.out.println("Title:  " + pr.getTitle() + "  Price:  " + pr.getPrice() + "  Q-ty:  " + pr.getQuantity());
			}
		    System.out.println("Summa: " + d.getSum());	    
		}
	}
	
	private Deal inputDeal(){
		
		Party buyer = inputParty("buyer"); // возвращается обьект, который содержит name, address, key[] и values[]
		Party seller = inputParty("seller");
		
		System.out.print("Сделка по купле/продаже: -фотиков (нажмите 1) или -ботинок (нажмите 2)");
		type_of_product = Integer.valueOf(keyboard("aa", false)); 
				
		Product[] pr = new Product[MAX_PRODUCTS];// массив объектов Product
		
		for (int i = 0; i < pr.length ; i++){
			pr[i] = inputProduct(); //возвращает или объект класса FotoProduct или обьект класса BotinkiProduct
			}
				
		Deal d = new Deal(seller, buyer, pr); //в одной сделке 3 обьекта - Party: buyer,seller,  и Product: массив объектов Product
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
					
		Product pr = null; //заглушка, чтобы обращение ниже аля pr.setTitle(title) не выдавало ошибку в Эклипсе
		
		if (type_of_product == 1){
			
			FotoProduct fotoPr = new FotoProduct();
			String megapx = keyboard("кол-во мегапикселей фотоаппарата", true);
			String digital = keyboard("тип фотоаппарата: 1 - цифровой, 0- пленочный", true);
			fotoPr.setMegapx(Double.valueOf(megapx));
			fotoPr.setDigital(Boolean.valueOf(digital));
			
			pr = fotoPr;
			
		}else if (type_of_product == 2){
			
			BotinkiProduct botinkiPr = new BotinkiProduct();
			String size = keyboard("размер ботинок", true);
			String color = keyboard("цвет ботинок", true);
			botinkiPr.setSize(Integer.valueOf(size));
			botinkiPr.setColor(color);
			
			pr = botinkiPr;
			
		}else{
			System.err.println("Неизвестный продукт!");
			System.exit(-1);
		}
		
		String title = keyboard("title", true);
		String priceStr = keyboard("price", true);
		String quantityStr = keyboard("quantity", true);
		
		//Product pr = new Product(); // один из объектов, который сохраняется в массиве объектов Product в inputDeal()
		pr.setTitle(title);
		pr.setPrice(Double.valueOf(priceStr));
		pr.setQuantity(Integer.valueOf(quantityStr));
	
		return pr;
		
	}
	
	private String keyboard(String message, boolean visible) { 
		
		if (visible) System.out.print("Введите пожалуйста " + message + ": "); 
	     Scanner scan = new Scanner(System.in); 
	     String rez = scan.next(); 
	     scan.reset(); 
	     return rez; 
	}

}
