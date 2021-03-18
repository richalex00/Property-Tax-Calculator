import java.util.ArrayList;

/***
 * This class is used to store the propertyList in csv
 */
public class PropertiesList {
    private final String propertiesFileName = "data/properties.csv";
    private ArrayList<Property> properties;

    /***
     * This is a constructor used to initialize the property list
     * and it is also used to add the address of a property to an arrayList of property
     * and it initialize the price of the property and the residence type
     */
    public PropertiesList() {
        properties = new ArrayList<Property>();
        ArrayList<String[]> csvData = CSVHandler.loadCSV(propertiesFileName);

        for (String[] s : csvData) {
            double estimatedValue = Double.parseDouble(s[8]); // casting to correct type
            boolean principalResidence = Boolean.parseBoolean(s[9]); // casting to correct type
            Eircode eircode = new Eircode();
            eircode.fromString(s[6]);
            int dateAdded = Integer.parseInt(s[10]);
            properties.add(new Property(new Address(s[0], s[1], s[2], s[3], s[4], s[5], eircode), s[7], estimatedValue, principalResidence, dateAdded));
        }
    }

    /***
     * this methods is used to write the address of a property to a csv file
     */
    private void saveToCSV() {
        ArrayList<String[]> stringData = new ArrayList<>();
        for (Property pro : properties) {
            String[] s = new String[11];
            //Address
            s[0] = pro.getAddress().getFirstLine();
            s[1] = pro.getAddress().getSecondLine();
            s[2] = pro.getAddress().getCity();
            s[3] = pro.getAddress().getCounty();
            s[4] = pro.getAddress().getCountry();
            s[5] = pro.getAddress().getLocationType();
            s[6] = pro.getAddress().getEircode().getCode();
            //PropertyOwner
            s[7] = pro.getPropertyOwner();
            //EstimatedValue
            s[8] = String.valueOf(pro.getEstimatedMarketValue()); // casted
            //Need to check for the principle residence
            s[9] = Boolean.toString(pro.getPrincipalResidence()); // casted
            //Date property was added to the system - need to know in order to calculate overdue tax
            s[10] = Integer.toString(pro.getYearAdded());
            stringData.add(s);
        }
        CSVHandler.writeCSV(propertiesFileName, stringData);
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    /***
     * this method add a property and save it to csv file
     * @param p property passed as parameter
     */
    public void addProperty(Property p) {
        properties.add(p);
        saveToCSV();
    }
    // Admin
    // Return a list of properties from the system based on just the routing key

    /***
     *
     * @param routingKey passed as parameter
     * @return a list of properties from the system based on just the routing key
     */
    public ArrayList<Property> getPropertiesByEircode(String routingKey) {
        ArrayList<Property> temp = new ArrayList<>();
        for (Property p : properties) {
            if (p.getAddress().getEircode().getRoutingKey().equals(routingKey)) {
                temp.add(p);
            }
        }
        return temp;
    }


    /***
     * get the list of properties owned by a specific owner
     * @param owner passed as parameter
     * @return a list of properties from the system that are owned by a specific owner
     */
    public ArrayList<Property> getPropertiesByOwner(PropertyOwner owner) {
        ArrayList<Property> temp = new ArrayList<>();
        for (Property p : properties) {
            if (p.getPropertyOwner().equals(owner.getName())) {
                temp.add(p);
            }
        }
        return temp;
    }

    /***
     * get property that has the address passed
     * @param address passed as parameter
     * @return the property depending on the address passed
     */
    public Property getPropertyByAddress(Address address) {
        for (Property p : properties) {
            if (p.getAddress().equals(address)) {
                return p;
            }
        }
        return null;
    }
}
