import java.util.ArrayList;

/***
 * This class is used to store the informations about the users
 */
public class UsersList {
    private final String usersFileName = "data/users.csv";
    private ArrayList<User> users;

    /***
     * This is a constructor used to initialize the arrayList of users
     * And it instantiate the csv file that store the users informations
     */
    public UsersList() {
        users = new ArrayList<>();
        ArrayList<String[]> csvData = CSVHandler.loadCSV(usersFileName);

        for (String[] s : csvData) {
            if (s[2].equals("propertyOwner")) {
                users.add(new PropertyOwner(s[0], s[1]));
            } else if (s[2].equals("administrator")) {
                users.add(new Administrator(s[0], s[1]));
            }
            // maybe throw an error here if the user type is invalid
        }
    }

    /***
     * this method is used to write informations users to csv file named file usersFileName
     */
    private void saveToCSV() {
        ArrayList<String[]> stringData = new ArrayList<>();
        for (User user : users) {
            String[] s = new String[3];
            s[0] = user.getName();
            s[1] = user.getPassword();
            if (user instanceof PropertyOwner) {
                s[2] = "propertyOwner";
            } else if (user instanceof Administrator) {
                s[2] = "administrator";
            }
            stringData.add(s);
        }
        CSVHandler.writeCSV(usersFileName, stringData);
    }

    /***
     * this method just remove a user from arraylist
     * @param u user passed as parameter
     */
    public void deleteUser(User u) {
        users.remove(u);
        saveToCSV();
    }

    /***
     * this method just add a user from arraylist
     * @param u user passed as parameter
     */
    public void addUser(User u) {
        users.add(u);
        saveToCSV();
    }

    /***
     * get the user by its the userName and password
     * @param username passed as parameter
     * @param password passed as parameter
     * @return user
     */
    public User getUser(String username, String password) {
        for (User u : users) {
            if (u.getName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /***
     * get the userName
     * @param username
     * @return user by its user name
     */
    public User getUserByName(String username) {
        for (User u : users) {
            if (u.getName().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /***
     * get the true true if the user name passed exists in a list and false if it does not
     * @param username
     * @return true or false
     */
    public boolean userExists(String username) {
        for (User u : users) {
            if (u.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
