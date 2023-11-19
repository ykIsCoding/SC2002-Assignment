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
    ArrayList<Action> actions = new ArrayList<>();

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
        super(vcc);
        this.vcc=vcc;

        this.actions.add(new Action("Log In",1));
        this.actions.add(new Action("Close Program",2));
        this.actions.add(new Action((this.tc!=null && this.tc.isDarkMode())?"Toggle Light Mode":"Toggle Dark Mode", 3));

    }

    public void promptCredentials(){
        boolean res = false;
		Scanner scnrr = new Scanner(System.in);
		//Console cnsle = System.console();
        String email = "";
		
			while(!res){
                if(isUnderCooldown()){
                    System.out.println("Login disabled.");
                    scnrr.next();
                    continue;
                }else{
                    if(email.isBlank()){
                        System.out.println("Enter email:");
                        email = InputUtils.tryGetEmail();
                    }
                    System.out.println("Enter password:");
				    String pw = scnrr.next();
				    //String pw = new String(cnsle.readPassword("Enter your password: ")); //might use this instead
				    if(authenticate(pw,email.toLowerCase())){
					    this.vcc.navigate(3);
					    break;
				    };
                }
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
