package Views.Apps;

import Controllers.ViewControllerController;
import Models.Action;
import Models.CampCommiteeMember;
import Models.Staff;
import Models.Student;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.util.ArrayList;

/**
 * This is the HomeView class to display the home page for the users
 */
public class HomeView implements IView {
    ViewControllerController vcc;
    
    ArrayList<Action> actions = new ArrayList<>();

    /**
     * The HomeView constructor takes in the ViewControllerController as its parameter.
     * This is for navigation purposes within the app.
     * @param vcc is the ViewControllerController
     */
    public HomeView(ViewControllerController vcc){
        this.vcc = vcc;
        actions.add(new Action("View All Camps",1));
        actions.add(new Action("View My Profile",2));
        actions.add(new Action("Log Out",3));
    }

    /**
     * The handle input function takes in an integer based on what the users enter and controls what the application does based on the choice
     * @param selection is the integer based on the choice of the user.
     */
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch (selection) {
            case 1:
                this.vcc.navigate(2);
                break;
            case 2:
                ProfileView pv = new ProfileView(this.vcc);
                pv.render();
                break;
            case 3:
                System.out.println("logging out");
                this.vcc.navigate(0);
                break;
            case 4:
                this.vcc.navigate(2);
                break;
            case 5:
                this.vcc.navigate(1);
                break;
            default:
                break;
        }
        
    }

    /**
     * The render function outputs what is shown to the user and also sets up the business logic of getting an input from the user.
     */
    public void render(){

        PageUtils.printTitle("Welcome");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
