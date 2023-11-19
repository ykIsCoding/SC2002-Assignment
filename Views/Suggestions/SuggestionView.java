package Views.Suggestions;


import Controllers.SuggestionViewController;
import Controllers.ViewControllerController;
import Models.Action;
import Models.Suggestion;
import Models.SuggestionList;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionView extends SuggestionList implements IView {
    SuggestionViewController svc;
    Suggestion currSuggestion;
    ViewControllerController vcc;

    Action GoBack = new Action("Back to all suggestions", 1);
    Action editSuggestionAction = new Action("Edit Suggestion", 2);
    Action deleteSuggestionAction=   new Action("Delete Suggestion", 3);
    Action approveSuggestionAction=   new Action("Approve Suggestion", 4);
    Action actions[] ={
        GoBack,
        editSuggestionAction,
        deleteSuggestionAction,
        approveSuggestionAction
    };

    public SuggestionView(Suggestion b, ViewControllerController vcc){
        super(b.getSuggestionCamp().getCampID());
		this.currSuggestion =b;
        this.vcc = vcc;
        this.svc = new SuggestionViewController(b);
        render();
	}

    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
        case 1: 
            this.vcc.navigate(1);
            break;
        case 2: 
         //   this.svc.inputToWithinViewController(3);
            break;
        case 3: 
          //  this.svc.inputToWithinViewController(4);
            break;
        case 4: 
          //  this.svc.inputToWithinViewController(1);
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
        
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
