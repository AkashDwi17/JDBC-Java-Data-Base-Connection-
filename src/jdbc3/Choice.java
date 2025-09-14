package library3;

import java.util.*;

public class Choice {
	public void printLibrary() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. Insert Book");
			System.out.println("2. View All Book");
			System.out.println("3. Serch Book");
			System.out.println("4. Update Book");
			System.out.println("5. Delete Book");
			System.out.println("6. Available Book");
			System.out.println("7. Exit");

			System.out.print("Enter Your Choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				BookDAO.insertBook();
				break;
			case 2:
				BookDAO.viewAllBook();
				break;
			case 3:
				BookDAO.bookById();
				break;
			case 4:
				BookDAO.updateBook();
				break;
			case 5:
				BookDAO.deleteBook();
				break;
			case 6:
				BookDAO.availableBook();
				break;
			default:
				System.out.println("Enter Valid Choice");
				break;
			case 7:
				return;
			}

		}

	}

}
