package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Apps.HomeView;
import Views.Apps.ProfileView;
import Views.Authentications.LoginView;
import Views.Camps.CampListView;
import Views.Suggestions.SuggestionListView;

public class ViewControllerController {
    ThemeController tc = new ThemeController();
    
    LoginView lv = new LoginView(this);
    SuggestionListView svl = new SuggestionListView(this);
    CampListView clv = new CampListView(this);
    HomeView hv = new HomeView(this);
    ProfileView pv = new ProfileView(this);

    public ViewControllerController(){
           
    }

    public void navigate(int num){
        tc.start();
        //tc.printSwitch();
        switch(num) {
			case 0: 
                lv.receiveThemeController(tc);
                lv.render();
                break;
			case 1: 
                svl.render();
                break;
            case 2: 
                clv.render();
                break;
			case 3: 
                hv.render();
                break;
			case 4: 
                pv.render();
                break;
            case 5: ;break;
            case 6: ;break;
            case 7: System.exit(0);
            default:
                System.out.println("error");
		}
        tc.end();
        return;
    }
}
