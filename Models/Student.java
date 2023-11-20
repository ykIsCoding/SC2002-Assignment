package Models;

import Models.Abstract.AUser;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Student extends AUser {
    private final List<Camp> registeredCamps;
    private final EnumSet permissions = PermissionList.getStudentPermission();
    
    public Student(String UserID, String Faculty) {
        super(UserID, "Student", Faculty);
        //Assume default Camp Commitee Membership is False.
        

        // Initialize the list of registered camps
        this.registeredCamps = new ArrayList<>();
    }

    public List<Camp> getRegisteredCamps() {
        return registeredCamps;
    }
    

}
