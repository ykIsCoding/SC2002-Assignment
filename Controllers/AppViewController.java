package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Apps.ProfileView;

public class AppViewController implements IViewController {
    HomeView hv = new HomeView();
    ProfileView pv = new ProfileView();
    
    @Override
    public void initialise(ViewControllerController vcvc) {
        // TODO Auto-generated method stub
        hv.render();
    }
}
