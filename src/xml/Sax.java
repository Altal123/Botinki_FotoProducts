package xml;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//Поиск в XML-документе с помощью SAX-парсера поля worker c аттрибутом ID='2'

public class Sax {

	public static void main(String[] args) throws SAXException {
		
		try{
	
			SAXParserFactory factory = SAXParserFactory.newInstance(); //создаем фабрику

			SAXParser p = factory.newSAXParser(); //создаем парсер
			
			DefaultHandler handler = new DefaultHandler(){
				
//				boolean departments = false;
//				boolean department = false;
//				boolean name = false;
				boolean workerWitnId2 = false;
//				boolean nickname = false;
//				boolean salary = false;
				
			public void startElement(String uri, String localName,String qName, // Method called at the start of
		                Attributes attributes) {                                // each open tags of XML document.
				
//				System.out.println(qName);
				
//				System.out.println(attributes.getQName(0));
//				System.out.println(attributes.getValue(0));
					
					if (qName.equalsIgnoreCase("WORKER") && attributes.getLength() > 0){
						if (attributes.getQName(0).equalsIgnoreCase("id") && attributes.getValue(0).equalsIgnoreCase("2"))
						workerWitnId2 = true;
					}			 		
					
					
				}
                      
			
//			public void endElement(String uri, String localName,                   Method called at the end of
//            Attributes attributes) {                                             each closing tags of XML document.
//					String qName){
//			 
//					System.out.println("End Element :" + qName);
//			 
//				}
			
			public void characters(char ch[], int start, int length) throws SAXException {  //Method called with the text contents in between the start and end tags of an XML document element.
					 
					if (workerWitnId2) {
						System.out.println("Workers Name : " + new String(ch, start, length));
						workerWitnId2 = false;
					}
					
			}
			
	};
	
	p.parse("D:\\Sasha\\My_JS_Project\\java\\Hello\\src\\xml\\department.xml", handler);

		 } catch (Exception e) {
		       e.printStackTrace();
		     }
	
 }
		
		
}
