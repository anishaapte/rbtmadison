import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Dummy implementation of the methods on the backend interface
 * @author anishaapte 
 * 
 */
public class DummyBackendImplementation implements BackendInterface {

    /**
     * Loading the contents of the file into a car. Taking into account
     * if the given file is valid or not, and if the given attributes 
     * in the file are valid or not 
     */
    @Override
    public void loadInventory(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            throw new FileNotFoundException("file does not exist " + fileName);
        }
        Scanner scnr = new Scanner(f);
        if (scnr.hasNextLine()) {
            scnr.nextLine();//skipping the first line of titles 
            while (scnr.hasNextLine()) {
                String data = scnr.nextLine();
                //getting all of the individual attributes of the car 
                String[] tokenData = data.split(",");
                if (tokenData.length < 6) {
                    throw new IOException("incomplete car information " + data);
                }
                String brand = tokenData[1];
                String model = tokenData[2];
                float price;
                float mileage;
                int year;
                try {
                    price = Float.parseFloat(tokenData[0]);
                }
                catch (NumberFormatException e) {
                    throw new IOException("the price was not a valid float for this car " + data);
                    
                }
                try {
                    year = Integer.parseInt(tokenData[3]);
                }
                catch (NumberFormatException e) {
                    throw new IOException("the year was not a valid integer for this car " + data);
                    
                }
                try {
                    mileage = Float.parseFloat(tokenData[5]);
                }
                catch (NumberFormatException e) {
                    throw new IOException("the mileage was not a valid float for this car " + data);
                    
                }
                Car c = new CarImplementation(brand, model, year, price, mileage);
                //now that i have the car, insert it into RBT
                
            }//end while 
        }//end if 
        scnr.close();
    }

    /**
     * Placeholder method to make sure that the test methods pass. Have made
     * 2 cars and have added them to the ArrayList
     */
    @Override
    public ArrayList<Car> getCarsWithMinMileage() {
        Car c1 = new CarImplementation("chevrolet", "1500",2018, (float)27700,(float)6654.0);
        Car c2 = new CarImplementation("dodge", "mpv",2018, (float)5700,(float)6654.0);
        ArrayList<Car> c = new ArrayList<Car>();
        c.add(c1);
        c.add(c2);
        return c;
    }

    /**
     * Placeholder method to make sure that the test methods pass. Have made 
     * 2 cars and have added them to the ArrayList
     */
    @Override
    public ArrayList<Car> getCarsAtOrAboveThreshold(float threshold) {
        Car c1 = new CarImplementation("chevrolet", "1500",2018, (float)27700.0,(float)6654.0);
        Car c2 = new CarImplementation("dodge", "mpv",2018, (float)5700.0,(float)5555.0);
        ArrayList<Car> c = new ArrayList<Car>();
        c.add(c1);
        c.add(c2);
        return c;
    }

}
