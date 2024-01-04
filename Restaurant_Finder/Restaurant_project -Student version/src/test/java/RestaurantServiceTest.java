import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
    	Restaurant restaurant = service.findRestaurantByName("Amelie's cafe");
		assertTrue(service.getRestaurants().contains(restaurant));
		assertNotNull(restaurant);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
    	Restaurant restaurant = service.findRestaurantByName("Gulati's Restaurant");
		assertFalse(service.getRestaurants().contains(restaurant));
		assertNull(restaurant);
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
    	RestaurantService spiedrestauraService = Mockito.spy(service);
    	
        int initialNumberOfRestaurants = spiedrestauraService.getRestaurants().size();
        spiedrestauraService.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, spiedrestauraService.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
    	RestaurantService spiedrestauraService = Mockito.spy(service);

    	spiedrestauraService.findRestaurantByName("Pantry d'or");
//        assertThrows(restaurantNotFoundException.class,()->spiedrestauraService.removeRestaurant("Pantry d'or"));
    	assertEquals(restaurantNotFoundException.class,spiedrestauraService.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
    	RestaurantService spiedrestauraService = Mockito.spy(service);

        int initialNumberOfRestaurants = spiedrestauraService.getRestaurants().size();
        spiedrestauraService.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,spiedrestauraService.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}