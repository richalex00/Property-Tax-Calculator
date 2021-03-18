/***
 * this class is used to store the payment information
 */
public class Payment {
    private int yearPaid;
    private double amount;
    private Address propertyAddress;
    private String ownerName;

    /***
     * this initialize the payment objects
     * @param yearPaid passed as parameter
     * @param amount passed as parameter
     * @param propertyAddress passed as parameter
     * @param ownerName passed as parameter
     */
    public Payment(int yearPaid, double amount, Address propertyAddress, String ownerName) {
        this.yearPaid = yearPaid;
        this.amount = amount;
        this.propertyAddress = propertyAddress;
        this.ownerName = ownerName;
    }

    /***
     * get the year of payment
     * @return payment year
     */
    public int getYearPaid() {
        return yearPaid;
    }

    /***
     * get the amount of payment
     * @return amount paid
     */
    public double getAmount() {
        return amount;
    }

    /***
     * get the property address
     * @return the address of a property
     */
    public Address getPropertyAddress() {
        return propertyAddress;
    }

    /***
     * get the owner name
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }
}
