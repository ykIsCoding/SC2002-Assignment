package Controllers;

import Models.Abstract.AUser;
import Models.CampCommiteeMember;
import Models.Staff;
import Models.Student;
import Views.Apps.HomeView;
import Views.Apps.ProfileView;
import Views.Authentications.LoginView;
import Views.Camps.CampListView;
import Views.Suggestions.SuggestionListView;

/**
 * ViewControllerController is used to control the views within the app
 */
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

    /**
     * get the current user that is logged in
     * @return
     */
    public AUser getCurrentUser(){
        return this.currentUser; 
    }

    /**
     * set the current user to a staff
     * @param x current user
     */
    public void setCurrentUser(Staff x){
        this.currentUser = x;
    }

    /**
     * set the current user to a camp committee member
     * @param x staff
     */
    public void setCurrentUser(CampCommiteeMember x){
        this.currentUser = x;
    }

    /**
     * set the current user to a student
     * @param x student
     */
    public void setCurrentUser(Student x){
        this.currentUser = x;
    }

    /**
     * navigate function that takes in an integer and navigates the views
     * @param num the integer that determines the views to render
     */
    public void navigate(int num){
        tc.start();
        switch(num) {
			case 0: 
                lv.receiveThemeController(tc);
                lv.render();
                break;
			case 1:
                break;
            case 2: 
                clv = new CampListView(this);
                clv.render();
                break;
			case 3: 
                hv = new HomeView(this);
                hv.render();
                break;
            case 7: System.exit(0);
            default:
                System.out.println("error");
		}
        ThemeController.end();
    }
}
