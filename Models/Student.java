package Models;

import Models.Abstract.AUser;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Student class for students
 */
public class Student extends AUser {
    private final List<Camp> registeredCamps;
    

    /**
     * Staff constructor takes in user id and faculty
     * @param UserID userid of student
     * @param Faculty faculty of student
     */
    public Student(String UserID, String Faculty) {
        super(UserID, "Student", Faculty);
        //Assume default Camp Commitee Membership is False.
        

        // Initialize the list of registered camps
        this.registeredCamps = new ArrayList<>();
    }

    /**
     * get the list of registered camps of the user
     * @return
     */
    public List<Camp> getRegisteredCamps() {
        return registeredCamps;
    }
    

}
