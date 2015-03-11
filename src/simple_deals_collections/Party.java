package simple_deals_collections;

import java.util.LinkedHashMap;
import java.util.Map;

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

	/**
	 * @return the keyValues
	 */
	public Map<String, String> getKeyValues() {
		return keyValues;
	}

}
