package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Authentications.LoginView;

public class AuthenticationViewController implements IViewController {
    LoginView lv = new LoginView(this);
    ViewControllerController vcc;

    @Override
    public void initialise(ViewControllerController vcvc) {
        // TODO Auto-generated method stub
        this.vcc=vcvc;
        lv.render();
    }

    public void inputToViewControllerController(int x){
        this.vcc.setCurrentController(x);
    }
    
}
