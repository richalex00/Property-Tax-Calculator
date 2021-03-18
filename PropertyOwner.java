import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/***
 * This class is uded to store the information of property onwer objects
 */
public class PropertyOwner extends User {


    /***
     * this is a constructor to iniatilize to propertyOwner objects
     * @param name passed as parameter
     * @param password passed as parameter
     */
    public PropertyOwner(String name, String password) {
        super(name, password);

    }

    public String getBalanceStatement(List<Property> properties, int year, PaymentList paymentList) {
        String output = "";
        ArrayList<Payment> allPayments = new ArrayList<>();
        ArrayList<Double> propertyTaxes = new ArrayList<Double>();
        double totalOverdue = 0;
        for (Property p : properties) {
            totalOverdue += PropertyTax.getOverdueTaxForYear(p, paymentList, year);
            for (Payment pay : paymentList.getPropertyPaymentsOnYear(p, year)) {
                allPayments.add(pay);
            }
            if (year >= p.getYearAdded() && year <= Year.now().getValue()) {
                propertyTaxes.add(PropertyTax.getYearlyTaxDue(p));
            }
        }

        output += String.format("Previous Overdue: %f\n", totalOverdue);
        for (Double d : propertyTaxes) {
            output += String.format("Property tax: %f\n", d);
            totalOverdue += d;
        }
        for (Payment pay : allPayments) {
            output += String.format("Payment: %f\n", pay.getAmount());
            totalOverdue -= pay.getAmount();
        }
        output += String.format("Balance: %f\n", totalOverdue * -1);
        return output;
    }


    /****
     * this methods compare two objects of propertyOnwer
     * @param other
     * @return return true if other is an instanceof propertyOwner
     * and false if it is not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof PropertyOwner)) {
            return false;
        }
        if (other instanceof PropertyOwner) {
            if (((PropertyOwner) other).getName().equals(this.getName())) {
                if (((PropertyOwner) other).getPassword().equals(this.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

}

