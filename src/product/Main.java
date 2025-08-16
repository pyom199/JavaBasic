package product;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		ProductsManager manager = new ProductsManager();

		manager.addProduct(new Product(1, "冷蔵庫", 50000, 10));
		manager.addProduct(new Product(2, "ソファ", 30000, 5));
		manager.addProduct(new Product(3, "米", 2000, 3));
		manager.addProduct(new Product(4, "小説", 1500, 4));
		manager.addProduct(new Product(5, "Tシャツ", 1500, 5));

		System.out.println("---商品を5つ追加して全てを表示する---");
		manager.displayAllProducts();
		manager.removeProduct(1);

		System.out.println("\n---商品を一つ削除して全てを表示する---");
		manager.displayAllProducts();

		Product found = manager.getProductByName("米");
		System.out.println("\n---商品名「米」の情報を表示する---");
		System.out.println(found);

		DiscountedProduct sofa = new DiscountedProduct(2, "ソファ", 30000, 5, 0.3);
		System.out.println("\n---商品名「ソファ」の情報と割引率30％の情報を表示する---");
		System.out.println(sofa);

		//forが必要な理由はリスト内の要素を取り出して処理するため
		List<Product> results = manager.search("Tシャツ");
		System.out.println("\n---商品名「Tシャツ」を検索して表示する---");
		for (Product product : results) {
			System.out.println(product);
		}
	}
}
