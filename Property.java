import java.time.Year;

/***
 * This class is used to store the information of property object
 */
public class Property {

    private Boolean principalResidence;
    private double estimatedMarketValue;
    private String propertyOwnerName;
    private Address address;
    private int yearAdded;

    /***
     * this is a constructor to initialize the property objects
     * @param address  passed as parameter
     * @param propertyOwnerName passed as parameter
     * @param estimatedMarketValue passed as parameter
     * @param principalResidence passed as parameter
     * @param yearAdded passed as parameter
     */
    public Property(Address address, String propertyOwnerName, double estimatedMarketValue, boolean principalResidence, int yearAdded) {
        //propertyTax = new PropertyTax();
        this.address = address;
        this.propertyOwnerName = propertyOwnerName;
        this.principalResidence = principalResidence; // depends on the owner object
        this.estimatedMarketValue = estimatedMarketValue;
        this.yearAdded = yearAdded;

    }

    // Returns an array with 2 elements, first is current tax due, second is overdue tax

    /***
     * get the array of cuurent tax due and overdue tax
     * @param payments passed as parameter
     * @return Returns an array with 2 elements, first is current tax due, second is overdue tax
     */
    public double[] getCurrentAndOverdueTax(PaymentList payments) {
        double[] output = new double[2];
        output[0] = PropertyTax.getYearlyTaxDue(this);
        output[1] = PropertyTax.getOverdueTaxForYear(this, payments, Year.now().getValue());
        double paymentsThisYear = 0;
        for (Payment p : payments.getPropertyPaymentsOnYear(this, Year.now().getValue())) {
            paymentsThisYear += p.getAmount();
        }
        // Make the payments pay the current tax first, then overdue tax
        output[0] -= paymentsThisYear;
        if (output[0] < 0) {
            output[1] += output[0];
            output[0] = 0;
            if (output[1] < 0) {
                output[1] = 0;
            }
        }
        return output;
    }


    /***
     * get the year of the tax paid
     * @return year
     */
    public int getYearAdded() {
        return yearAdded;
    }

    /***
     * get the address of property
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /***
     * get the property owner of a property
     * @return property owner
     */
    public String getPropertyOwner() {
        return propertyOwnerName;
    }

    /***
     * get estimated market value of property
     * @return estimated market value
     */
    public double getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    /*public PropertyTax getPropertyTax(){
        return propertyTax;
    }*/

    /***
     * get true or false
     * @return true if it is principal residence and false if not
     */
    public Boolean getPrincipalResidence() {
        return principalResidence;
    }

    /***
     * get true if the onject passed is an instance of property class and false if not
     * @param other passed as parameter
     * @return true if the onject passed is an instance of property class and false if not
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Property)) {
            return false;
        }
        if (((Property) other).getAddress().equals(this.getAddress())) {
            if (((Property) other).getPropertyOwner().equals(this.getPropertyOwner())) {
                if (((Property) other).getEstimatedMarketValue() == (this.getEstimatedMarketValue())) {
                    if (((Property) other).getPrincipalResidence() == (this.getPrincipalResidence())) {
                        if (((Property) other).getYearAdded() == (this.getYearAdded())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /***
     * get the output as toString
     * @return the attributes of property as toString
     */
    public String toString() {
        return getAddress().toString() + "\nEstimated Market Value: " + getEstimatedMarketValue()
                + "\nPrinciple residence: " + getPrincipalResidence() + "\nYear added: " + getYearAdded();
    }
}