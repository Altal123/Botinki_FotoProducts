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
	
	//private static final int COUNT_DEALS = 2; //кол-во сделок
	//private static final int MAX_PRODUCTS = 4; //кол-во продуктов в одной сделке!
	//private static final int MAX_KEYVALUES = 2; //кол-во ключей Key Values
	private int quantity;
	//Collection <Deal> deals = new ArrayList <Deal>();        //Коллекция всех сделок в виде ArrayList
	List <Product> allproducts = new LinkedList <Product>(); //Коллекция/хранилище всех продуктов LinkedList
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
	
		int tail = 1; //счетчик сделок
		Deal d;
		deals.setDeals(new ArrayList <Deal>());
		
		while (tail != 0){
			
		System.out.println();	
		System.out.println("Сделка №" + (tail));
		d = inputDeal();   //вызов очередной сделки
		//deals.add(d);      //добавляем сделку в коллекцию сделок
		deals.getDeals().add(d); //добавляем сделку в коллекцию сделок
		
		tail++;
		
		if (!continueInputQuestion("одну сделку")) tail = 0; // проверка - хотим ли мы вводить новую сделку?
		
		}
		
	}
	
	private boolean continueInputQuestion(String string) {
		System.out.println("Введем еще " + string + "? (y - Yes, n - No)");
		String answer = keyboard(string, false);
		if (answer.equalsIgnoreCase("y")) return true;
		else return false;
		
	}

	private void output(){
		
		int i = 0;
		int numberDeals = 1;     //Счетчик выводимых сделок
		int numberProducts = 1;  //Счетчик выводимых продуктов
				
		for (Deal d:deals.getDeals()){
			
			System.out.println();
			System.out.println("========== Вывод сделки №:" + numberDeals + " ============");
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
			
			//вывод продуктов текущей сделки---------------------------------------------------------
			for(Entry <Product, Integer> entry:d.getProducts().entrySet()){
				
				Product product = entry.getKey();
				int quantity = entry.getValue();
				
				System.out.println("---------- Вывод продукта №:" + numberProducts + " -----------");
				numberProducts ++;
				
				if (product.getType_of_product() == 1){
					System.out.println("Покупка/Продажа фотоаппаратов.");
					System.out.println("Кол-во мегапикселей:  " + product.getMegapx());
					  if (product.isDigital()){
	                     System.out.println("Тип фотоаппаратов: цифровые");
					  }
					  else{
						 System.out.println("Тип фотоаппаратов: пленочные");
					  }
				}else{
					System.out.println("Покупка/Продажа ботинок.");
					System.out.println("Размер ботинок:  " + product.getSize());
					System.out.println("Цвет ботинок:  " + product.getColor());  
				}
				System.out.println("Title:  " + product.getTitle() + "  Price:  " + product.getPrice() + "  Q-ty:  " + quantity + "  Amount: " + product.getCost(quantity));		
			}		
			
			System.out.println("----------------------------------");
		    System.out.println("Общая сумма сделки (Total Amount): " + d.getSum());	    
		}
	}
	
	private Deal inputDeal(){
		
		Party buyer = inputParty("buyer"); // возвращается ссылка на объект, который содержит name, address, key[] и values[]
		Party seller = inputParty("seller");
		
		Deal d = new Deal(seller, buyer); //в одной сделке 3 обьекта - Party: buyer,seller,  и Product: массив объектов Product
					
		while (true){
			
			Product pr = inputProduct();
		    d.getProducts().put(pr, quantity); // ссылку на объект Product и его кол-во добавляем в коллекцию Map текущей сделки
			allproducts.add(pr); //ссылку на объект Product добавляем в общую коллекцию Products (всех сделок)
			System.out.println("----------------------------------");
			
			if(!continueInputQuestion("один продукт")) break; //выход с цикла если не хотим больше новых продуктов  
			
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
				
				if (!continueInputQuestion("пару Key - Value")) break;
				
			}
				
			parties.setName(partyName);
			parties.setAddress(partyAddress);
						
			return parties;
	}
	
	private Product inputProduct(){
		
		int choiseProduct = 0;
		String typeProducts; 
		int previousProduct = 2; // по умолчанию значение 2 - продукт будет вводится по-новой, если 1 - то используется продукт, который был введен ранее
		Product pr = null; //заглушка, чтобы обращение ниже а-ля pr.setTitle(title) не выбивало ошибку в Эклипсе
			
		System.out.println("---->>>");
		System.out.println("Ввод нового продукта текущей сделки.");
		
		//Блок выбора продукта - старый продукт выбираем или новый вводим
		if (!allproducts.isEmpty()){                                  //Сначала проверяем не пустой ли список продуктов
			
			System.out.println("Будет использоваться ранее введенный продукт? Введите: 1-Да, 2-Нет");
			previousProduct = Integer.valueOf(keyboard("aa", false));
			
			//Блок выбора существующего продукта
			if (previousProduct == 1) {
				System.out.println("Выберите из списка номер ранее введенного продукта:");
				ListIterator <Product> itr = allproducts.listIterator();
				
				while (itr.hasNext()){
					
					pr = itr.next();
					if (pr.getType_of_product() == 1) typeProducts = "Фотоаппарат";
					else typeProducts = "Ботинки";
					System.out.println("Продукт №" + (choiseProduct + 1) + ": " + typeProducts + "  Title: " + pr.getTitle() + "  Price: " + pr.getPrice());
					choiseProduct++;				
				}
				System.out.println("Ваш выбор:");
				choiseProduct = Integer.valueOf(keyboard("aa", false));
				//Проверка что номер выбора ранее веденного продукта корректен
				if (choiseProduct > allproducts.size() || choiseProduct <= 0) {
					
					System.err.println("Неизвестный продукт!");
					System.exit(-1);
				}
				
				//Добавляем ссылку на ранее введенный продукт
				pr = allproducts.get(choiseProduct - 1);
								
			} else if (previousProduct != 2) {
				
				System.err.println("Номер введенного выбора не корректен!");
				System.exit(-1);
			}
				
		}

		   //Блок ввода нового продукта
		   if (previousProduct == 2){
						
	         System.out.print("Сделка по купле/продаже: -фотиков (нажмите 1) или -ботинок (нажмите 2)");
		     int type_of_product = Integer.valueOf(keyboard("aa", false)); //тип продукта - 1 - фотики или 2- ботинки 
		
		       if (type_of_product == 1){
			
			FotoProduct fotoPr = new FotoProduct();
			String megapx = keyboard("кол-во мегапикселей фотоаппарата", true);
			int digital = Integer.valueOf(keyboard("тип фотоаппарата: 1 - цифровой, 0- пленочный", true));
			fotoPr.setMegapx(Double.valueOf(megapx));
			fotoPr.setDigital(BooleanUtils.toBoolean(digital));
			fotoPr.setType_of_product(1);
			
			pr = fotoPr;
			
	    	}else if (type_of_product == 2){
			
			BotinkiProduct botinkiPr = new BotinkiProduct();
			String size = keyboard("размер ботинок", true);
			String color = keyboard("цвет ботинок", true);
			botinkiPr.setSize(Integer.valueOf(size));
			botinkiPr.setColor(color);
			botinkiPr.setType_of_product(2);
			
			pr = botinkiPr;
			
		    }else{
			System.err.println("Неизвестный продукт!");
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
		
		if (visible) System.out.print("Введите пожалуйста " + message + ": "); 
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
		
		Deal d = new Deal(seller, buyer); //в одной сделке 3 обьекта - Party: buyer,seller,  и Product: массив объектов Product
		
		//Product information
		
		FotoProduct fotoPr = new FotoProduct();
		
		fotoPr.setMegapx(Double.valueOf("16"));
		fotoPr.setDigital(BooleanUtils.toBoolean(1));
		fotoPr.setType_of_product(1);
		
		Product pr = fotoPr;
		
		String title = "Чобиток";
	    String priceStr = "650";
	    pr.setTitle(title);
	    pr.setPrice(Double.valueOf(priceStr));		
	
	    String quantityStr = "3";	
	    quantity = Integer.valueOf(quantityStr);
			
	    d.getProducts().put(pr, quantity); // ссылку на объект Product и его кол-во добавляем в коллекцию Map текущей сделки
	
	    deals.getDeals().add(d); //добавляем сделку в коллекцию сделок
		
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
