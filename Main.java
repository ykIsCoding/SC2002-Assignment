
import java.util.*;
import javax.swing.*;
import java.net.*;
import Controllers.AuthenticationController;
import Controllers.ThemeController;
import Controllers.ViewControllerController;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Utils.ThemeUtils;

import java.io.Console;

public class Main {
	
	public static void main(String args[]) {
		
		//ArrayList<String[]> c = DatabaseUtils.getCredentials("./Data/staff_list.txt");
		//for(int b=0;b<c.size();b++){

			//if(c.get(b)[0].equals("BRANDON")){
			//	String[] x = {"BRANDON TAN",c.get(b)[1],c.get(b)[2]};
				//c.set(b, x );
			//}
		//}
		//DatabaseUtils.setCredentials("./Data/staff_list.txt",c);

		boolean terminate = false;
		//InputUtils.initialise();
		ViewControllerController vcc = new ViewControllerController();
		AuthenticationController authController = new AuthenticationController(vcc);
		
		vcc.navigate(0);
		

		//might remove later
		

		
		//to change
	
		
		//HomeView test = new HomeView();
		//test.render();
	}
}

