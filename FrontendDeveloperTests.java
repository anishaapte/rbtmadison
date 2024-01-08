import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * This class contains 5 JUnit test methods for the functionalities provided by
 * FrontendInterface.java, using TextUITester to simulate user input and capture output for
 * verification. These tests are opaque-box tests that might run on any implementation of the
 * interface.
 */
public class FrontendDeveloperTests {


    /**
     * Test 1: Tests the display of the main menu and if the scanner can get the users input.
     * This test checks whether the frontend provides menu options to the user and
     * correctly reads in the user input for the main menu.
     */
    @Test
    public void test1() {
        TextUITester tester = new TextUITester("""
                1
                car1
                4
                """);
        // "4" selects the option to exit, must input command to exit here because other
        // options causes the program to bug because of no other inputs.

        CarOrganizerFrontend frontend = new CarOrganizerFrontend(new BackendPlaceholder(), new Scanner(System.in));
        frontend.startMainLoop();
        String output = tester.checkOutput();

        assertTrue(output.contains("20000.0,Toyota,Camry,2022,Clean,1000.0,Blue,1234VIN,5678,CA,USA,New"));
        //The actual prompt of the menu display might be different.
    }

    /**
     * Test 2: Tests the submenu display and user input handling.
     * This test checks whether the frontend displays the submenu based on user input and handles
     * user input correctly.
     */
    @Test
    public void test2() {
        TextUITester tester = new TextUITester("""
                2
                4
                """);
        // "4" selects the option to exit, must input command to exit here because other
        // options causes the program to bug because of no other inputs.

        BackendPlaceholder backend = new BackendPlaceholder();
        CarOrganizerFrontend frontend = new CarOrganizerFrontend(backend, new Scanner(System.in));
        frontend.startMainLoop();
        String output = tester.checkOutput();

        assertTrue(output.contains("[20000.0,Toyota,Camry,2022,Clean,1000.0,Blue,1234VIN,5678,CA,USA,New, 25000.0,Honda,Civic,2021,Clean,5000.0,Red,5678VIN,1234,TX,USA,Used]"));
        assertEquals(backend.getCarsWithMinMileage().size(), 2);
        // The actual prompt of the submenu display might be different.
    }

    /**
     * Test 3: Test list all vehicles with a mileage at or above a specified threshold.
     * Tests and make sures the application returns the correct output and vehicle information
     * when choosing command 3.
     */
    @Test
    public void test3() {
        TextUITester tester = new TextUITester("""
                3
                2000.0
                4
                """);

        CarOrganizerFrontend frontend = new CarOrganizerFrontend(new BackendPlaceholder(),
                new Scanner(System.in));
        frontend.startMainLoop();
        String output = tester.checkOutput();

        assertTrue(output.contains("[25000.0,Honda,Civic,2021,Clean,5000.0,Red,5678VIN,1234,TX," +
                "USA,Used]"));
    }

    /**
     * Test 4: Test negative mileage threshold.
     * Checks if that the application handles a negative mileage threshold and provides
     * appropriate feedback.
     */
    @Test
    public void test4() {
        TextUITester tester = new TextUITester("""
                3
                -1
                10000
                4
                """);

        CarOrganizerFrontend frontend = new CarOrganizerFrontend(new BackendPlaceholder(), new Scanner(System.in));
        frontend.startMainLoop();
        String output = tester.checkOutput();
        // Check if the error message is displayed.

        assertTrue(output.contains("Error: Please enter a mileage threshold that is not negative."));
    }


    /**
     * Test 5: Test non-numeric mileage threshold.
     * Checks if that non-numeric mileage thresholds are handled correctly.
     */
    @Test
    public void test5() {
        TextUITester tester = new TextUITester("""
                3
                string
                10000
                4
                """);

        CarOrganizerFrontend frontend = new CarOrganizerFrontend(new BackendPlaceholder(), new Scanner(System.in));
        frontend.startMainLoop();
        String output = tester.checkOutput();
        // Check if the error message is displayed.

        assertTrue(output.contains("Error: Please enter a valid numeric mileage threshold."));
    }
}