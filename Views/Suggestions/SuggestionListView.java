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
    };
    SuggestionViewController svc;

    public SuggestionListView(SuggestionViewController b){
		this.svc =b;
	}

    public void handleInput(int selection) {
        switch(selection){
            case 1: this.svc.inputToViewControllerController(2);
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
            PageUtils.printSuggestionBox(allsuggestions.get(n),"yong kang","Student");
        }
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
