
import java.util.*;

import Controllers.AuthenticationController;
import Controllers.ViewControllerController;

public class Main {
	
	public static void main(String args[]) {
		boolean terminate = false;
		ViewControllerController vcc = new ViewControllerController();
		AuthenticationController authController = new AuthenticationController(vcc);
		vcc.setCurrentController(0);

		//might remove later
		System.out.println("enter your password:");
		Scanner scnrr = new Scanner(System.in);
		String pw = scnrr.next();
		authController.authenticate(pw,"anything");
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

