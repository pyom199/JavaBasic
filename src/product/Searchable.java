package product;

import java.util.List;

public interface Searchable {
	List<Product> search(String keyword);
}
