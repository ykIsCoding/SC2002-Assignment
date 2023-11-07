
import java.util.*;
import javax.swing.*;
import java.net.*;
import Controllers.AuthenticationController;
import Controllers.ViewControllerController;
import Utils.DatabaseUtils;
import Utils.PageUtils;
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
		AuthenticationController authController = new AuthenticationController();
		ViewControllerController vcc = new ViewControllerController(authController);
		vcc.navigate(0);

		//might remove later
		

		
		//to change
	
		
		//HomeView test = new HomeView();
		//test.render();
	}
}

