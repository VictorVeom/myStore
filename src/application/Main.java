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
			String productName = order.addName();
	        double productPrice = order.addPrice();

			
			Product product = new Product(productName, productPrice);
	        OrderItem items = new OrderItem(order.addQuantity(), productPrice, product);
	         
	        order.addItem(items);
		}
		 order.printSummary();
		
		int opc = 0;
        while (opc < 2){
            System.out.print("Enter with: \n1 Edit quantity, 2 Remove item");
            opc = sc.nextInt();
            switch(opc){
                case 1:
                    OrderItem t = order.searchItems(order.addName());
                    if (t != null) {
                        System.out.println("Update: ");
                        t.setQuantity(order.addQuantity());
                    }else {
                        System.out.println("Name of product not exist, try again");
                    }
                    System.out.println("Update list Product");
                    break;
                case 2:
                    System.out.print("Remove ");
                    order.removeItem(order.addName());
                    opc = 0;
                default:
                	order.printSummary();
                    break;
            }
        }
        
		
		System.out.print("Enter your credit card: ");
		String card = sc.next();
		order.validationCard(card);
		
		
		sc.close();
		
	}

}
