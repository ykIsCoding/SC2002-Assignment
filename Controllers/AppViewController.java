package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Apps.ProfileView;


public class AppViewController implements IViewController {
    HomeView hv =new HomeView(this);
    ProfileView pv = new ProfileView(this);
    ViewControllerController vcc;
    
    @Override
    public void initialise(ViewControllerController vcvc) {
        // TODO Auto-generated method stub
        this.vcc = vcvc;
        hv.render();
    }

    public void inputToWithinViewController(int x){
        switch(x){
            case 1: 
                this.hv.render();
                break;
            case 2: 
                this.pv.render();
                break;
            case 3: 
               // this.adv.render();
                break;
            default:
                System.out.println("Invalid Selection"); 
        }
    }

    public void inputToViewControllerController(int x){
        this.vcc.setCurrentController(x);
    }
}
