package Controllers;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.InputMap;

import Models.CampCommiteeMember;
import Models.Staff;
import Models.Student;
import Models.Token;
import Models.Abstract.AUser;
import Utils.DatabaseUtils;
import Utils.InputUtils;

public class AuthenticationController {
    int attempsLeft = 3;
    String password = "password";
    Token currentSessionToken = null;
   ViewControllerController vcc;
    boolean underCoolDown = false;
    

    public AuthenticationController(ViewControllerController vcc){
        this.vcc = vcc;
	}

    public boolean isUnderCooldown(){
        return this.underCoolDown;
    }

    public boolean authenticate(String inputPassword, String email){
        if(underCoolDown) return false;
        //gets all staff and student as an array list
        ArrayList<String[]> g = DatabaseUtils.getCredentials("./Data/staff_list.txt");
        ArrayList<String[]> s = DatabaseUtils.getCredentials("./Data/student_list.txt");
        String[] userDetails = {};
        boolean isStudent = false;

        //finds the staff or student whose email matches the email entered
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
        //checks the password
        if(DatabaseUtils.checkPassword(inputPassword, userDetails[8], userDetails[4])){
            this.currentSessionToken = new Token(userDetails[0], this);
            if(isStudent){
                
                    this.vcc.setCurrentUser(new Student(userDetails[0],userDetails[5]));
                   //this.currentUser = new Student(userDetails[0],userDetails[5]);
                
            }else{
                this.vcc.setCurrentUser(new Staff(userDetails[0],userDetails[5]));
               // this.currentUser = new Staff(userDetails[0],userDetails[5]);
            }
            this.attempsLeft = 3;
            return true;
        }
        this.attempsLeft-=1;
        if(this.attempsLeft<=0){
            setCoolDown();
            System.out.println("Due to too many incorrect attempts, logging in is disabled. Please try again in 1 minute");
        }else{
            
            System.out.println("Password incorrect, please try again. You are left with "+ Integer.toString(this.attempsLeft)+" tries.");
        }
    
        return false;
        }catch(Exception e){
            System.out.println(e);
        return false;
    }
    }

    private void setCoolDown(){
        this.underCoolDown = true;
        new Timer().schedule(new TimerTask() {
            public void run(){
                underCoolDown = false;
                attempsLeft = 3;
                System.out.println("You may try logging in again. Enter any input and press enter to continue.");
            }
        }, 60000);
    }

    public boolean isInSession(){
        return this.currentSessionToken!=null;
    }

    

    public void removeToken(){
        this.currentSessionToken = null;
        System.out.println("terminating program as session is over");
        //System.exit(0);
        //this.vcc.navigate(0); 
        this.vcc.navigate(7);
        //this.vcc.navigate(7);
    }
}
