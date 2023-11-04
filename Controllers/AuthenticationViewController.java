package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Authentications.LoginView;

public class AuthenticationViewController implements IViewController {
    LoginView lv = new LoginView();
    @Override
    public void initialise(ViewControllerController vcvc) {
        // TODO Auto-generated method stub
        lv.render();
    }
}
