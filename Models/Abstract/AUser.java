package Models.Abstract;

public abstract class AUser {
    private final String UserID;
    private final String UserType;
    private String Name;
    private final String Faculty;
    private String Password;

    public AUser(String UserID, String UserType, String Faculty) {
        this.UserID = UserID;
        this.UserType = UserType;
        this.Faculty = Faculty;
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
