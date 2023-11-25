package Controllers;

import Models.Staff;
import Models.Student;
import Models.Token;
import Utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * AuthenticationController controls the authentication of the users
 */
public class AuthenticationController {
    int attempsLeft = 3;
    Token currentSessionToken = null;
   ViewControllerController vcc;
    boolean underCoolDown = false;

    /**
     * constructor takes in the ViewControllerController as its parameter.
     * This is for navigation purposes within the app.
     * @param vcc
     */
    public AuthenticationController(ViewControllerController vcc){
        this.vcc = vcc;
	}

    /**
     * check if user is under cooldown from logging
     * @return true if under cooldown, else false
     */
    public boolean isUnderCooldown(){
        return this.underCoolDown;
    }

    /**
     * authenticate user
     * @param inputPassword password entered by the user
     * @param email email input by the user
     * @return
     */
    public boolean authenticate(String inputPassword, String email){
        if(underCoolDown) return false;

        ArrayList<String[]> g = DatabaseUtils.getCredentials("./Data/staff_list.txt");
        ArrayList<String[]> s = DatabaseUtils.getCredentials("./Data/student_list.txt");
        String[] userDetails = {};
        boolean isStudent = false;


        for(int x = 0 ; x<g.size();x++){
            if(g.get(x)[2].toLowerCase().equals(email)){
                userDetails = g.get(x);
            }
        }
        for(int y =0 ; y <s.size();y++){
            if(s.get(y)[2].toLowerCase().equals(email)){
                userDetails = s.get(y);
                isStudent = true;
            }
        }
        
        try{

        if(DatabaseUtils.checkPassword(inputPassword, userDetails[8], userDetails[4])){
            this.currentSessionToken = new Token(userDetails[0], this);
            if(isStudent){
                
                    this.vcc.setCurrentUser(new Student(userDetails[0],userDetails[5]));

                
            }else{
                this.vcc.setCurrentUser(new Staff(userDetails[0],userDetails[5]));
            }
            this.attempsLeft = 3;
            return true;
        }
        this.attempsLeft-=1;
        if(this.attempsLeft<=0){
            setCoolDown();
            System.out.println("Due to too many incorrect attempts, logging in is disabled. Please try again in 1 minute");
        }else{
            
            System.out.println("Password incorrect, please try again. You are left with "+ this.attempsLeft +" tries.");
        }
    
        return false;
        }catch(Exception e){
            System.out.println(e);
        return false;
    }
    }

    /**
     * set cooldown if the user's log attempts run out
     */
    private void setCoolDown(){
        this.underCoolDown = true;
        new Timer().schedule(new TimerTask() {
            public void run(){
                underCoolDown = false;
                attempsLeft = 3;
                System.out.println("You may try logging in again. Enter any input and press enter to continue.");
            }
        }, 15000);
    }


    /**
     * remove the token of the user when the user's session is over
     */
    public void removeToken(){
        this.currentSessionToken = null;
        System.out.println("terminating program as session is over");
        this.vcc.navigate(7);
    }
}
