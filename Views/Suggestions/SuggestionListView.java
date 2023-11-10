package Views.Suggestions;

import java.util.ArrayList;

import Controllers.SuggestionViewController;
import Controllers.ViewControllerController;
import Models.Action;
import Models.Suggestion;
import Models.SuggestionList;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionListView extends SuggestionList implements IView {
    Action actions[] ={
            new Action("Back To Home", 1),
        };

    Action withSuggestionsActions[]={
        new Action("Back To Home", 1),
        new Action("View Suggestion", 2)
    };
    
    ViewControllerController vcc; //= new SuggestionViewController();
    //SuggestionViewController svc;
    public SuggestionListView(ViewControllerController vcc){
        this.vcc =vcc;
	}

    public void handleInput(int selection) {
        switch(selection){
            case 1: this.vcc.navigate(3); break;
            case 2: 
                System.out.println("Choose the suggestion to view by their number:");
                int sn = InputUtils.tryGetIntSelection(0, getSuggestionList().size()-1);
                SuggestionView sv = new SuggestionView(getSuggestion(sn), this.vcc);
                //svc = new SuggestionViewController(getSuggestion(sn));
                //this.svc.inputToWithinViewController(5);
                //this.svc.inputToWithinViewController(5, getSuggestion(sn));break;
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
        if(allsuggestions.size()==0){
            PageUtils.printTitle("There are currently no suggestions");
        }
        PageUtils.printActionBox((allsuggestions.size()==0)?actions:withSuggestionsActions);
        int choice = InputUtils.tryGetIntSelection(1, 2);
        handleInput(choice);
    }
}
