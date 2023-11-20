package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import Models.Abstract.AUser;
import Utils.DatabaseUtils;

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
