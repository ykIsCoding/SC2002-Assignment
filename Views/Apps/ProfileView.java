package Views.Apps;
import Controllers.ViewControllerController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class ProfileView implements IView {
    ViewControllerController vcc;

    public ProfileView(ViewControllerController vcc){
        this.vcc = vcc;
    }

    Action actions[] ={
        new Action("Back To Home", 1),
    };

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
         switch(selection){
            case 1: this.vcc.navigate(3);;
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
