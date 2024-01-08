import java.io.IOException;
import java.util.ArrayList;

/**
 * This interface for the backend that exposes the 
 * required functionality to the frontend. Inventory data is loaded
 * from a CSV file and stored internally
 * @author anishaapte
 *
 */
public interface BackendInterface {
    /**
     * Reads data from a CSV file, and loads it in internal data structure
     * @param fileName - name of CSV file containing inventory
     * @throws IOException - thrown if file is not found or if file has bad records
     */    
    public void loadInventory(String fileName) throws IOException;
    
    /**
     * Gets a list of cars with the minimum mileage
     * @return - an arrayList of cars containing the min mileage
     */
    public ArrayList<Car> getCarsWithMinMileage();
    
    /**
     * Gets a list of cars with mileage at or above a specified threshold 
     * @param threshold
     * @return - an arrayList of cars with mileage at or above a specified threshold
     */
    public ArrayList<Car> getCarsAtOrAboveThreshold(float threshold);
   

}
