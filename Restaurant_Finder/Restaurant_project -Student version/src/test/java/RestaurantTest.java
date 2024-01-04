import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
	RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    List<Restaurant> restaurants = service.getRestaurants();
    
    @BeforeEach
    public void setup() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	
    	Restaurant spiedrestaura = Mockito.spy(service.findRestaurantByName("Amelie's cafe"));
    	when(spiedrestaura.getCurrentTime()).thenReturn(LocalTime.parse("13:30:00"));
    	assertTrue(spiedrestaura.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	Restaurant spiedrestaura = Mockito.spy(service.findRestaurantByName("Amelie's cafe"));
    	when(spiedrestaura.getCurrentTime()).thenReturn(LocalTime.parse("08:30:00"));
    	assertFalse(spiedrestaura.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
    	Restaurant spiedrestaura = Mockito.spy(service.findRestaurantByName("Amelie's cafe"));
//        spiedrestaura.addToMenu("Sweet corn soup",119);
//        spiedrestaura.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = spiedrestaura.getMenu().size();
        spiedrestaura.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
    	Restaurant spiedrestaura = Mockito.spy(service.findRestaurantByName("Amelie's cafe"));
//        spiedrestaura.addToMenu("Sweet corn soup",119);
//        spiedrestaura.addToMenu("Vegetable lasagne", 269);

    	spiedrestaura.getMenu();
        int initialMenuSize = spiedrestaura.getMenu().size();
        spiedrestaura.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
    	Restaurant spiedrestaura = Mockito.spy(service.findRestaurantByName("Amelie's cafe"));
//    	spiedrestaura.addToMenu("Sweet corn soup",119);
//    	spiedrestaura.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->spiedrestaura.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}