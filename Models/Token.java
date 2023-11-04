package Models;
import java.util.UUID;

import Controllers.AuthenticationController;

import java.util.Timer;
import java.util.TimerTask;

public class Token {
    private UUID tokenID;
    private String userID;
    private Timer timer;
    private int period =3600000; 
    private AuthenticationController authcntrlr;

    public Token(String userID, AuthenticationController authcontroller){
        this.tokenID = UUID.randomUUID();
        this.userID = userID;
        this.authcntrlr = authcontroller;
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run(){
                endSession();
            }
        }, period);
    }

    public void endSession(){
        System.out.println("logging out");
        this.timer.cancel();
        authcntrlr.removeToken();
    }
}
