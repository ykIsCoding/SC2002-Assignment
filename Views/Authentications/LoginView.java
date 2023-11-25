package Views.Authentications;

import Controllers.AuthenticationController;
import Controllers.ThemeController;
import Controllers.ViewControllerController;
import Models.Action;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the LoginView to show the login page for the users
 */
public class LoginView extends AuthenticationController implements IView {
    ThemeController tc;
    ViewControllerController vcc;
    ArrayList<Action> actions = new ArrayList<>();

    /**
     *The handle input function takes in an integer based on what the users enter and controls what the application does based on the choice
     * @param selection is the integer input by the user
     */
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
            case 1: promptCredentials(); break;
            case 2: this.vcc.navigate(7); break;
            case 3: 
                this.tc.toggleCurrentTheme();
                
                this.render();
                break;
        }
    }

    /**
     * This is the function to set up the ThemeController, which will be used to control and light and dark mode of the app
     * @param tc is the ThemeController that is to be used to set in this class.
     */
    public void receiveThemeController(ThemeController tc){
        this.tc = tc;
    }

    /**
     * The LoginView constructor takes in the ViewControllerController as its parameter.
     * This is for navigation purposes within the app.
     * @param vcc
     */
    public LoginView(ViewControllerController vcc){
        super(vcc);
        this.vcc=vcc;

        this.actions.add(new Action("Log In",1));
        this.actions.add(new Action("Close Program",2));
        this.actions.add(new Action((this.tc!=null && this.tc.isDarkMode())?"Toggle Light Mode":"Toggle Dark Mode", 3));

    }

    /**
     * The function is to get the user to input their credentials and communicate with the user. This function will decide how tha app will behave.
     */
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
                        if(DatabaseUtils.checkIfUserLogsInForFirstTime(this.vcc.getCurrentUser().getUserID())){
                            System.out.println("As this is your first time logging in, please enter a new password:");
                            String npw = InputUtils.tryGetPassword();
                            if(npw!=null){
                                String s = DatabaseUtils.generateS().toString();
                               
                                String hpw = DatabaseUtils.hashPassword(npw, s);
                                String[] userData = DatabaseUtils.getUserByID(this.vcc.getCurrentUser().getUserID());
                                userData[4]= hpw;
                                userData[8]=s;
                                userData[9]="1";
                                DatabaseUtils.setUserByID(userData[0],userData);
                                System.out.println("Password updated!");
                                //do save to database
                            }
                        }
					    this.vcc.navigate(3);
					    break;
				    }
                }
			}
		
		scnrr.close();
    }

    /**
     * The render function outputs what is shown to the user and also sets up the business logic of getting an input from the user.
     */
    public void render(){
        PageUtils.printTitle("Camp Application Management System");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 3);
        handleInput(choice);
    }
}
