package simple_deals_collections_jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType (XmlAccessType.PROPERTY)
public class BotinkiProduct extends Product {
	
	protected int size;
	protected String color;
	
	//--------------------------------------------геттеры и сеттеры для полей класса------------
	
	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
