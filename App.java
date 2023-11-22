import Controllers.AuthenticationController;
import Controllers.ViewControllerController;

/**
 * The App class contains the entire app
 */
public class App {
    /**
     * The start function initialises the entire app
     */
    public void start(){
    boolean terminate = false;
		//InputUtils.initialise();
		ViewControllerController vcc = new ViewControllerController();
		AuthenticationController authController = new AuthenticationController(vcc);
		vcc.navigate(0);
    }
}
