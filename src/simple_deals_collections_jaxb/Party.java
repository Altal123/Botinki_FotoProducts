package simple_deals_collections_jaxb;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "party")
@XmlAccessorType (XmlAccessType.FIELD)
public class Party {
	
	private String name;
	private String address;
	Map <String, String> keyValues = new LinkedHashMap <String, String>();

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Map<String, String> getKeyValues() {
		return keyValues;
	}

}
