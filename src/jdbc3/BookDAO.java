package library3;

import java.sql.*;
import java.util.*;

public class BookDAO {
	static Scanner sc = new Scanner(System.in);

	// Insert Book
	public static void insertBook() {
		try (Connection con = ConnectionProvider.getConnection()) {
			System.out.print("Enter Id: ");
			int id = sc.nextInt();

			sc.nextLine();

			System.out.print("Enter title: ");
			String title = sc.nextLine();

			System.out.print("Enter author: ");
			String author = sc.nextLine();

			System.out.print("Enter price: ");
			int price = sc.nextInt();

			System.out.print("Enter quantity: ");
			int quantity = sc.nextInt();

			String q = "INSERT INTO books (id, title, author, price, quantity) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, author);
			pstmt.setInt(4, price);
			pstmt.setInt(5, quantity);

			pstmt.executeUpdate();
			System.out.println("Book Added Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// View All Book
	public static void viewAllBook() {
		try (Connection con = ConnectionProvider.getConnection()) {
			String q = "SELECT * FROM books";
			PreparedStatement pstmt = con.prepareStatement(q);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("\n--- Book List ---");
				System.out.println(
					    "ID: " + rs.getInt("id") +
					    ", Title: " + rs.getString("title") +
					    ", Author: " + rs.getString("author") +
					    ", Price: " + rs.getInt("price") +
					    ", Quantity: " + rs.getInt("quantity")
					);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// View Book By Id
	public static void bookById () {
		try (Connection con = ConnectionProvider.getConnection()) {
			System.out.print("Enter Book Id: ");
			int id = sc.nextInt();
			
			String q = "SELECT * FROM books WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
	            System.out.println(
	                "ID: " + rs.getInt("id") +
	                ", Title: " + rs.getString("title") +
	                ", Author: " + rs.getString("author") +
	                ", Price: " + rs.getInt("price") +
	                ", Quantity: " + rs.getInt("quantity")
	            );
	        } else {
	            System.out.println("Book Not Found");
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Update Book
	public static void updateBook () {
		try (Connection con = ConnectionProvider.getConnection()){
			
			System.out.print("Enter new price: ");
			int price = sc.nextInt();
			
			System.out.print("Enter new quantity: ");
			int quantity = sc.nextInt();
			
			System.out.print("Enter Book Id: ");
			int id = sc.nextInt();

			String q = "UPDATE books SET price = ?, quantity = ? WHERE id = ?";

			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, price);
			pstmt.setInt(2, quantity);
			pstmt.setInt(3, id);
			
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("Book Updated Successfully!");
			}else {
				System.out.println("Book Not Found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete Book
	public static void deleteBook () {
		try (Connection con = ConnectionProvider.getConnection()){
			System.out.print("Enter Id: ");
			int id = sc.nextInt();
			String q = "DELETE FROM books WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("Book Deleted Suscessfully");
			}else {
				System.out.println("Book Not Found");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Check Availability
	public static void availableBook () {
		try (Connection con = ConnectionProvider.getConnection()){
			System.out.print("Enter Id: ");
			int id = sc.nextInt();
			
			System.out.print("Enter Quantity: ");
			int reqQuantity = sc.nextInt();
			
			String q = "SELECT quantity FROM books WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int available = rs.getInt("quantity");
				if (available >= reqQuantity) {
					available -= reqQuantity;
					System.out.println(" Book issued successfully!");
                } else {
                    System.out.println(" Insufficient stock. Available: " + available);
                }
			}else {
				System.out.println("Book Not Found");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}

