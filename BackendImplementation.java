import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/**
 * This class implements the methods in the BackendInterface. 
 * Creates the tree of IterableMultiKeyRBT<Car> as soon as class is implemented 
 * @author anishaapte
 *
 */
public class BackendImplementation implements BackendInterface {
    //create the tree
    IterableMultiKeyRBT<Car> tree;
    
    /**
     * Constructor for the BackendImplementation. Will create a new tree and 
     * instantiate the instance variable to this new tree created
     */
    public BackendImplementation() {
        IterableMultiKeyRBT<Car> tree = new IterableMultiKeyRBT<Car>();
        this.tree = tree;
        
    }
    
    

    /**
     * Reads data from a CSV file, and loads it in internal data structure
     * @param fileName - name of CSV file containing inventory
     * @throws IOException - thrown if file is not found or if file has bad records
     */
    @Override
    public void loadInventory(String fileName) throws IOException {
        this.tree.clear();
        
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
                //breaking down the data and saving off the items of each row into a variable
                //for easier use late on 
                //also will be throwing exceptions if the data given is invalid 
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
                //will then use all of these variable to create a car, which will then be 
                //inserted into the tree
                Car c = new CarImplementation(brand, model, year, price, mileage);
                this.tree.insertSingleKey(c);
                
            }//end while 
        }//end if 
        scnr.close();
        
    }

    /**
     * Gets a list of cars with the minimum mileage
     * @return - an arrayList of cars containing the min mileage
     */
    @Override
    public ArrayList<Car> getCarsWithMinMileage() {
        //will get the min mileage when the start point is set to null
        this.tree.setIterationStartPoint(null);
        //creating the carList and the iterator to iterate over the tree
        ArrayList<Car> carList = new ArrayList<Car>();
        Iterator<Car> iter = this.tree.iterator();
        
        //using while loop until null is crossed over in path
        while(iter.hasNext()) {
            //when the carList is empty, then the iterator will add the leftMost node from the tree
            if (carList.size() == 0) {
                carList.add(iter.next());
            }
            //else the leftMost node already exists in the carList, so will need to insert
            //the duplicate into the carList
            else {
                Car add = iter.next();
                if (add.getMileage() == carList.get(0).getMileage()) {
                    carList.add(add);
                }
                else {
                    break;
                }
            }

        }
        
        return carList;
    }

    /**
     * Gets a list of cars with mileage at or above a specified threshold 
     * @param threshold - mileage inputted by user 
     * @return - an arrayList of cars with mileage at or above a specified threshold
     */
    @Override
    public ArrayList<Car> getCarsAtOrAboveThreshold(float threshold) {
        //creating a dummy car passing in the given threshold and setting that as the stating point 
        Car dummyCar = new CarImplementation("dummy", "dummy", 2018, 5700, threshold);
        this.tree.setIterationStartPoint(dummyCar);
        ArrayList<Car> carList = new ArrayList<Car>();
        Iterator<Car> iter = this.tree.iterator();
        
        //will add everything that is above this at or above threshold that is in the dummycar 
        while(iter.hasNext()) {
            carList.add(iter.next());
        }
        
        return carList;
    }
    
    /**
     * Main method to implement the interactive frontend portion of the app
     * @param args - unused 
     */
    public static void main(String[] args) {
        
//        System.out.println("running main");
//        BackendInterface b = new BackendImplementation();
//        try {
//          b.loadInventory("data/testMinMileage.csv"); 
//        }
//        catch(Exception e) {
//            System.out.println(e);
//        }
//        ArrayList<Car> test = b.getCarsWithMinMileage();
//        for (int i = 0; i < test.size(); i++) {
//            System.out.println(test.get(i));
//        }
//        
//        
//      loading the file using backend method
        BackendInterface loadFile = new BackendImplementation();
//        //creating the frontend and using the tester, passing the right input
        FrontendInterface frontend = new CarOrganizerFrontend(loadFile, new Scanner(System.in));
//        TextUITester tester = new TextUITester("""
//                1
//                3
//                data/testThresh.csv
//                10000
//                4
//                """);
        
        frontend.startMainLoop();
//        //supposed to pass, sp this code should get printed 
//        
    }
    

}
