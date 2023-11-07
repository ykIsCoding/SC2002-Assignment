package Views.Suggestions;

import java.util.ArrayList;

import Controllers.SuggestionViewController;
import Models.Action;
import Models.Suggestion;
import Models.SuggestionList;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionListView extends SuggestionList implements IView {
    Action actions[] ={
        new Action("Back To Home", 1),
        new Action("View Suggestion", 2),
    };
    SuggestionViewController svc; //= new SuggestionViewController();

    public SuggestionListView(SuggestionViewController n){
        this.svc =n;
	}

    public void handleInput(int selection) {
        switch(selection){
            case 1: this.svc.inputToViewControllerController(2); break;
            case 2: 
                System.out.println("Choose the suggestion to view by their number:");
                int sn = InputUtils.tryGetIntSelection(0, getSuggestionList().size()-1);
                this.svc.inputToWithinViewController(5, getSuggestion(sn));break;
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        PageUtils.printTitle("All Suggestions");

        ArrayList<Suggestion> allsuggestions = getSuggestionList();
        for(int n=0;n<allsuggestions.size();n++){
            PageUtils.printRow(n,allsuggestions.get(n).getTitle());
        }
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 2);
        handleInput(choice);
    }
}
