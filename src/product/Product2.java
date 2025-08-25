package product;

public class Product2 {
	private int id;
	private String name;
	private int price;
	private int stock;

	public Product2(int id, String name, int price, int stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return String.format("ID: %d, 名前: %s, 価格: %d円, 在庫: %d個", id, name, price, stock);
	}
}
