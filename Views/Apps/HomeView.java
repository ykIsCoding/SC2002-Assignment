package Views.Apps;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.util.ArrayList;

import Controllers.AuthenticationController;
import Controllers.CampViewController;
import Controllers.ViewControllerController;
import Models.Action;

public class HomeView implements IView {
    ViewControllerController vcc;
    
    Action actions[] = {
        new Action("View All Camps", 1), 
        new Action("View My Camps", 2),
        new Action("View My Profile",3),
        new Action("View My Suggestions",4),
         new Action("Log Out",5),
    };

    public HomeView(ViewControllerController vcc){
        this.vcc = vcc;
        
    }

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch (selection) {
            case 1:
                this.vcc.navigate(2);
                break;
            case 2:
                break;
            case 3:
                this.vcc.navigate(4);
                break;
            case 4:
                this.vcc.navigate(1);
                break;
            case 5:
                System.out.println("logging out");
                this.vcc.navigate(0);
                break;
            default:
                break;
        }
        
    }

    

    public void render(){

        PageUtils.printTitle("Welcome");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 5);
        handleInput(choice);
    }
}
