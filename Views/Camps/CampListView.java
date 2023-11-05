package Views.Camps;

import Controllers.CampViewController;
import Controllers.SuggestionViewController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class CampListView implements IView {
    Action actions[] ={
        new Action("Back To Home", 1),
    };
    CampViewController cvc;

    public CampListView(CampViewController b){
		this.cvc =b;
	}

    public void handleInput(int selection) {
        switch(selection){
            case 1: this.cvc.inputToViewControllerController(2);
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        PageUtils.printTitle("All Camps");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
