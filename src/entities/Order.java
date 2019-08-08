package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.enums.OrderStatus;

public class Order {
	Scanner sc = new Scanner(System.in);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Date date;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> items = new ArrayList<>();
	public Order(Date date, OrderStatus status, Client client) {
		this.date = date;
		this.status = status;
		this.client = client;
	}
	public Date getDate() {
		return date;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<OrderItem> getOrdemItem() {
		return items;
	}
	
	public String addName() {
		System.out.print("Product name: ");
		String debug = sc.nextLine();
		return debug;
		
	}
	
	public double addPrice() {
		System.out.print("Product price: ");
		double price = sc.nextDouble();
		return price;
	}
	
	public int addQuantity() {
		System.out.print("Product quantity: ");
		int quantity = sc.nextInt();
		 sc.nextLine();
		return quantity;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(String name) {
		OrderItem aux = items.stream().filter(x -> x.getProduct().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		items.remove(aux);
	}
	
	public void printSummary() {
		System.out.println("ORDER SUMMARY:");
		System.out.println();
		System.out.printf("Data: %s\nStatus: %s\nClient: %s, %s, %s\n", getDate(), getStatus(), client.getName(), sdf.format(client.getBirhDate()), client.getEmail());
		System.out.println("Ordem items : ");
		getAllItems();
		System.out.println("Total Price : " + total());
		
	}
	
	public OrderItem searchItems(String name) {
		OrderItem aux = items.stream().filter(x -> x.getProduct().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return aux;
	}
	
	public void getAllItems() {
		for (OrderItem item : items) {
			System.out.print("Name: " + item.getProduct().getName() + "\nPrice: " + item.getPrice() + "\nQuantity: " + item.getQuantity() + "\n\n");
		}
	}
	
	public void validationCard(String card) {
		 int sum = 0;
         boolean alternate = false;
         for (int i = card.length() - 1; i >= 0; i--)
         {
                 int n = Integer.parseInt(card.substring(i, i + 1));
                 if (alternate)
                 {
                         n *= 2;
                         if (n > 9)
                         {
                                 n = (n % 10) + 1;
                         }
                 }
                 sum += n;
                 alternate = !alternate;
         }
         if (sum % 10 == 0) {
        	 this.status =  OrderStatus.valueOf("APPROVED");
        	 System.out.println("Valid\nStatus " + this.status);
         }else {
        	 this.status =  OrderStatus.valueOf("DENIED");
        	 System.out.println("Invalid\nStatus: "+ this.status);
         }
	}
	
	public double total() {
		double sum = 0.0;
		
		for(OrderItem item : items) {
			sum += item.subTotal();
		}
	
		return sum;
	}
	
	

}
