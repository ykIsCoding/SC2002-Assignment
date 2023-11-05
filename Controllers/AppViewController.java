package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Apps.ProfileView;

public class AppViewController implements IViewController {
    HomeView hv =new HomeView(this);
    ProfileView pv = new ProfileView();
    ViewControllerController vcc;
    
    @Override
    public void initialise(ViewControllerController vcvc) {
        // TODO Auto-generated method stub
        this.vcc = vcvc;
        hv.render();
    }

    public void inputToViewControllerController(int x){
        this.vcc.setCurrentController(x);
    }
}
