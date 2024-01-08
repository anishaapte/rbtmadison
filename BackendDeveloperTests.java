import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Testing the backend implementation of the 3 methods on the backend interface
 * @author anishaapte
 *
 */
public class BackendDeveloperTests {
    /**
     * Testing the loadInventory method on the backend implementation with a file that does 
     * not exist. 
     */
    @Test
    public void testLoadNoFile() {
        try {
            BackendInterface noFile = new DummyBackendImplementation();
            noFile.loadInventory("data/noFile.csv");
            //supposed to fail, so code should never happen
            assertFalse("This should not be reached because file doesn't exist", true);
        }
        catch(Exception e) {
            //method throws an IOException, so we should be looking for that 
            if (e instanceof IOException) {
                assertTrue("Passed with expected exception", true);
            }
            else {
                //Other exceptions are not what we are looking for 
                assertTrue("Other exceptions have been caught, but is not IOException " + e , false);
            }
        }
        
    }
    
    /**
     * Testing the loadInventory method on the backend implementation with a 
     * file that is incomplete
     */
    @Test
    public void testLoadIncompleteData() {
        try {
            BackendInterface b = new DummyBackendImplementation();
            b.loadInventory("data/incomplete.csv");
            //supposed to fail, so code should never happen
            assertFalse("This should not be reached because file doesn't exist", true);
        }
        catch(Exception e) {
            //method throws an IOException, so we should be looking for that 
            if (e instanceof IOException) {
                assertTrue("Passed with expected exception", true);
            }
            else {
                //Other exceptions are not what we are looking for 
                assertTrue("Other exceptions have been caught, but is not IOException " + e , false);
            }
        }
        
    }
    
    /**
     * Testing the loadInventory method on the backend implementation with a file
     * that does not have the correct data
     */
    @Test
    public void testLoadBadDataType() {
        try {
            BackendInterface b = new DummyBackendImplementation();
            b.loadInventory("data/badData.csv");
            //supposed to fail, so code should never happen
            assertFalse("This should not be reached because file doesn't exist", true);
        }
        catch(Exception e) {
            //method throws an IOException, so we should be looking for that 
            if (e instanceof IOException) {
                assertTrue("Passed with expected exception", true);
            }
            //Other exceptions are not what we are looking for 
            else {
                assertTrue("Other exceptions have been caught, but is not IOException " + e , false);
            }
        }
        
    }
    
    /**
     * Testing the getCarsWithMinMileage method on the backend implementation
     */
    @Test
    public void testGetCarsWithMinMileage() {
        try {
            BackendInterface b = new DummyBackendImplementation();
            b.loadInventory("data/testMinMileage.csv");
            //capturing the arrayList outputted by the method 
            ArrayList<Car> testMinMileage = b.getCarsWithMinMileage();
            //first checking that the arrayList is the correct size
            assertEquals("Expecting 2, got " + testMinMileage.size(),2,testMinMileage.size());
            Car c1 = new CarImplementation("chevrolet", "1500",2018, (float)27700,(float)6654.0);
            Car c2 = new CarImplementation("dodge", "mpv",2018, (float)5700,(float)6654.0);
            //checking that the arrayList contains the right elements
            assertTrue("Expected cars are there",testMinMileage.contains(c1) && testMinMileage.contains(c2));
                     
        }
        catch(Exception e) {
            assertTrue("An exception caught " + e , false);
            
        }
        
    }
    
    /**
     * Testing the getCarsAtOrAboveThreshold() method on the backend implementation
     */
    @Test
    public void testGetCarsAtOrAboveThreshold() {
        try {
            BackendInterface b = new BackendImplementation();
            b.loadInventory("data/testThresh.csv");
            //capturing the arrayList outputted by the method 
            ArrayList<Car> testThresh = b.getCarsAtOrAboveThreshold(100000);
            //first checking that the arrayList is the correct size
            assertEquals("Expecting 2, got " + testThresh.size(),2,testThresh.size());
            Car c1 = new CarImplementation("toyota", "cruiser",2008, (float)6300.0,(float)274117.0);
            Car c2 = new CarImplementation("ford", "se",2011, (float)2899.0,(float)190552.0);
            //checking that the arrayList contains the right elements
            assertTrue("Expected cars are there",testThresh.contains(c1) && testThresh.contains(c2));
            
        }
        catch(Exception e) {
            assertTrue("An exception caught " + e , false);
            
        }
        
    }
    
