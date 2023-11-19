package Views.Apps;
import java.util.ArrayList;

import Controllers.ViewControllerController;
import Models.Action;
import Models.CampCommiteeMember;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class ProfileView implements IView {
    ViewControllerController vcc;
    ArrayList<Action> actions = new ArrayList();

    public ProfileView(ViewControllerController vcc){
        this.vcc = vcc;
        this.actions.add(new Action("Back To Home",1));
        this.actions.add(new Action("Change Password",2));
    }


    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
         switch(selection){
            case 1: this.vcc.navigate(3);break;
            case 2: 
                System.out.println("Enter a new password");
                String npw = InputUtils.tryGetPassword();
                if(npw!=null){
                    String s = DatabaseUtils.generateS().toString();
                   
                    String pw = DatabaseUtils.hashPassword(npw, s);
                    String[] userData = DatabaseUtils.getUserByID(this.vcc.getCurrentUser().getUserID());
                    userData[4]= pw;
                    userData[8]=s;
                    DatabaseUtils.setUserByID(userData[0],userData);
                    System.out.println("Password updated!");
                    //do save to database
                }
                render();
                break;
            default:
                System.out.println("Invalid Selection");
        }
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
        String[] userData = DatabaseUtils.getUserByID(this.vcc.getCurrentUser().getUserID());
         PageUtils.printTitle("Profile");
         System.out.println("Name:");
         System.out.println(userData[1]);
         System.out.println("Email:");
         System.out.println(userData[2]);
         System.out.println("Faculty:");
         System.out.println(userData[5]);
         System.out.println("UserName:");
         System.out.println(userData[3]);
         if(this.vcc.getCurrentUser() instanceof CampCommiteeMember){
            System.out.println("Points:");
            System.out.println(userData[6]);
         }
         PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
