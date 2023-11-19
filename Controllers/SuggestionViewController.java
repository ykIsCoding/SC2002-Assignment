package Controllers;

import Controllers.Interfaces.IViewController;
import Models.Suggestion;
import Views.Suggestions.SuggestionApproveView;
import Views.Suggestions.SuggestionCreateView;
import Views.Suggestions.SuggestionDeleteView;
import Views.Suggestions.SuggestionEditView;
import Views.Suggestions.SuggestionListView;
import Views.Suggestions.SuggestionView;

public class SuggestionViewController implements IViewController{
    SuggestionEditView sev = new SuggestionEditView(this);
    SuggestionDeleteView sdv = new SuggestionDeleteView(this);
    SuggestionCreateView scv = new SuggestionCreateView(this);
    SuggestionApproveView sav = new SuggestionApproveView(this);
    //SuggestionView sv = new SuggestionView(this);
	Suggestion suggestion;
	
	public SuggestionViewController(Suggestion x){
        suggestion = x;
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
                //this.sv.render();
                break;
            default:
                System.out.println("Invalid Selection. Redirecting to Suggestion List View"); 
                //this.svl.render();
        }
    }

    public Suggestion getCurrentSuggestion(){
        return this.suggestion;
    }

   

}
