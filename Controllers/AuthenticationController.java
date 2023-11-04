package Controllers;

import Models.Token;

public class AuthenticationController {
    int maxAttempts = 3;
    String password = "password";
    Token currentSessionToken = null;
    String UserID;
    ViewControllerController vcc;

    public AuthenticationController(ViewControllerController vcc){
		this.vcc = vcc;
	}

    public boolean authenticate(String inputPassword, String email){
        if(inputPassword.equals(this.password)){
            this.currentSessionToken = new Token("55", this);
            vcc.setCurrentController(2);
            return true;
        }
        return false;
    }

    public void removeToken(){
        this.currentSessionToken = null;
        vcc.setCurrentController(0);
    }
}
