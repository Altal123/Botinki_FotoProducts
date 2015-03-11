package simple_deals;

public class FotoProduct extends Product {
	
	private static final int DISCOUNT_FOR_NON_DIGITAL = 20;
	private double megapx;
	private boolean digital;
	
	//--------------------------------------------геттеры и сеттеры для полей класса------------
	public boolean isDigital() {
		return digital;
	}

	public void setDigital(boolean digital) {
		this.digital = digital;
	}

	public double getMegapx() {
		return megapx;
	}

	public void setMegapx(double megapx) {
		this.megapx = megapx;
	}

	//--------------------------------------------геттеры и сеттеры для полей класса------------
	
	protected int getDiscount(){
		
		int def = super.getDiscount();
		
		if (!digital) def += DISCOUNT_FOR_NON_DIGITAL;
		else return def;
		return def;
		
	}

}
