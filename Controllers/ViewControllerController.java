package Controllers;

import Controllers.Interfaces.IViewController;

public class ViewControllerController {
    AuthenticationViewController authvc;
	SuggestionViewController svc;
    AppViewController avc;
    EnquiryViewController evc;
    ProfileViewController pvc;

    public ViewControllerController(){
        authvc = new AuthenticationViewController();
        svc = new SuggestionViewController();
        avc = new AppViewController();
        evc =new EnquiryViewController();
        pvc = new ProfileViewController();
    }

    public void setCurrentController(int num){
        switch(num) {
			case 0: authvc.initialise(this);break;
			case 1: svc.initialise(this);break;
            case 2: avc.initialise(this);break;
			case 3: svc.initialise(this);break;
			case 4: pvc.initialise(this);break;
		}
    }

    
    
}
