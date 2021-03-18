import java.time.Year;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/***
 * This method will allows the users to manage the system from command line interfence
 */
public class CmdLineInterface {
    private Scanner scan;
    TaxManagementSystem tms;

    /***
     * This is a constructor to initialize the instance variables of this class
     */
    public CmdLineInterface()
    {
        scan = new Scanner(System.in);
        tms = new TaxManagementSystem();
    }

    /***
     * This method allows the user to login and do the managemt tax system
     */
    public void run()
    {
        String interfaceState = "createOrLogIn";

        while (interfaceState.equals("createOrLogIn")) {
            String accountCmd = chooseFromList(Arrays.asList("Create account", "Log in", "Quit"));
            if (accountCmd.equals("Create account")) {
                interfaceState = "createAccount";
            }
            else if (accountCmd.equals("Log in")) {
                interfaceState = "logIn";
            }
            else if (accountCmd.equals("Quit")) {
                System.exit(0);
            }

            System.out.println("Username:");
            String username = scan.nextLine();
            System.out.println("Password:");
            String password = scan.nextLine();

            if (interfaceState.equals("createAccount")) {
                if (!tms.createAccount(username, password)) {
                    System.out.println("A user with that name already exists");
                }
                interfaceState = "createOrLogIn";
            }
            else if (interfaceState.equals("logIn"))
            {
                User currentUser = tms.attemptLogin(username, password);
                if (currentUser == null) {
                    System.out.println("Invalid username or password");
                    interfaceState = "createOrLogIn";
                }
                else if (currentUser instanceof PropertyOwner) {
                    interfaceState = "loggedInPropertyOwner";
                }
                else if (currentUser instanceof Administrator) {
                    interfaceState = "loggedInAdministrator";
                }
            }
        }

        while (interfaceState.equals("loggedInPropertyOwner")) {
            System.out.printf("Welcome, %s. What would you like to do?\n", tms.getLoggedInUser().getName());
            String ownerCmd = chooseFromList(Arrays.asList("Change password", "Register property", "View properties",
                    "Pay property tax", "Balance statement", "Quit"));
            if (ownerCmd.equals("Change password")) {
                System.out.println("New password:");
                String password = scan.nextLine();
                tms.changeCurrentUserPassword(password);
            }
            else if (ownerCmd.equals("Register property")) {
                Address address = inputAddress();
                System.out.println("Estimated Market Value:");
                double marketValue = inputDouble();
                System.out.println("Is this your principal private residence?");
                String principalResidenceChoice = chooseFromList(Arrays.asList("Yes", "No"));
                boolean isPrincipalResidence = principalResidenceChoice.equals("Yes");
                Property property = new Property(address, tms.getLoggedInUser().getName(), marketValue, isPrincipalResidence, Year.now().getValue());
                tms.registerProperty(property);
            }
            else if (ownerCmd.equals("View properties")) {
                System.out.print(tms.getOwnedPropertiesListString());
            }
            else if (ownerCmd.equals("Pay property tax")) {
                System.out.println("On which property?");
                Property p = chooseProperty(tms.getOwnedProperties());
                System.out.println("How much do you pay?");
                double paymentAmount = inputDouble();
                tms.payPropertyTax(p, paymentAmount);
            }
            else if (ownerCmd.equals("Balance statement")) {
                String balanceStatementType = chooseFromList(Arrays.asList("By property", "By year"));
                if (balanceStatementType.equals("By property")) {
                    System.out.println("Choose property:");
                    Property p = chooseProperty(tms.getOwnedProperties());
                    System.out.println("Enter year:");
                    int year = (int)inputDouble();

                    System.out.println(tms.getBalanceStatement(Arrays.asList(p), year));
                }
                else if (balanceStatementType.equals("By year")) {
                    System.out.println("Enter year:");
                    int year = (int)inputDouble();

                    System.out.println(tms.getBalanceStatement(tms.getOwnedProperties(), year));
                }
            }
            else if (ownerCmd.equals("Quit")) {
                System.exit(0);
            }
        }

        while (interfaceState.equals("loggedInAdministrator")) {
            System.out.println("What would you like to do?");
            String adminCmd = chooseFromList(Arrays.asList("Get property tax payment data",
                    "Get overdue property tax", "Get property tax statistics", "Quit"));
            if (adminCmd.equals("Get property tax payment data")) {
                String paymentDataType = chooseFromList(Arrays.asList("For a property", "For a property owner"));
                if (paymentDataType.equals("For a property")) {
                    Address address = inputAddress();
                    System.out.print(tms.getTaxDataForPropertyAddress(address));
                }
                else if (paymentDataType.equals("For a property owner")) {
                    System.out.println("Property owner name:");
                    String ownerName = scan.nextLine();
                    System.out.print(tms.getTaxDataForPropertyOwner(ownerName));
                }
            }
            else if (adminCmd.equals("Get overdue property tax")) {
                System.out.println("Enter year:");
                int year = (int)inputDouble();
                System.out.println("Select specific area?");
                boolean areaChoice = chooseFromList(Arrays.asList("Yes", "No")).equals("Yes");
                String routingKey = "";
                if (areaChoice) {
                    System.out.println("Enter Eircode routing key (first 3 characters)");
                    routingKey = inputRoutingKey();
                }
                System.out.print(tms.getOverduePropertyTaxByArea(year, routingKey));
            }
            else if (adminCmd.equals("Get property tax statistics")) {
                System.out.println("Enter year:");
                int year = (int)inputDouble();
                System.out.println("Enter Eircode routing key (first 3 characters)");
                String routingKey = inputRoutingKey();
                System.out.print(tms.getPropertyTaxStatistics(year, routingKey));
            }
            else if (adminCmd.equals("Quit")) {
                System.exit(0);
            }
        }
    }

