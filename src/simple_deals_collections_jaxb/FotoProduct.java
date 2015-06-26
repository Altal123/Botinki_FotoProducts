package simple_deals_collections_jaxb;

import javax.xml.bind.annotation.XmlElement;

public class FotoProduct extends Product {
	
	private static final int DISCOUNT_FOR_NON_DIGITAL = 20; //������ ��� ��������� �������
	private double megapx;
	private boolean digital;
		
	//--------------------------------------------������� � ������� ��� ����� ������------------
	@Override
	public boolean isDigital() {
		return digital;
	}

	@XmlElement
	public void setDigital(boolean digital) {
		this.digital = digital;
	}
	
	@Override
	public double getMegapx() {
		return megapx;
	}

	@XmlElement
	public void setMegapx(double megapx) {
		this.megapx = megapx;
	}

	//--------------------------------------------������� � ������� ��� ����� ������------------
	@Override
	protected int getDiscount(int quantity){
		
		int def = super.getDiscount(quantity);
		
		if (!digital) def += DISCOUNT_FOR_NON_DIGITAL;
		return def;
		
	}

}
