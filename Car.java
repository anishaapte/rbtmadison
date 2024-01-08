
/**
 * This interface defines a single car and exposes car properties 
 * required by the frontend 
 * @author anishaapte
 *
 */
public interface Car extends Comparable<Car> {
  
  //Constructor would look like this :
  //public car(String brand, String model, int year, double price, float mileage)
  //Will also implement compareTo method to compare 2 cars based on mileage as the key
  //This comparison is required for all BST operations
  //public int compareTo(Car c) {
       
 /**
 * Getter method for the brand of the car 
 * @return - the brand of the car
 */
public String getBrand();
  
 /**
 * Getter method for the model of the car 
 * @return - the model of the car
 */
public String getModel();
  
 /**
 * Getter method for the year of the car 
 * @return - the year of the car
 */
public int getYear();
  
 /**
 * Getter method for the price of the car
 * @return - the price of the car
 */
public float getPrice();
  
 /**
 * Getter method for the mileage of the car 
 * @return - the mileage of the car
 */
public float getMileage();
}
