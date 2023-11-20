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

    public void setPassword(String newPassword) {
        this.Password = newPassword;
    }
    

    

    
    public String getName() {
        return Name;
    }
    public String getUserID() {
        return UserID;
    }
    public String getUserType() {
        return UserType;
    }
    public String getFaculty() {
        return Faculty;
    }
    public String getPassword() {
        return Password;
    }

}
