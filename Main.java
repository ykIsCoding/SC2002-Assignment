
import java.util.*;
import javax.swing.*;
import java.net.*;
import Controllers.AuthenticationController;
import Controllers.ViewControllerController;
import Utils.PageUtils;
import java.io.Console;

public class Main {
	
	public static void main(String args[]) {
		boolean terminate = false;
		ViewControllerController vcc = new ViewControllerController();
		AuthenticationController authController = new AuthenticationController(vcc);
		vcc.setCurrentController(0);
		

		//might remove later
		boolean res = false;
		Scanner scnrr = new Scanner(System.in);
		//Console cnsle = System.console();
		
			while(!res){
				System.out.println(authController.isUnderCooldown()?"Login disabled.":"Password:");
				String pw = scnrr.next();
				//String pw = new String(cnsle.readPassword("Enter your password: ")); //might use this instead
				res = authController.authenticate(pw,"anything");
			}
		
		scnrr.close();
		
		
		
		//to change
		while(terminate) {
			
			Scanner scnr = new Scanner(System.in);
			if(scnr.nextInt()==1) {
				vcc.setCurrentController(1);
			}
			if(scnr.nextInt()==-1) {
				System.out.println("exiting");
				break;
			}
			scnr.close();
		}
		
		//HomeView test = new HomeView();
		//test.render();
	}
}

