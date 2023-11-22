import Controllers.AuthenticationController;
import Controllers.ViewControllerController;

public class App {
    public void start(){
    boolean terminate = false;
		//InputUtils.initialise();
		ViewControllerController vcc = new ViewControllerController();
		AuthenticationController authController = new AuthenticationController(vcc);
		
		vcc.navigate(0);
    }
}
