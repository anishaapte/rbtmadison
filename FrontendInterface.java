import java.util.ArrayList;

public interface FrontendInterface {

    /**
     * Constructor to initialize the frontend with a reference to the backend and a Scanner for user input.
     *
     * @param backend Reference to the backend (IndividualBackendInterface)
     * @param scanner Scanner for user input

    public IndividualFrontendInterface(IndividualBackendInterface backend, Scanner scanner);
    This line of code is not able to compile, we would need to the interface developed from the
     backend for the code to work properly.

    */

    /**
     * Starts the main command loop for the user.
     * This method should display the main menu, handle user input, and execute the selected
     * commands. It should start all the required/desired function.
     */
    public void startMainLoop();

    /**
     * Displays the main menu to the user and prompts for a command selection.
     *
     */
    public void displayMainMenu();

    /**
     * Displays a submenu for listing vehicles based on the specified data and handles user input.
     * This method should handle user input for menus (e.g., list the lowest mileage, list by
     * threshold).
     *
     * @param submenuTitle Title of the submenu
     */
    public void displaySubmenu(String submenuTitle);

    /**
     * Prompts the user to specify a data file to load.
     *
     * @return The path to the selected data file
     */
    public String promptForDataFile();

    /**
     * Prompts the user for a mileage threshold value.
     *
     * @return The specified mileage threshold as type float
     */
    public Float promptForMileageThreshold();

    /**
     * Displays a list of vehicles based on the provided data.
     *
     * @param vehicleList The list of vehicles to display
     */
    public void displayVehicleList(ArrayList vehicleList);

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display
     */
    public void displayError(String errorMessage);
}

