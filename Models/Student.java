package Models;

import Controllers.Abstract.AUser;
import java.util.Scanner;

public class Student extends AUser {
    private boolean campCommiteeMembership;
    //private int StudentID;
    
    public Student(String UserID, String Faculty) {
        super(UserID, "Student", Faculty);
        //Assume default Camp Commitee Membership is False.
        setCampCommiteeMembership(false);

        //Generate Initial Password
        //System.out.println("Your assigned password is " + password);
    }

    public void setCampCommiteeMembership(boolean campCommiteeMembership) {
        this.campCommiteeMembership = campCommiteeMembership;
    }
    public boolean isCampComiteeMembership() {
        return campCommiteeMembership;
    }

    @Override
    public void changePassword() {

    }

}
