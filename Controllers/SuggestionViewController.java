package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Suggestions.SuggestionApproveView;
import Views.Suggestions.SuggestionCreateView;
import Views.Suggestions.SuggestionDeleteView;
import Views.Suggestions.SuggestionEditView;
import Views.Suggestions.SuggestionListView;
import Views.Suggestions.SuggestionView;

public class SuggestionViewController implements IViewController{
    SuggestionEditView sev = new SuggestionEditView();
    SuggestionDeleteView sdv = new SuggestionDeleteView();
    SuggestionCreateView scv = new SuggestionCreateView();
    SuggestionApproveView sav = new SuggestionApproveView();
    SuggestionListView svl = new SuggestionListView(this);;
    SuggestionView sv = new SuggestionView(this);
	ViewControllerController vcc;
	
	SuggestionViewController(){}
    
    @Override
    public void initialise(ViewControllerController vcc) {
        // TODO Auto-generated method stub
        this.vcc = vcc;
        this.svl.render();
    }

    public void inputToWithinViewController(int x){
        switch(x){
            case 1: 
                this.sav.render();
                break;
            case 2: 
                this.scv.render();
                break;
            case 3: 
                this.sdv.render();
                break;
            case 4: 
                this.sev.render();
                break;
            case 5: 
                this.sv.render();
                break;
            default:
                System.out.println("Invalid Selection. Redirecting to Suggestion List View"); 
                this.svl.render();
        }
    }

    public void inputToViewControllerController(int x){
        this.vcc.setCurrentController(x);
    }

}
