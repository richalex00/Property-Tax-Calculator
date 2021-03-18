import java.util.ArrayList;

/***
 * this class is used to store the payment
 */
public class PaymentList {
    //DF- double balance
    // When a property owner makes a payment on the tax due on the property a receipt is logged in this list

    private final String paymentFileName = "data/payments.csv";
    private ArrayList<Payment> payments;

    /***
     * This is a constructor used to initialize the payment  list instance variable
     * and it is also used to add the address of a property to an arrayList of property and the date and amount paid
     */
    public PaymentList() {
        payments = new ArrayList<Payment>();
        ArrayList<String[]> csvData = CSVHandler.loadCSV(paymentFileName);

        for (String[] s : csvData) {
            int temp = Integer.parseInt(s[0]);
            double temp1 = Double.parseDouble(s[1]);  // casting to correct type
            Eircode eircode = new Eircode();
            eircode.fromString(s[8]);
            this.payments.add(new Payment(temp, temp1, new Address(s[2], s[3], s[4], s[5], s[6], s[7], eircode), s[9]));
        }
    }

    /***
     * this methods is used to write the address of a property to a csv file and date when payment was made
     */
    private void saveToCSV() {
        ArrayList<String[]> stringData = new ArrayList<>();
        for (Payment pro : payments) {
            String[] s = new String[10];
            //Date payment was made
            s[0] = Integer.toString(pro.getYearPaid());
            //Amount paid
            s[1] = String.valueOf(pro.getAmount());
            //The payment is made on this property
            //Address
            s[2] = pro.getPropertyAddress().getFirstLine();
            s[3] = pro.getPropertyAddress().getSecondLine();
            s[4] = pro.getPropertyAddress().getCity();
            s[5] = pro.getPropertyAddress().getCounty();
            s[6] = pro.getPropertyAddress().getCountry();
            s[7] = pro.getPropertyAddress().getLocationType();
            s[8] = pro.getPropertyAddress().getEircode().getCode();
            // Owner
            s[9] = pro.getOwnerName();

            stringData.add(s);
        }
        CSVHandler.writeCSV(paymentFileName, stringData);
    }

    /***
     * get the payment made
     * @return payment made
     */
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    /***
     * this method add payment of a specif property to csv file
     * @param p payment passed as parameter
     */
    public void addPayment(Payment p) {
        payments.add(p);
        saveToCSV();
    }

    //Admin
    //Return payment logs for a specific user

    /***
     * get a list of payments made by owner
     * @param p passed parameter
     * @return return a list of payment for a specific owner
     */
    public ArrayList<Payment> getOwnerPayments(PropertyOwner p) {
        ArrayList<Payment> temp = new ArrayList<>();
        for (Payment pay : payments) {
            if (pay.getOwnerName().equals(p.getName())) {
                payments.add(pay);
            }
        }
        return temp;
    }

    //Admin
    //Return payment logs on a specific property

    /***
     * get the list of properties made on particular property
     * @param p passed as parameter
     * @return a list of payment made on a specif property
     */
    public ArrayList<Payment> getPropertyPayments(Property p) {
        ArrayList<Payment> temp = new ArrayList<>();
        for (Payment pay : payments) {
            if (pay.getPropertyAddress().equals(p.getAddress())) {
                temp.add(pay);
            }
        }
        return temp;
    }

    // Returns payments made to a specific property on a specific year

    /***
     * get the list of payments made to a specific property on a specific year
     * @param p passed as parameter
     * @param year passed as paramter
     * @return payments made to a specific property on a specific year
     */
    public ArrayList<Payment> getPropertyPaymentsOnYear(Property p, int year) {
        ArrayList<Payment> temp = new ArrayList<>();
        for (Payment pay : getPropertyPayments(p)) {
            if (pay.getYearPaid() == year) {
                temp.add(pay);
            }
        }
        return temp;
    }
}
