package Views.Apps;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.util.ArrayList;

import Controllers.AppViewController;
import Controllers.CampViewController;
import Controllers.ViewControllerController;
import Models.Action;

public class HomeView implements IView {
    AppViewController avc;
    Action actions[] = {
        new Action("View All Camps", 1), 
        new Action("View My Camps", 2),
        new Action("View My Profile",3),
        new Action("View My Enquiries",4),
        new Action("View My Suggestions",5),
    };

    public HomeView(AppViewController avc){
        this.avc = avc;
    }

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        
    }

    

    public void render(){
        PageUtils.printTitle("Welcome");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 5);
        switch (choice) {
            case 1:
                avc.inputToViewControllerController(5);
                break;
            case 2:
                avc.inputToViewControllerController(5);
                break;
            case 3:
                avc.inputToViewControllerController(5);
                break;
            case 4:
                avc.inputToViewControllerController(5);
                break;
            case 5:
                System.out.println("selected");
                avc.inputToViewControllerController(3);
                break;
            default:
                break;
        }
    }
}
