package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDB {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ product_management ";
		String user = "root";
		String password = "42wwwwww";

		String query = "SELECT id, name, price, stock, category_id FROM products";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			System.out.println("--productsテーブルの全ての商品情報を表示--\n");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				int categoryId = rs.getInt("category_id");

				System.out.println("id: " + id);
				System.out.println("name: " + name);
				System.out.println("price: " + price);
				System.out.println("stock: " + stock);
				System.out.println("category_id: " + categoryId);
				System.out.println();//商品と商品の空白用
			}

		} catch (SQLException e) {
			System.err.println("MySQLデータベースエラーが発生しました: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
		}
	}
}
