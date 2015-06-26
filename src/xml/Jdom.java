package xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Jdom {

	public static void main(String[] args) {
	
		 SAXBuilder builder = new SAXBuilder();
		 
		 File xmlFile = new File("D:\\Sasha\\My_JS_Project\\java\\Hello\\src\\xml\\department.xml");

		 try {
			 
				Document doc = builder.build(xmlFile);
				Element rootNode = doc.getRootElement();
				getChild(rootNode);
				 
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		 
	}
	
	private static void getChild(Element node){
				
				List list = node.getChildren();
				String attr = "";
				
				for (int i = 0; i < list.size(); i++) {	
					 
					   node = (Element) list.get(i);
					   
					   
					   if (!node.getAttributes().isEmpty()) attr = node.getAttributes().toString();
					   if (!node.getTextNormalize().isEmpty()) System.out.println(node.getName() + attr + ": " +  node.getText());
					     else System.out.println(node.getName());
					   
					   if (!node.getChildren().isEmpty()) getChild(node);
				}
	}

}