    /***
     * This method allows the user to choose what he wants to do with system ei: view properties
     * @param choices passed as parameter
     * @return information
     */
    private String chooseFromList(List<String> choices) {
        // Somewhat taken from the AppointmentMenu class from Lab 7
        while (true) {
            char c = 'A';
            for (String s : choices) {
                System.out.printf("%c) %s\n", c, s);
                c++;
            }
            String input = scan.nextLine().toUpperCase();
            if (input.length() > 0) {
                int inputNum = input.charAt(0) - 'A';
                if (inputNum >= 0 && inputNum < choices.size()) {
                    return choices.get(inputNum);
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    /***
     * this method allows the user to choose the property he wants to get info on
     * @param choices passed as parameter
     * @return property
     */
    private Property chooseProperty(List<Property> choices) {
        while (true) {
            char c = 'A';
            for (Property p : choices) {
                System.out.printf("%c) %s\n", c, p.getAddress().toString());
                c++;
            }
            String input = scan.nextLine().toUpperCase();
            if (input.length() > 0) {
                int inputNum = input.charAt(0) - 'A';
                if (inputNum >= 0 && inputNum < choices.size()) {
                    return choices.get(inputNum);
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    /***
     * Get address of a property inserted from command line
     * @return address of property
     */
    private Address inputAddress() {
        System.out.println("Address first line:");
        String addressFirst = scan.nextLine();
        System.out.println("Address second line:");
        String addressSecond = scan.nextLine();
        System.out.println("Address city / town:");
        String addressCity = scan.nextLine();
        System.out.println("Address county:");
        String addressCounty = scan.nextLine();
        System.out.println("Address country:");
        String addressCountry = scan.nextLine();
        System.out.println("Location type:");
        String locationType = chooseFromList(Arrays.asList("City", "Large town", "Small town", "Village", "Countryside"));
        System.out.println("Eircode:");
        Eircode eircode = inputEircode();

        return new Address(addressFirst, addressSecond, addressCity, addressCounty, addressCountry, locationType, eircode);
    }

    /***
     * get routing key is it has size 3
     * @return RoutingKey
     */
    private String inputRoutingKey() {
        while (true) {
            String routingKey = scan.nextLine();
            if (routingKey.length() == 3) {
                return routingKey;
            }
            else {
                System.out.println("Invalid routing key");
            }
        }
    }

    /***
     * Get eircode if it is valid and correct
     * @return eirCode
     */
    private Eircode inputEircode() {
        Eircode result = new Eircode();
        while (true) {
            String eircodeString = scan.nextLine();
            if (result.fromString(eircodeString)) {
                return result;
            }
            else {
                System.out.println("Invalid Eircode");
            }
        }
    }

    /***
     * This method handles the input from command line
     * @return double value like property price, property tax, year
     */
    private double inputDouble() {
        while (true) {
            String inputString = scan.nextLine();
            try {
                double returnDouble = Double.parseDouble(inputString);
                return returnDouble; // this will only run if the previous line did not cause an exception
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }
}
