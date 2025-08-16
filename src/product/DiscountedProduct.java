package product;

public class DiscountedProduct extends Product {

	private double discountRate; // 割引率（例：0.1 → 10%）

	public DiscountedProduct(int id, String name, int price, int stock, double discountRate) {
		super(id, name, price, stock);
		this.discountRate = discountRate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	// 割引後の価格を計算するメソッド
	public int calculateDiscountedPrice() {
		return (int) (getPrice() * (1 - discountRate)); //定価×（1-割引率）intで整数の結果に
	}

	public String toString() {
		return super.toString() + ", 割引率=" + discountRate + ", 割引後価格=" + calculateDiscountedPrice();
	}
}
