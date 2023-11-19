package Controllers;

import Controllers.Interfaces.IViewController;
import Models.CampCommiteeMember;
import Models.Staff;
import Models.Student;
import Models.Abstract.AUser;
import Views.Apps.HomeView;
import Views.Apps.ProfileView;
import Views.Authentications.LoginView;
import Views.Camps.CampListView;
import Views.Suggestions.SuggestionListView;

public class ViewControllerController{
    AUser currentUser;
    ThemeController tc = new ThemeController(); 
    LoginView lv = new LoginView(this);
    SuggestionListView svl;
    CampListView clv;
    HomeView hv;
    ProfileView pv;

    public ViewControllerController(){
           
    }

    public AUser getCurrentUser(){
        return this.currentUser; 
    }

    public void setCurrentUser(Staff x){
        this.currentUser = x;
    }

    public void setCurrentUser(CampCommiteeMember x){
        this.currentUser = x;
    }

    public void setCurrentUser(Student x){
        this.currentUser = x;
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
                //svl = new SuggestionListView(this);
                //svl.render();
                break;
            case 2: 
                clv = new CampListView(this);
                clv.render();
                break;
			case 3: 
                hv = new HomeView(this);
                hv.render();
                break;
			case 4: 
               // pv = new ProfileView(this);
                //pv.render();
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
