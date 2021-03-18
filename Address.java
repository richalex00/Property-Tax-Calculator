/****
 * created on the 12/12/2020
 * Address class is used to store the address of a property
 */
public class Address {

    private String firstLine;
    private String secondLine;
    private String city;
    private String county;
    private String country;
    private String locationType;
    private Eircode eircode;

    /****
     * this is a constructor to initialize the instances variable of Address class
     * @param firstLine
     * @param secondLine
     * @param city
     * @param county
     * @param country
     * @param locationType
     * @param eircode
     */
    public Address(String firstLine, String secondLine, String city, String county,
                   String country, String locationType, Eircode eircode) {

        this.eircode = eircode;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.county = county;
        this.country = country;
        this.locationType = locationType;
    }

    /****
     * get the Eircode OF the address
     * @return Eircode
     */
    public Eircode getEircode() {
        return eircode;
    }

    /****
     * get the FisrtLine of the address
     * @return FisrtLine of the address
     */
    public String getFirstLine() {
        return firstLine;
    }

    /****
     * get the Eircode OF the address
     * @return Eircode
     */
    public String getSecondLine() {
        return secondLine;
    }

    /****
     * get the city of the address
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /****
     * get the county of the address
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /****
     * get the Eircode OF the address
     * @return Eircode
     */
    public String getCountry() {
        return country;
    }

    /****
     * get the location of the address
     * @return the location
     */
    public String getLocationType() {
        return locationType;
    }

    /****
     * print out the whole address
     * @return the address as a toString
     */
    @Override
    public String toString() {
        if (secondLine.equals("")) {
            return firstLine + " " + city + " " + county + " " + country + " " + eircode.getCode();
        } else {
            return firstLine + " " + secondLine + " " + city + " " + county + " " + country + " " + eircode.getCode();
        }
    }

    /****
     * this methods compare two address objects
     * @param other
     * @return return true if other is an instanceof Address
     * and False if it is not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Address)) {
            return false;
        }
        if (((Address) other).getFirstLine().equals(this.getFirstLine())) {
            if (((Address) other).getSecondLine().equals(this.getSecondLine())) {
                if (((Address) other).getCity().equals(this.getCity())) {
                    if (((Address) other).getCounty().equals(this.getCounty())) {
                        if (((Address) other).getCountry().equals(this.getCountry())) {
                            if (((Address) other).getLocationType().equals(this.getLocationType())) {
                                if (((Address) other).getEircode().getCode().equals(this.getEircode().getCode())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
