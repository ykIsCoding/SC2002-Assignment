
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
		AuthenticationController authController = new AuthenticationController();
		ViewControllerController vcc = new ViewControllerController(authController);
		vcc.setCurrentController(0);

		//might remove later
		
		
		
		//to change
	
		
		//HomeView test = new HomeView();
		//test.render();
	}
}

