package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Authentications.LoginView;

public class AuthenticationViewController implements IViewController {
    //LoginView lv = new LoginView(this);
    ViewControllerController vcc;

  

    public void inputToViewControllerController(int x){
        this.vcc.navigate(x);
    }
    
}
