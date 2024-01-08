import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CarOrganizerFrontend provides an interactive user interface for managing and querying
 * a car inventory system. The frontend communicates with a backend system to load
 * vehicle data, display lists of vehicles based on various criteria, and handle other
 * related tasks. The interface is primarily text-based and operates through a series
 * of menus and prompts to guide the user.
 *
 * @author George Shi
 */
public class CarOrganizerFrontend implements FrontendInterface {
    private BackendInterface backend;
    private Scanner scanner;

    /**
     * Constructor to initialize the frontend with a reference to the backend and a Scanner for user input.
     *
     * @param backend Reference to the backend (IndividualBackendInterface)
     * @param scanner Scanner for user input
     */
    public CarOrganizerFrontend(BackendInterface backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }

    /**
     * Starts the main command loop for the user.
     * This method should display the main menu, handle user input, and execute the selected
     * commands. It should start all the required/desired function.
     */
    @Override
    public void startMainLoop() {
        String command = "-1";
        boolean exit = false;


        while (!exit) {

            displayMainMenu();


            while (true) {
                if (scanner.hasNextLine()) {
                    String userchoice = scanner.nextLine();
                    if (!userchoice.equals("1") && !userchoice.equals("2") && !userchoice.equals("3") && !userchoice.equals("4")) {
                        displayError("Please pick a valid choice from 1, 2, 3, or 4.");
                    } else {
                        command = userchoice;
                        break;
                    }
                }
            }

            switch (command) {
                case "1":
                    displaySubmenu("1. Specify and load data.");
                    // Load data using backend
                    break;
                case "2":
                    // List vehicles with lowest mileage
                    displaySubmenu("2. List vehicles with lowest mileage.");
                    break;
                case "3":
                    // List vehicles above the threshold using backend
                    displaySubmenu("3. List all vehicles with a mileage at or above a specified " +
                            "threshold.");
                    break;
                case "4":
                    exit = true;
                    System.out.println("Thank you for using Car Organizer! Goodbye.");
                    break;
            }

        }
    }

    /**
     * Displays the main menu to the user and prompts for a command selection.
     */
    @Override
    public void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Specify and load data.");
        System.out.println("2. List vehicles with lowest mileage.");
        System.out.println("3. List all vehicles with a mileage at or above a specified threshold.");
        System.out.println("4. Exit.");

        System.out.print("Enter your choice: ");
    }

    /**
     * Displays a submenu for listing vehicles based on the specified data and handles user input.
     * This method should handle user input for menus (e.g., list the lowest mileage, list by
     * threshold).
     *
     * @param submenuTitle Title of the submenu
     */
    @Override
    public void displaySubmenu(String submenuTitle) {
        System.out.println(submenuTitle);

        switch (submenuTitle) {
            case "1. Specify and load data.":
                String filePath = promptForDataFile();
                try {
                    backend.loadInventory(filePath);
                    System.out.println("valid file " + filePath);
                } catch (IOException e) {
                    System.out.println("invalid file " + filePath);
                    //throw new RuntimeException(e);
                }
                break;
            case "2. List vehicles with lowest mileage.":
                displayVehicleList(backend.getCarsWithMinMileage());
                break;
            case "3. List all vehicles with a mileage at or above a specified threshold.":
                float mileageThreshold = promptForMileageThreshold();
                System.out.println(backend.getCarsAtOrAboveThreshold(mileageThreshold));
                break;

        }

        System.out.println("Command complete, you can choose the next command:");
    }

    /**
     * Prompts the user to specify a data file to load.
     *
     * @return The path to the selected data file
     */
    @Override
    public String promptForDataFile() {
        System.out.print("Enter the path to the data file: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user for a mileage threshold value. Repeatedly checks for a valid input until
     * the user input is a non-negative type float.
     *
     * @return The specified mileage threshold as type float
     */
    @Override
    public Float promptForMileageThreshold() {
        String userInput = null;

        System.out.print("Enter the mileage threshold: ");

        while (true) {
            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
                try {
                    if (Float.parseFloat(userInput) > 0) {
                        return Float.parseFloat(userInput);
                    }
                    System.out.println("Please enter a number value above 0.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number value above 0.");
                }
            }
        }
    }

    /**
     * Displays a list of vehicles based on the provided data.
     *
     * @param vehicleList The list of vehicles to display
     */
    @Override
    public void displayVehicleList(ArrayList vehicleList) {
        System.out.println("Vehicle List:");
        System.out.println(vehicleList.toString());
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display
     */
    @Override
    public void displayError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    public static void main(String[] args) {

        System.out.println("running main");
        BackendInterface backend = new BackendImplementation();
        FrontendInterface frontend = new CarOrganizerFrontend(backend, new Scanner(System.in));
        frontend.startMainLoop();

    }
}

