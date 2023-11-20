package Models;

import Controllers.AuthenticationController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Token {
    private final UUID tokenID;
    private final String userID;
    private final Timer timer;
    private final int period =3600000;
    private final AuthenticationController authcntrlr;

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
        //System.out.println("logging out");
        this.timer.cancel();
        authcntrlr.removeToken();
        
    }
}
