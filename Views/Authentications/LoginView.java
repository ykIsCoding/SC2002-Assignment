package Views.Authentications;
import java.util.ArrayList;
import java.util.Scanner;

import Controllers.AuthenticationController;
import Controllers.AuthenticationViewController;
import Controllers.ThemeController;
import Controllers.ViewControllerController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Utils.ThemeUtils;
import Views.Interfaces.IView;

public class LoginView extends AuthenticationController implements IView {
    ThemeController tc;
    ViewControllerController vcc;
    Action actions[] ={
        new Action("Log In", 1),
        new Action("Close Program", 2),
        new Action("Toggle Light/Dark Mode", 3)
    };

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
            case 1: promptCredentials(); break;
            case 2: this.vcc.navigate(7); break;
            case 3: 
                this.tc.toggleCurrentTheme();
                this.tc.printSwitch();
                this.render();
                break;
        }
    }

    public void receiveThemeController(ThemeController tc){
        this.tc = tc;
    }

    public LoginView(ViewControllerController vcc){
        this.vcc=vcc;
    }

    public void promptCredentials(){
        boolean res = false;
		Scanner scnrr = new Scanner(System.in);
		//Console cnsle = System.console();
		
			while(!res){
				System.out.println(isUnderCooldown()?"Login disabled.":"Password:");
				String pw = scnrr.next();
				//String pw = new String(cnsle.readPassword("Enter your password: ")); //might use this instead
				if(authenticate(pw,"anything")){
					this.vcc.navigate(3);
					break;
				};
			}
		
		scnrr.close();
    }

    public void render(){
        PageUtils.printTitle("Camp Application Management System");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 3);
        handleInput(choice);
    }
}
