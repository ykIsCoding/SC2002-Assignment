package Models;

import Controllers.AuthenticationController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Token class that is issued to user after authentication success.
 */
public class Token {
    private final UUID tokenID;
    private final String userID;
    private final Timer timer;
    private final int period =3600000;
    private final AuthenticationController authcntrlr;

    /**
     * constructor takes in userid of the user who was authenticated, and authentication controller to control the program flow
     * @param userID
     * @param authcontroller
     */
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

    /**
     * ends the session of the user, called when user logs out or times out
     */
    public void endSession(){
        //System.out.println("logging out");
        this.timer.cancel();
        authcntrlr.removeToken();
        
    }
}
