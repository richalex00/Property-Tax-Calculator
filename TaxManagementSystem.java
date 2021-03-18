import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/***
 * this class handle the mtax management system of property
 */
public class TaxManagementSystem {
    private UsersList usersList;
    private PropertiesList propertiesList;
    private PaymentList paymentList;

    private User currentUser;

    /***
     * This is a constructor to initialize the instance variable of management system
     */
    public TaxManagementSystem() {
        usersList = new UsersList();
        propertiesList = new PropertiesList();
        paymentList = new PaymentList();
        PropertyTax.loadPropertyTax();
    }

    /***
     * get user
     * @param username passed as parameter
     * @param password passed as parameter
     * @return user
     */
    public User attemptLogin(String username, String password) {
        currentUser = usersList.getUser(username, password);
        return currentUser;
    }

    //  false if that user already exists, true if creation succeeded

    /***
     * get false if that user already exists, true if creation succeeded
     * @param username passed as parameter
     * @param password passed as parameter
     * @return Returns false if that user already exists, true if creation succeeded
     */
    public boolean createAccount(String username, String password) {
        if (usersList.userExists(username)) {
            return false;
        } else {
            usersList.addUser(new PropertyOwner(username, password));
            return true;
        }
    }

    /***
     * get user who is logged in
     * @return user who is loggein
     */
    public User getLoggedInUser() {
        return currentUser;
    }

    /***
     * this alllows the changing of passoword
     * @param password passed as parameter
     */
    public void changeCurrentUserPassword(String password) {
        usersList.deleteUser(currentUser);
        currentUser.setPassword(password);
        usersList.addUser(currentUser);
    }

    /***
     * This method register the property
     * @param property passed as parameter
     */
    public void registerProperty(Property property) {
        propertiesList.addProperty(property);
    }

    /***
     * get owned properties as a string
     * @return owned properties as a string
     */
    public String getOwnedPropertiesListString() {
        String output = "";
        ArrayList<Property> ownerProperties = propertiesList.getPropertiesByOwner((PropertyOwner) currentUser);
        for (Property p : ownerProperties) {
            output += p.toString() + "\n";
            double[] propertyTax = p.getCurrentAndOverdueTax(paymentList);
            output += String.format("Current property tax: %f Overdue property tax: %f\n", propertyTax[0], propertyTax[1]);
            output += "\n";
        }
        return output;
    }

    /***
     * get the list of owned properties
     * @return a list of owned properties
     */
    public List<Property> getOwnedProperties() {
        return propertiesList.getPropertiesByOwner((PropertyOwner) currentUser);
    }

    /***
     *  this method allows the user to pay for a property
     * @param p passed as parameter
     * @param amount passed as parameter
     */
    public void payPropertyTax(Property p, double amount) {
        paymentList.addPayment(new Payment(Year.now().getValue(), amount, p.getAddress(), currentUser.getName()));
    }

    /***
     * get balance statement
     * @param properties passed as parameter
     * @param year passed as parameter
     * @return balnce stetement
     */
    public String getBalanceStatement(List<Property> properties, int year) {
        return ((PropertyOwner) currentUser).getBalanceStatement(properties, year, paymentList);
    }

    /***
     * get property data tax for perty depending on the address
     * @param address passed as parameter
     * @return property data tax
     */
    public String getTaxDataForPropertyAddress(Address address) {
        Property p = propertiesList.getPropertyByAddress(address);
        if (p == null) {
            return "There is no registered property at that address.";
        }
        double[] taxData = p.getCurrentAndOverdueTax(paymentList);
        return String.format("Current property tax: %f Overdue property tax: %f\n", taxData[0], taxData[1]);
    }

    /***
     * get property data tax for property depending on the owner name
     * @param ownerName passed as parameter
     * @return property data tax
     */
    public String getTaxDataForPropertyOwner(String ownerName) {
        User propertyOwner = usersList.getUserByName(ownerName);
        if (propertyOwner == null || !(propertyOwner instanceof PropertyOwner)) {
            return "There is no property owner with that name.";
        }
        String output = "";
        List<Property> ownedProperties = propertiesList.getPropertiesByOwner((PropertyOwner) propertyOwner);
        for (Property p : ownedProperties) {
            output += p.getAddress().toString() + "\n";
            double[] taxData = p.getCurrentAndOverdueTax(paymentList);
            output += String.format("Current property tax: %f Overdue property tax: %f\n", taxData[0], taxData[1]);
        }
        return output;
    }

    /***
     * get overdue data tax for property depending on the routingKey and year
     * @param year passed as parameter
     * @param routingKey passed as parameter
     * @return Overdue Property Tax
     */
    public String getOverduePropertyTaxByArea(int year, String routingKey) {
        String output = "";
        List<Property> relevantProperties;
        if (!routingKey.equals("")) {
            relevantProperties = propertiesList.getPropertiesByEircode(routingKey);
        } else {
            relevantProperties = propertiesList.getProperties();
        }
        for (Property p : relevantProperties) {
            if (p.getYearAdded() < year) {
                output += p.getAddress().toString() + "\n";
                output += String.format("Overdue property tax: %f\n", PropertyTax.getOverdueTaxForYear(p, paymentList, year + 1));
            }
        }
        return output;
    }

    /***
     * get property tax Statistics for property depending on the routingKey and year
     * @param year passed as parameter
     * @param routingKey passed as parameter
     * @return PropertyTaxStatistics
     */
    public String getPropertyTaxStatistics(int year, String routingKey) {
        List<Property> relevantProperties = propertiesList.getPropertiesByEircode(routingKey);

        double totalTaxPaid = 0;
        int numProperties = relevantProperties.size();
        int numPropertiesFullyPaid = 0;
        for (Property p : relevantProperties) {
            List<Payment> propertyPayments = paymentList.getPropertyPaymentsOnYear(p, year);
            for (Payment pay : propertyPayments) {
                totalTaxPaid += pay.getAmount();
            }
            // If the property tax overdue for the following year is 0, then this year was fully paid
            if (PropertyTax.getOverdueTaxForYear(p, paymentList, year + 1) == 0) {
                numPropertiesFullyPaid++;
            }
        }
        String output = "";
        output += String.format("Total tax paid: %f\n", totalTaxPaid);
        output += String.format("Average tax paid per property: %f\n", totalTaxPaid / numProperties);
        output += String.format("Total number of properties: %d\n", numProperties);
        output += String.format("Number of properties with tax paid in full: %d\n", numPropertiesFullyPaid);
        output += String.format("Percentage of properties with tax paid in full: %f%%\n", ((double) numPropertiesFullyPaid / (double) numProperties) * 100);
        return output;
    }
}
