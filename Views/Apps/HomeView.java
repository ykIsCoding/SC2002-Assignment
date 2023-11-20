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

public class HomeView implements IView {
    ViewControllerController vcc;
    
    ArrayList<Action> actions = new ArrayList<>();

    public HomeView(ViewControllerController vcc){
        this.vcc = vcc;
        
        actions.add(new Action("View All Camps",1));
        actions.add(new Action("View My Profile",2));
        actions.add(new Action("Log Out",3));

        if(vcc.getCurrentUser() instanceof Staff){ 

        }else if(vcc.getCurrentUser() instanceof CampCommiteeMember){
           
        }else if(vcc.getCurrentUser() instanceof Student){
            
        }

        
    }

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

    

    public void render(){

        PageUtils.printTitle("Welcome");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
