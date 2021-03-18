import java.util.ArrayList;

/****
 * Administrator class uses for property and property information
 */
public class Administrator extends User {
    PropertiesList object = new PropertiesList();
    PaymentList payments = new PaymentList();

    /****
     * this is a constructor to initialize the administrator object
     * @param name an initial person name
     * @param password and his password
     */
    public Administrator(String name, String password) {
        super(name, password);
    }


}