    /**
     * Tests the loadFile method using the frontend implementation. 
     * Uses the TextUI Tester by passing the appeopriate input required 
     * by the mainMenu and subMenu for loading a file 
     * 
     * Test passes as long as there is no exception, means that the file is 
     * valid(passing in a valid file)
     */
    @Test
    public void testLoadFileIntegration() {
        try {
            TextUITester tester = new TextUITester("""
                    1
                    data/cars.csv
                    4
                    """);
            //loading the file using backend method
            BackendInterface loadFile = new BackendImplementation();
            //creating the frontend and using the tester, passing the right input
            FrontendInterface frontend = new CarOrganizerFrontend(loadFile, new Scanner(System.in));
            
            frontend.startMainLoop();
            //supposed to pass, sp this code should get printed 
            assertTrue("This should work because cars.csv is a valid file", true);
        }
        catch(Exception e) {
            //catching exception - invalid file 
            //should not happen because file is valid 
            assertFalse("An exception caught " + e , true);
            
        }
        
    }
    
    /**
     * Testing the getCarsWithMinMileage() method using the 
     * TextUITesterwith appropriate input 
     */
    @Test 
    public void testGetCarsThresholdIntegration() {
        try {
            TextUITester tester = new TextUITester("""
                    1
                    data/testThresh.csv
                    3
                    100000
                    4
                    """);
            //using backend to create the file 
            BackendInterface loadFile = new BackendImplementation();
            //creating a frontend and using tester, passing the right input
            FrontendInterface frontend = new CarOrganizerFrontend(loadFile, new Scanner(System.in));
            
            frontend.startMainLoop();
            String testerOutput = tester.checkOutput();
            System.out.println(testerOutput);
            
            String expected1 = "toyota,cruiser,2008,6300.0,274117.0";
            String expected2 = "ford,se,2011,2899.0,190552.0";
            //checking that the output contains the expected strings
            assertTrue("Testeroutput is: " + testerOutput,testerOutput.contains(expected1) && testerOutput.contains(expected2));
            
        }
        catch(Exception e) {
            //exception is caught - invalid file 
            //should not happen as file is valid
            assertFalse("An exception caught " + e , true);
            
        }
        
    }
    
    /**
     * Integration test with the loadFile method. The frontend should recognize that 
     * the file I have passed in is invalid. Integration should work properly
     */
    @Test
    public void testFrontendLoadInvalidFileIntegration() {
        try {
            TextUITester tester = new TextUITester("""
                    1
                    data/incomplete.csv
                    4
                    """);
            //loading the file using backend method
            BackendInterface loadFile = new BackendImplementation();
            //creating the frontend and using the tester, passing the right input
            FrontendInterface frontend = new CarOrganizerFrontend(loadFile, new Scanner(System.in));
            
            frontend.startMainLoop();
            
            String actual = tester.checkOutput();
            String expected = "invalid file data/incomplete.csv";
            System.out.println("--------" + actual + "--------");
            //supposed to pass, sp this code should get printed 
            assertTrue("This should fail because file is invalid ", actual.contains(expected));
        }
        catch(Exception e) {
            //catching exception - invalid file 
            //should not happen because file is valid 
            assertFalse("An exception caught " + e , true);
            
        }
        
    }
    /**
     * Integration test with the frontend for the minMileage method. 
     * Integration should run properly and the program should output the 
     * correct ArrayList of cars
     */
    @Test
    public void testFrontMinMileageIntegration() {
        try {
            TextUITester tester = new TextUITester("""
                    1
                    data/testMinMileage.csv
                    2
                    4
                    """);
            //using backend to create the file 
            BackendInterface loadFile = new BackendImplementation();
            //creating a frontend and using tester, passing the right input
            FrontendInterface frontend = new CarOrganizerFrontend(loadFile, new Scanner(System.in));
            
            //capturing the results into testerOutput
            frontend.startMainLoop();
            String testerOutput = tester.checkOutput();
            System.out.println(testerOutput);
            
            //what should be expected out of testerOutput 
            String expected1 = "chevrolet,1500,2018,27700.0,6654.0";
            String expected2 = "dodge,mpv,2018,5700.0,6654.0";
            //checking that the output contains the expected strings
            assertTrue("Testeroutput is: " + testerOutput,testerOutput.contains(expected1) && testerOutput.contains(expected2));
            
        }
        catch(Exception e) {
            //exception is caught - invalid file 
            //should not happen as file is valid
            assertFalse("An exception caught " + e , true);
            
        }
    }
    
}
