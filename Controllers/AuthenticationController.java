package Controllers;

import java.util.Timer;
import java.util.TimerTask;

import Models.Token;

public class AuthenticationController {
    int attempsLeft = 3;
    String password = "password";
    Token currentSessionToken = null;
    String UserID;
   // ViewControllerController vcc;
    boolean underCoolDown = false;

    public AuthenticationController(){
		
	}

    public boolean isUnderCooldown(){
        return this.underCoolDown;
    }

    public boolean authenticate(String inputPassword, String email){
        if(underCoolDown) return false;
        if(inputPassword.equals(this.password)){
            this.currentSessionToken = new Token("55", this);
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
    }

    private void setCoolDown(){
        this.underCoolDown = true;
        new Timer().schedule(new TimerTask() {
            public void run(){
                underCoolDown = false;
                attempsLeft = 3;
                System.out.println("You may try logging in again.");
            }
        }, 60000);
    }

    public void removeToken(){
        this.currentSessionToken = null;
    }
}
