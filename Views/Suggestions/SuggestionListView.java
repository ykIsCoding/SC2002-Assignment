package Views.Suggestions;

import java.util.ArrayList;

import Controllers.SuggestionViewController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionListView implements IView {
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
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
