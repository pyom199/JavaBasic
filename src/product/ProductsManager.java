package product;

import java.util.ArrayList;
import java.util.List;

public class ProductsManager implements Searchable {
	private List<Product> productList;

	public ProductsManager() {
		productList = new ArrayList<>();
	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public void removeProduct(int id) {
		productList.removeIf(product -> product.getId() == id);
	}

	public Product getProductByName(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	public void displayAllProducts() {
		for (Product product : productList) {
			System.out.println(product);
		}
	}

	//Overrideは親クラスやインターフェースで定義されたメソッドをがあるときに利用

	@Override
	public List<Product> search(String keyword) {
		List<Product> results = new ArrayList<>();
		for (Product product : productList) {
			if (product.getName().contains(keyword)) {
				results.add(product);
			}
		}
		return results;
	}
}