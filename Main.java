
import java.util.*;
import javax.swing.*;
import java.net.*;
import Controllers.AuthenticationController;
import Controllers.ViewControllerController;

public class Main {
	
	public static void main(String args[]) {
		boolean terminate = false;
		ViewControllerController vcc = new ViewControllerController();
		AuthenticationController authController = new AuthenticationController(vcc);
		vcc.setCurrentController(0);

		

		//might remove later
		boolean res = false;
		Scanner scnrr = new Scanner(System.in);
		while(!res){
			System.out.println(authController.isUnderCooldown()?"Login disabled.":"enter your password:");
			String pw = scnrr.next();
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

