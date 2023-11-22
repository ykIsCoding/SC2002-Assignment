package Models.Abstract;

import Utils.DatabaseUtils;

/**
 * Abstract User base class that is the blueprint of all user types such as Student and Staff.
 */
public abstract class AUser {
    private final String UserID;
    private final String UserType;
    private String Name;
    private final String Faculty;
    private final String email;
    private String Password;

    /**
     * The constructors of the AUser class.
     * @param UserID is the ID of the user.
     * @param UserType is the type of the user, such as Student, Staff etc.
     * @param Faculty is the faculty of the user type such as SCSE, NBS, CoE etc.
     * The last 2 information were retrieved from the Database.
     */
    public AUser(String UserID, String UserType, String Faculty) {
        this.UserID = UserID;
        this.UserType = UserType;
        this.Faculty = Faculty;
        String[] info = DatabaseUtils.getUserByID(UserID);
        this.Name = info[1];
        this.email = info[2];
    }

    /**
     * Getter Method
     * @return the name of the User
     */
    public String getName() {
        return Name;
    }

    /**
     * Getter Method
     * @return the ID of the User
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * Getter Method
     * @return the faculty of the User
     */
    public String getFaculty() {
        return Faculty;
    }


}
