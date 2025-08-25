package product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager2 {

	private List<Product> productList = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private int nextId = 1;

	public void run() {
		while (true) {
			System.out.println("--メニュー--");
			System.out.println("1:商品追加");
			System.out.println("2:商品削除");
			System.out.println("3:商品情報取得");
			System.out.println("4:商品検索");
			System.out.println("5:商品全て表示");
			System.out.println("0:終了");
			System.out.print("\nメニューから操作を選択してください：");

			String choice = scanner.nextLine().trim();

			switch (choice) {
			case "1":
				addProduct();
				break;
			case "2":
				deleteProduct();
				break;
			case "3":
				getProductInfo();
				break;
			case "4":
				searchProduct();
				break;
			case "5":
				displayAllProducts();
				break;
			case "0":
				System.out.println("終了します");
				return;
			default:
				System.out.println("無効な入力です。");
			}

			System.out.println();
		}
	}

	private void addProduct() {
		try {
			System.out.println("商品ID: " + nextId);
			String name = inputName();
			int price = inputPrice();
			int stock = inputStock();

			Product p = new Product(nextId, name, price, stock);
			productList.add(p);
			System.out.println("商品を追加しました: " + p);
			nextId++;
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("\tat " + e.getStackTrace()[0]);
		}
	}

	private void deleteProduct() {
		try {
			System.out.print("削除する商品のIDを入力してください：");
			int id = Integer.parseInt(scanner.nextLine().trim());
			boolean removed = productList.removeIf(p -> p.getId() == id);
			if (removed) {
				System.out.println("商品ID " + id + " を削除しました。");
			} else {
				System.out.println("商品ID " + id + " は見つかりませんでした。");
			}
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("\tat " + e.getStackTrace()[0]);
		}
	}

	private void getProductInfo() {
		try {
			System.out.print("商品名を入力してください：");
			String name = scanner.nextLine().trim();
			Product found = productList.stream()
					.filter(p -> p.getName().equals(name))
					.findFirst()
					.orElse(null);
			if (found != null) {
				System.out.println("商品情報: " + found);
			} else {
				System.out.println("該当する商品がありません。");
			}
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("\tat " + e.getStackTrace()[0]);
		}
	}

	private void searchProduct() {
		try {
			System.out.print("検索キーワードを入力してください：");
			String keyword = scanner.nextLine().trim();
			List<Product> results = new ArrayList<>();
			for (Product p : productList) {
				if (p.getName().contains(keyword)) {
					results.add(p);
				}
			}
			if (results.isEmpty()) {
				System.out.println("該当する商品はありません。");
			} else {
				System.out.println("検索結果:");
				for (Product p : results) {
					System.out.println(p);
				}
			}
		} catch (Exception e) {
			System.out.println("無効な入力です。");
			e.printStackTrace();
		}
	}

	private void displayAllProducts() {
		try {
			if (productList.isEmpty()) {
				System.out.println("商品は登録されていません。");
				return;
			}
			System.out.println("全商品リスト:");
			for (Product p : productList) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println("無効な入力です。");
			e.printStackTrace();
		}
	}

	private String inputName() throws Exception {
		System.out.print("商品名を入力してください：");
		String name = scanner.nextLine().trim();
		if (name.isEmpty()) {
			System.out.println("無効な入力です。入力された商品名：" + name);
			throw new Exception("無効な入力です。商品名を正しく入力してください。");
		}
		return name;
	}

	private int inputPrice() throws Exception {
		System.out.print("価格を入力してください：");
		String input = scanner.nextLine().trim();
		int price = Integer.parseInt(input);
		if (price < 0) {
			System.out.println("無効な入力です。入力された価格：" + price);
			throw new Exception("無効な入力です。価格を正しく入力してください。");
		}
		return price;
	}

	private int inputStock() throws Exception {
		System.out.print("在庫数を入力してください：");
		int stock = Integer.parseInt(scanner.nextLine().trim());
		if (stock < 0) {
			System.out.println("無効な入力です。入力された在庫数：" + stock);
			throw new Exception("無効な入力です。在庫を正しく入力してください。");
		}
		return stock;
	}
}
