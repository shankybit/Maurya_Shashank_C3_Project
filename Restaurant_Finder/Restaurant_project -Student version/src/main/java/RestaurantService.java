

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class RestaurantService {
	
	
    private static List<Restaurant> restaurants = new ArrayList<>();
    public List<Restaurant> getRestaurants() {
//    	restaurants.add(new Restaurant("Indian Coffee", "Andheri East Mumbai", LocalTime.of(10,00,00), LocalTime.of(22,00,00)));
//    	restaurants.add(new Restaurant("Sagar Ratna", "Mulund", LocalTime.of(11,00,00), LocalTime.of(23,00,00)));
//    	restaurants.add(new Restaurant("Amelie's cafe","Chennai",LocalTime.of(10,30,00),LocalTime.of(22,00,00)));
        return restaurants;
    }
    
    public Restaurant findRestaurantByName(String restaurantName){
    	Restaurant restaura = null;
    	for(Restaurant restaurant: restaurants) {
            if(restaurantName.equals(restaurant.getName())) {
            	restaura = restaurant;
            }
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }
    	return restaura;
    }

//    List<Restaurant> restaurants = new ArrayList<>();
   
    
    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    
    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    

	
}
