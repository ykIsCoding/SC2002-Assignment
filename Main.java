import Controllers.AuthenticationController;
import Controllers.ViewControllerController;

public class Main {
	
	public static void main(String[] args) {
		//ArrayList<String[]> c = DatabaseUtils.getCredentials("./Data/student_list.txt");
		//ArrayList<String[]> p = new ArrayList<>();
	//for(int b=0;b<c.size();b++){
			//byte[] s = DatabaseUtils.generateS();
			//c.get(b)[4]=DatabaseUtils.hashPassword("password",s.toString());
			//c.get(b)[8]=s.toString();
			//System.out.println(c.get(b)[4]);
			//if(c.get(b)[0].equals("BRANDON")){
			//	String[] x = {"BRANDON TAN",c.get(b)[1],c.get(b)[2]};
				//c.set(b, x );
			//}
		//}
		//DatabaseUtils.setCredentials("./Data/student_list.txt",c);
		//String password1 = DatabaseUtils.hashPassword("password",c.get(0)[8].getBytes() );
		//System.out.println(password1);
		//System.out.println(DatabaseUtils.checkPassword("password",c.get(1)[4]));

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

