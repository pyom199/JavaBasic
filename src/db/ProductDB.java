package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductDB {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/product_management";
		String user = "root";
		String password = "42wwwwww";

		Scanner scanner = new Scanner(System.in);//Scanner を使ってユーザーから商品情報を入力（名前、価格、在庫、カテゴリID）

		// ----- パート1：商品の登録 -----
		System.out.println("--商品の登録--\n");

		System.out.print("商品名を入力してください：");
		String name = scanner.nextLine();

		System.out.print("価格を入力してください：");
		int price = Integer.parseInt(scanner.nextLine());

		System.out.print("在庫数を入力してください：");
		int stock = Integer.parseInt(scanner.nextLine());

		System.out.print("カテゴリーIDを入力してください：");
		int categoryId = Integer.parseInt(scanner.nextLine());

		String insertSql = "INSERT INTO products (name, price, stock, category_id) VALUES (?, ?, ?, ?)";//SQL文 INSERT INTO products (...) VALUES (?, ?, ?, ?) を準備

		try (Connection conn = DriverManager.getConnection(url, user, password); //PreparedStatement に値をセット
				PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setInt(3, stock);
			pstmt.setInt(4, categoryId);

			int rowsInserted = pstmt.executeUpdate();//executeUpdate() を呼び出してデータベースに追加

			System.out.println();
			System.out.println("登録成功件数： " + rowsInserted + "件");//結果として、登録成功件数と登録された商品情報を表示
			System.out.println("登録内容：");
			System.out.println("商品名：" + name + "、価格：" + price + "、在庫数：" + stock + "、カテゴリーID：" + categoryId);

		} catch (SQLException e) {
			System.err.println("登録時にデータベースエラーが発生しました: " + e.getMessage());//	例外が起きた場合は catch 節でメッセージを表示
		}

		// ----- パート2：商品の更新（価格と在庫） -----
		System.out.println("\n--商品の価格と在庫を更新--");//「商品ID」「新しい価格」「新しい在庫数」を入力

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.print("商品IDを入力してください：");
			int productId = Integer.parseInt(scanner.nextLine());

			System.out.print("価格を入力してください：");
			int newPrice = Integer.parseInt(scanner.nextLine());

			System.out.print("在庫数を入力してください：");
			int newStock = Integer.parseInt(scanner.nextLine());

			String updateSql = "UPDATE products SET price = ?, stock = ? WHERE id = ?";//UPDATE SQL文を使って、指定された id の商品を更新する

			try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
				pstmt.setInt(1, newPrice);
				pstmt.setInt(2, newStock);
				pstmt.setInt(3, productId);

				int rowsUpdated = pstmt.executeUpdate();

				System.out.println();
				System.out.println("更新成功件数： " + rowsUpdated + "件");//更新が成功した場合は件数と内容を表示し、失敗した場合は失敗と表示

				if (rowsUpdated > 0) {
					System.out.println("更新内容：");
					System.out.println("商品ID: " + productId + "、価格: " + newPrice + "、在庫数: " + newStock);
				} else {
					System.out.println("更新失敗");
				}
			}

		} catch (SQLException e) {
			System.err.println("更新時にデータベースエラーが発生しました: " + e.getMessage());
		} //例外（入力エラーやDB接続エラー）をキャッチして適切に処理する

		// ----- パート3：商品の削除（カテゴリID指定） -----
		System.out.println("\n--商品の削除（カテゴリーID指定）--");//削除対象の「カテゴリーID」を入力

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.print("削除するカテゴリーIDを入力してください：");
			int deleteCategoryId = Integer.parseInt(scanner.nextLine());

			String deleteSql = "DELETE FROM products WHERE category_id = ?";//SQL文 DELETE FROM products WHERE category_id = ? を実行

			try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
				pstmt.setInt(1, deleteCategoryId);

				int rowsDeleted = pstmt.executeUpdate();

				System.out.println();
				System.out.println("削除成功件数： " + rowsDeleted + "件");//削除された件数を表示（成功 or 対象なし）

				if (rowsDeleted > 0) {
					System.out.println("カテゴリーID " + deleteCategoryId + " の商品を削除しました。");
				} else {
					System.out.println("該当する商品は見つかりませんでした。");
				}
			}
		} catch (SQLException e) {
			System.err.println("削除時にデータベースエラーが発生しました: " + e.getMessage());//例外が発生した場合は、メッセージを表示して処理終了
		}

		// ----- 全商品表示 -----
		System.out.println("\n--productsテーブルの全ての商品情報を表示--\n");

		String selectSql = "SELECT id, name, price, stock, category_id FROM products";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement stmt = conn.prepareStatement(selectSql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String productName = rs.getString("name");
				int productPrice = rs.getInt("price");
				int productStock = rs.getInt("stock");
				int productCategoryId = rs.getInt("category_id");

				System.out.println("id: " + id);
				System.out.println("name: " + productName);
				System.out.println("price: " + productPrice);
				System.out.println("stock: " + productStock);
				System.out.println("category_id: " + productCategoryId);
				System.out.println();
			}

		} catch (SQLException e) {
			System.err.println("一覧表示時にデータベースエラーが発生しました: " + e.getMessage());
		}

		scanner.close();
	}
}
