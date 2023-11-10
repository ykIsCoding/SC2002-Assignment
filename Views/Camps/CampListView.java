package Views.Camps;

import Controllers.CampViewController;
import Controllers.SuggestionViewController;
import Controllers.ViewControllerController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class CampListView implements IView {
    ViewControllerController vcc;
    CampViewController cvc;

    Action actions[] ={
        new Action("Back To Home", 1),
    };

    public CampListView(ViewControllerController vcc){
        this.vcc=vcc;
	
	}

    public void handleInput(int selection) {
        switch(selection){
            case 1:this.vcc.navigate(3);break;
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        PageUtils.printTitle("All Camps");
        PageUtils.printTitle("There are currently no camps");
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
