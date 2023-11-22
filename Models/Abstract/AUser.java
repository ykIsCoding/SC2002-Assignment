package Models.Abstract;

import Utils.DatabaseUtils;

public abstract class AUser {
    private final String UserID;
    private final String UserType;
    private String Name;
    private final String Faculty;
    private final String email;
    private String Password;

    public AUser(String UserID, String UserType, String Faculty) {
        this.UserID = UserID;
        this.UserType = UserType;
        this.Faculty = Faculty;
        String[] info = DatabaseUtils.getUserByID(UserID);
        this.Name = info[1];
        this.email = info[2];
    }

    
    public String getName() {
        return Name;
    }
    public String getUserID() {
        return UserID;
    }

    public String getFaculty() {
        return Faculty;
    }


}
