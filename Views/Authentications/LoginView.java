package Views.Authentications;
import java.util.ArrayList;

import Utils.PageUtils;
import Views.Interfaces.IView;

public class LoginView implements IView {
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        
    }
    public void render(){
       //dummy array to remove later
        ArrayList<String> actions = new ArrayList();
        actions.add("student");
        actions.add("staff");

        PageUtils.printTitle("Camp Application Management System");
        System.out.println("Log in to CAMS");
        
    }
}
