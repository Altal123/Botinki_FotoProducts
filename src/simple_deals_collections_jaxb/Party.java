package simple_deals_collections_jaxb;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class Party {
	
	private String name;
	private String address;
	Map <String, String> keyValues = new LinkedHashMap <String, String>();

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the keyValues
	 */
	public Map<String, String> getKeyValues() {
		return keyValues;
	}

}
