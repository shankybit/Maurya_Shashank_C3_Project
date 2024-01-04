
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    

    public void setMenu(List<Item> menu) {
		this.menu = menu;
	}


	public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    

    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRestaurantOpen() {
       
    	if(getCurrentTime().compareTo(openingTime) > 0 && getCurrentTime().compareTo(closingTime)<0) {
    		return true;
    	}else {
    		return false;
    	}
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
    	
    	menu.add(new Item("1.Pav Bhaji", 150));
    	menu.add(new Item("2.Chilly Paneer", 140));
    	menu.add(new Item("3.Bread Omlette", 160));
    	menu.add(new Item("4.Spring Roll", 170));
    	menu.add(new Item("5.Egg Roll", 180));
    	menu.add(new Item("6.Fried Rice", 190));
    	 return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }
    
    public void displayMenu() {
    	for(Item item: menu ) {
    		item.toString();
    	}
    }

    private Item findItemByName(String itemName){
    	Item menuItem = null;
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                menuItem = item;
        }
        return menuItem;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null) {
            throw new itemNotFoundException(itemName);
        }
        menu.remove(itemToBeRemoved);
    }
    
    public void displayDetails(){
        System.out.println("Restaurant:"+ getName() + "\n"
                +"Location:"+ getLocation() + "\n"
                +"Opening time:"+ getOpeningTime() +"\n"
                +"Closing time:"+ getClosingTime() +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
