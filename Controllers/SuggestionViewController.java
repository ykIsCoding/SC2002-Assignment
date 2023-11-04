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
    SuggestionView sv = new SuggestionView();
	ViewControllerController vcc;
	
	SuggestionViewController(){}
	
	SuggestionViewController(ViewControllerController vcc){
		this.vcc = vcc;
	}
    
    @Override
    public void initialise(ViewControllerController vcc) {
        // TODO Auto-generated method stub
        this.svl.render();
    }
}
