package Views.Authentications;
import java.util.ArrayList;
import java.util.Scanner;

import Controllers.AuthenticationController;
import Controllers.AuthenticationViewController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class LoginView extends AuthenticationController implements IView {
    AuthenticationViewController avc;
    Action actions[] ={
        new Action("Log In", 1),
        new Action("Close Program", 2)
    };

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
            case 1: promptCredentials(); break;
            case 2: this.avc.inputToViewControllerController(7);
        }
    }

    public LoginView(AuthenticationViewController avc){
        this.avc=avc;
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
					this.avc.inputToViewControllerController(2);
					break;
				};
			}
		
		scnrr.close();
    }

    public void render(){
        PageUtils.printTitle("Camp Application Management System");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 2);
        handleInput(choice);
    }
}
