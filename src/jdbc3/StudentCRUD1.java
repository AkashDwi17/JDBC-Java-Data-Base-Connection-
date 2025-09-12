package jdbc3;
import java.sql.*;
import java.util.*;

public class StudentCRUD1 {
	static Scanner sc = new Scanner (System.in);
	
	public static void insertStudent () {
		
		// insert
		
		try (Connection con = ConnectionProvider1.getConnection()) {
			System.out.print("Enter Id: ");
			int id = sc.nextInt();
			
			System.out.print("Enter name: ");
			String name = sc.next();
			
			System.out.print("Enter subject: ");
			String subject = sc.next();
			
			System.out.print("Enter prn: ");
			long prn = sc.nextLong();
			
			
			String q = "INSERT INTO student (id, name, subject, prn) VALUES (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(q);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, subject);
			pstmt.setLong(4, prn);
			
			pstmt.executeUpdate();
			
			System.out.println("Data Instered Suscessfully");
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updatStudent() {
		
		try (Connection con = ConnectionProvider1.getConnection()){
			
			
			System.out.print("Enter name: ");
			String name = sc.next();
			
			System.out.print("Enter subject: ");
			String subject = sc.next();
			
			System.out.print("Enter prn: ");
			long prn = sc.nextLong ();
			
			System.out.print("Enter Id: ");
			int id = sc.nextInt();
			
			String q = "UPDATE student SET name=?,  subject=?,  prn=? WHERE id=?";
			
			PreparedStatement pstmt = con.prepareStatement(q);
			
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setLong(3, prn);
			pstmt.setInt(4, id);
	
			pstmt.executeUpdate ();
			System.out.println("Update Suscessfully");
			pstmt.close();
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteStudent () {
		try (Connection con = ConnectionProvider1.getConnection()){
			System.out.print("Enter Id to Delete: ");
			int id = sc.nextInt();
			String q = "Delete FROM student  WHERE id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			System.out.println("Deleted Suscessfully");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void displayStudent (){
		try (Connection con = ConnectionProvider1.getConnection()) {
			String q = "SELECT * FROM student";
			PreparedStatement pstmt = con.prepareStatement(q);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + rs.getString("name") + rs.getString("subject") + rs.getLong("prn"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println("Insert Student");
		insertStudent();
		System.out.println("update Student");
		updatStudent();
		System.out.println("delete Student");
		deleteStudent();
		System.out.println("display Student");
		displayStudent();
	}



	
	
}
