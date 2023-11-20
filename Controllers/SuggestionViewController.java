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

    //SuggestionView sv = new SuggestionView(this);
	Suggestion suggestion;
	
	public SuggestionViewController(Suggestion x){
        suggestion = x;
    }
    
    





   

}
