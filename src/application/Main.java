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
		OrderStatus status = OrderStatus.valueOf("PEDDING_PAYMENT");
		
		Order order = new Order(new Date(), status, client);
		
		int opc = 0;
        while (opc <= 4){
            System.out.print("Enter: \n1 Add item, 2 Remove item, 3 Edit quantity, 4 Edit price or other number to payment ");
            opc = sc.nextInt();
            switch(opc){
            	case 1:
            		System.out.println("Enter order data:");
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
            		 break;
                case 2:
                    System.out.print("Remove ");
                    order.removeItem(order.addName());
                    System.out.println("Updated Product List");
                    order.printSummary();
                    break;
                case 3:
                    OrderItem t = order.searchItems(order.addName());
                    if (t != null) {
                    	System.out.print("Edit ");
                        t.setQuantity(order.addQuantity());
                        System.out.println("Updated Product List");
                        order.printSummary();
                    }else {
                        System.out.println("Product name does not exist, try again");
                    }
                    break;
                case 4:
                	t = order.searchItems(order.addName());
                	if (t != null) {
                		System.out.println("Edit ");
                		t.setPrice(order.addPrice());
                		System.out.println("Updated Product List");
                        order.printSummary();
                	}else {
                        System.out.println("Product name does not exist, try again");
                    }
                	break;
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
