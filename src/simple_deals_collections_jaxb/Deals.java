package simple_deals_collections_jaxb;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "deals")
@XmlAccessorType (XmlAccessType.FIELD)
public class Deals {
	
	@XmlElement(name = "deal")
	private Collection <Deal> deals = null;


	public Collection<Deal> getDeals() {
		return deals;
	}


	public void setDeals(ArrayList<Deal> deals) {
		this.deals = deals;
	}

}
