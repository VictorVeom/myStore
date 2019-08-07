package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Main {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter Client data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
	
		System.out.print("Email: ");
		String email = sc.next();
		
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date birhDate = sdf.parse(sc.next());
		
		Client client = new Client(name, email, birhDate);
		
		System.out.println("Enter order data:");
		OrderStatus status = OrderStatus.valueOf("PEDDING_PAYMENT");
		
		Order order = new Order(new Date(), status, client);
		
		System.out.print("How many items to this order? ");
		int n = sc.nextInt();
		
		for(int i=1; i<=n ; i++) {
			System.out.println("Enter #" + i + " item data:");
			System.out.print("Product name: ");
			sc.nextLine();
			String productName = sc.nextLine();
			
			System.out.println("Product price: ");
			double productPrice = sc.nextDouble();
			
			Product product = new Product(productName, productPrice);
			
			System.out.println("Product quantity: ");
			int productQuantity = sc.nextInt();
			
			OrderItem items = new OrderItem(productQuantity, productPrice, product);
			
			order.addItem(items);
		}

		
		order.printSummary();
		
		System.out.print("Enter your credit card: ");
		String card = sc.next();
		order.validationCard(card);
		
		
		sc.close();
		
	}

}
