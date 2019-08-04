package application;

import java.util.Scanner;

import entities.Client;
import entities.OrderItem;
import entities.Order;
import entities.Product;
import entities.enums.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Main {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter Client data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.println("Email: ");
		String email = sc.next();
		
		System.out.println("Birth date (DD/MM/YYYY): ");
		Date birhDate = sdf.parse(sc.next());
		
		Client client = new Client(name, email, birhDate);
		
		System.out.println("Enter order data:");
		OrderStatus status = OrderStatus.valueOf("PEDDING_PAYMENT");
		
		Order order = new Order(new Date(), status, client);
		
		System.out.println("How many items to this order? ");
		int n = sc.nextInt();
		
		for(int i=1; i<=n ; i++) {
			System.out.println("Enter #" + i + " item data:");
			System.out.println("Product name: ");
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
		
		System.out.println("ORDER SUMMARY:");
		System.out.println();
		System.out.printf("Data: %s\nStatus: %s\nClient: %s, %s, %s\n", order.getDate(), order.getStatus(), client.getName(), sdf.format(client.getBirhDate()), client.getEmail());
		System.out.println("Ordem items : ");
		order.getAllItems();
		System.out.println("Total Price : " + order.total());
		
		
		sc.close();
		
	}

}
