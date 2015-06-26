package simple_deals_collections_jaxb;

import javax.xml.bind.annotation.XmlElement;

public class BotinkiProduct extends Product {
	
	protected int size;
	protected String color;
	
	//--------------------------------------------геттеры и сеттеры для полей класса------------
	
	@Override
	public int getSize() {
		return size;
	}
	@XmlElement
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String getColor() {
		return color;
	}
	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

}
