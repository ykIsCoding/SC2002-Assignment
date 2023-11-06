package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Camps.CampCreateView;
import Views.Camps.CampDeregisterView;
import Views.Camps.CampEditView;
import Views.Camps.CampListView;
import Views.Camps.CampRegisterView;
import Views.Camps.CampView;

public class CampViewController implements IViewController {
    CampCreateView ccc = new CampCreateView(this);
    CampListView clc = new CampListView(this);
    CampEditView cec = new CampEditView(this);
    CampRegisterView crc = new CampRegisterView(this);
    CampDeregisterView cdrc = new CampDeregisterView(this);
    CampView cv = new CampView(this);
    ViewControllerController vcc;

    //might move to a parent class
    public void inputToViewControllerController(int x){
        this.vcc.setCurrentController(x);
    }
    

    public void inputToWithinViewController(int x){
        switch(x){
            case 1: 
                this.ccc.render();
                break;
            case 2: 
                this.clc.render();
                break;
            case 3: 
                this.cec.render();
                break;
            case 4: 
                this.crc.render();
                break;
            case 5: 
                this.cdrc.render();
                break;
            default:
                System.out.println("Invalid Selection.");
        }
    }

    @Override
    public void initialise(ViewControllerController vcvc) {
        // TODO Auto-generated method stub
        this.vcc= vcvc;
        clc.render();
    }
}
