
/****
 * this class is used to stores the EirCode information
 */
public class Eircode {

    private String code;

    /***
     * this is a constructor it initialize the eirCode
     */
    public Eircode() {
        code = "       "; // set it to something so the substring operations don't cause errors
    }

    /***
     * this method check the code passed if it has space in it or not
     * @param codeString eirCode passed as String
     * @return true if the code has no space and false if it has
     */
    public boolean fromString(String codeString) {
        String codeNoSpaces = codeString.toUpperCase().replace(" ", "");
        if (codeNoSpaces.length() != 7) {
            return false;
        }
        this.code = codeNoSpaces;
        return true;
    }

    /***
     * get eircode
     * @return the eirCode of a property
     */
    public String getCode() {
        return code;
    }

    /***
     * get eircode routing key
     * @return eirCode's routing Key
     */
    public String getRoutingKey() {
        return code.substring(0, 3);
    }

    /***
     * get unique Id of eirCode
     * @return unique id of eirCode
     */
    public String getUniqueId() {
        return code.substring(3, 7);
    }
}
