package Views.Apps;

import Controllers.AppViewController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class ProfileView implements IView {
    AppViewController avc;

    public ProfileView(AppViewController avc){
        this.avc = avc;
    }

    Action actions[] ={
        new Action("Back To Home", 1),
    };

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
         switch(selection){
            case 1: this.avc.inputToWithinViewController(1);
            default:
                System.out.println("Invalid Selection");
        }
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
         PageUtils.printTitle("Profile");
         PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
