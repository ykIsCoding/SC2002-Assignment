package Views.Suggestions;

import Controllers.SuggestionViewController;
import Models.Action;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionView implements IView {
    SuggestionViewController svc;
    Action createSuggestionAction = new Action("Create Suggestion", 1);
    Action editSuggestionAction = new Action("Edit Suggestion", 2);
    Action deleteSuggestionAction=   new Action("Delete Suggestion", 3);
    Action approveSuggestionAction=   new Action("Approve Suggestion", 4);
    Action actions[] ={
        createSuggestionAction,
        editSuggestionAction,
        deleteSuggestionAction,
        approveSuggestionAction
    };

    public SuggestionView(SuggestionViewController b){
		this.svc =b;
	}

    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
        case 1: 
            this.svc.inputToWithinViewController(2);
            break;
        case 2: 
            this.svc.inputToWithinViewController(3);
            break;
        case 3: 
            this.svc.inputToWithinViewController(4);
            break;
        case 4: 
            this.svc.inputToWithinViewController(1);
            break;
        default:
            System.out.println("Invalid selection.");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        PageUtils.printTitle("Suggestion");
    
    }
}
