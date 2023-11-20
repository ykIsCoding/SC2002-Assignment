package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Camps.CampCreateView;
import Views.Camps.CampDeregisterView;
import Views.Camps.CampEditView;
import Views.Camps.CampListView;
import Views.Camps.CampRegisterView;
import Views.Camps.CampView;

public class CampViewController implements IViewController {

    ViewControllerController vcc;

    //might move to a parent class
    public void inputToViewControllerController(int x){
        this.vcc.navigate(x);
    }
    



   
}
