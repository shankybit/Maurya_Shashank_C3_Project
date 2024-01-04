import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {

	RestaurantService restaurantService = new RestaurantService();
	List<Restaurant> restaurants = restaurantService.getRestaurants();
	Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws itemNotFoundException, restaurantNotFoundException{
		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		
		restaurants.add(new Restaurant("Gulati's Restaurant","Noida",LocalTime.of(10,00,00),LocalTime.of(23,00,00)));

		restaurants.add(new Restaurant("Indian Coffee", "Andheri East Mumbai", LocalTime.of(10,00,00), LocalTime.of(22,00,00)));
    	restaurants.add(new Restaurant("Sagar Ratna", "Mulund", LocalTime.of(11,00,00), LocalTime.of(23,00,00)));
//    	restaurants.add(new Restaurant("Amelie's cafe","Chennai",LocalTime.of(10,30,00),LocalTime.of(22,00,00)));
    	
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the restaurant to be found");
		String restaurantName1 = scan.next();
		String restaurantName2 = scan.next();
		
		String restaurantName = restaurantName1+" "+restaurantName2;
		
		try {
			restaurantService.findRestaurantByName(restaurantName).displayDetails();
		}catch (Exception restaurantNotFoundException) {
			// TODO Auto-generated catch block
			System.out.println("Not Found");
		}
			
		
		
		
		Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
		List<Item> menu = restaurant.getMenu();
		System.out.println("Enter the number of orders");
		int totalCost = 0;
		int input = scan.nextInt();
		int[] orders = new int[input];
		for(int i= 0; i<input;i++) {
			System.out.println("\nEnter the order number");
			orders[i] = scan.nextInt();
			Item element = menu.get(orders[i]);
			totalCost = element.getPrice() + totalCost;
			System.out.println("Total Cost: " + totalCost);
		}
		
		System.out.println("Enter the 1 to add a Restaurant and 2 to add menu and 3 to remove restaurant and 4 to remove item from menu and 5 to know if the restaurant is open");
		int option = scan.nextInt();
		if(option==1) {
			addingRestaurant();
		}else if(option == 2) {
			addingMenu();
		}else if(option == 3) {
				removingRestaurant();
			
		}else if(option == 4) {
			removingFromMenu();
		}else if(option == 5) {
			if(restaurant.isRestaurantOpen()) {
				System.out.println("OPEN");
			}else {
				System.out.println("CLOSED");
			}
		}

	    		
		}
		
	public static void addingMenu() {
		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the restaurant to add the menu to");
		String restaurantName1 = scan.next();
		String restaurantName2 = scan.next();
		
		String restaurantName = restaurantName1+" "+restaurantName2;
		Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
//		List<Item> menu = restaurant.getMenu();
		System.out.println("If wish to add menu to restaurant enter'y' for yes and 'n' for no");
		String wishToAddMenu = scan.next();
		try {
			if(wishToAddMenu.charAt(0)=='y') {
				System.out.println("Enter the menu item");
				String menuItem1 = scan.next();
				String menuItem2 = scan.next();
				String menuItem = menuItem1+" "+menuItem2;
				System.out.println("Enter the price");
				int itemPrice = scan.nextInt();
				restaurant.addToMenu(menuItem,itemPrice);
			}else if(wishToAddMenu.charAt(0)=='n') {
				System.out.println("Its okay");
			}
		}catch(Exception e) {
			System.out.println("Incorrect Input");
		}
		
		System.out.println("Added");
		for(Restaurant restauran : restaurants) {
			restauran.displayDetails();
		}
		
	}
	
	public static void addingRestaurant() {
		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Scanner scan = new Scanner(System.in);
		System.out.println("If wish to add restaurant 'y' for yes and 'n' for no");
		String wishToAddRestaurant = scan.next();
		try {
			if(wishToAddRestaurant.charAt(0)== 'y') {
				System.out.println("Enter the restaurant name of two words");
				String newRestaurantName = scan.next();
				System.out.println("Enter the restaurant city location");
				scan.next();
				String newRestaurantLocation = scan.next();
				System.out.println("Enter the restaurant opening hour");
				int openingHour = scan.nextInt();
				System.out.println("Enter the restaurant opening minutes");
				int openingMinutes = scan.nextInt();
				System.out.println("Enter the restaurant closing hour");
				int closingHour = scan.nextInt();
				System.out.println("Enter the restaurant closing minutes");
				int closingMinutes = scan.nextInt();
				restaurantService.addRestaurant(newRestaurantName,newRestaurantLocation,LocalTime.of(openingHour,openingMinutes,00),LocalTime.of(closingHour,closingMinutes,00));
			}else if(wishToAddRestaurant.charAt(0)== 'n') {
				System.out.println("okay no problem");
			}
		}catch (Exception e) {
			System.out.println("Incorrect input");
		}
		System.out.println("Added");
		restaurantService.getRestaurants();
		for(Restaurant restaurant : restaurants) {
			restaurant.displayDetails();
		}
	}
	public static void removingRestaurant() throws restaurantNotFoundException{
		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the restaurant you want to remove");
		String restaurantName1 = scan.next();
		String restaurantName2 = scan.next();
		
		String restaurantName = restaurantName1+" "+restaurantName2;
		
			restaurantService.removeRestaurant(restaurantName);
			restaurantService.getRestaurants();
			for(Restaurant restaurant : restaurants) {
				restaurant.displayDetails();
			}
	}
	
	public static void removingFromMenu() throws itemNotFoundException {
		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the restaurant to remove the item from menu");
		String restaurantName1 = scan.next();
		String restaurantName2 = scan.next();
		
		String restaurantName = restaurantName1+" "+restaurantName2;
		Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
		
		System.out.println("If wish to remove item from menu to restaurant enter'y' for yes and 'n' for no");
		String wishToRemoveFromMenu = scan.next();
		if(wishToRemoveFromMenu.charAt(0)=='y') {
			System.out.println("Enter the menu item");
			String menuItem1 = scan.next();
			String menuItem2 = scan.next();
			String menuItem = menuItem1+" "+menuItem2;
			restaurant.removeFromMenu(menuItem);
		}else if(wishToRemoveFromMenu.charAt(0)=='n') {
			System.out.println("Its okay");
		}
		List<Item> menu = restaurant.getMenu();
		for(Restaurant restauran : restaurants) {
			restauran.displayDetails();
		}
	}
	
}