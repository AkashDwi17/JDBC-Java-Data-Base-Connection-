package jdbc3;

import java.sql.*;
import java.util.Scanner;

public class StudentCRUD {

    // 🔹 Create (Insert)
    public static void insertStudent(int id, String name, String course, float fee) {
        try (Connection con = ConnectionProvider.getConnection()) {
            String q = "INSERT INTO students (id, name, course, fee) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setFloat(4, fee);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student inserted successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Read (Select)
    public static void getStudents() {
        try (Connection con = ConnectionProvider.getConnection()) {
            String q = "SELECT * FROM students";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            System.out.println("📌 Student Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Course: " + rs.getString("course") +
                        ", Fee: " + rs.getFloat("fee"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Update
    public static void updateStudent(int id, String name, String course, float fee) {
        try (Connection con = ConnectionProvider.getConnection()) {
            String q = "UPDATE students SET name=?, course=?, fee=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, name);
            pstmt.setString(2, course);
            pstmt.setFloat(3, fee);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student updated successfully.");
            } else {
                System.out.println("⚠️ Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Delete
    public static void deleteStudent(int id) {
        try (Connection con = ConnectionProvider.getConnection()) {
            String q = "DELETE FROM students WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student deleted successfully.");
            } else {
                System.out.println("⚠️ Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Main method for testing
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Student CRUD Menu ===");
            System.out.println("1. Insert Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter Fee: ");
                    float fee = sc.nextFloat();
                    insertStudent(id, name, course, fee);
                    break;

                case 2:
                    getStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter New Course: ");
                    String ucourse = sc.nextLine();
                    System.out.print("Enter New Fee: ");
                    float ufee = sc.nextFloat();
                    updateStudent(uid, uname, ucourse, ufee);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    deleteStudent(did);
                    break;

                case 5:
                    System.out.println("🚪 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }
}
