
/****
 * this class used for storing user information
 */
public class User {
    private String name;
    private String password;

    /****
     * this is a constructor to initialize user objets
     * @param name passed as parameter
     * @param password passed as parameter
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /***
     * get a name of user
     * @return user name
     */
    public String getName() {
        return name;
    }

    /***
     * To set a name of a person
     * @param name a new user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * get a password of user
     * @return user passsord
     */
    public String getPassword() {
        return password;
    }

    /***
     * To set a password of a person
     * @param password a new user password
     */
    public void setPassword(String password) {
        this.password = password;

    }

    /****
     * this methods compare two objects of user
     * @param other
     * @return return true if other is an instanceof user
     * and false if it is not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof User)) {
            return false;
        }
        return name.equals(((User) other).getName()) && password.equals(((User) other).getName());
    }
}


