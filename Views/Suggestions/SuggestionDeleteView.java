package Views.Suggestions;

import Controllers.SuggestionViewController;
import Models.Action;
import Views.Interfaces.IView;

public class SuggestionDeleteView implements IView {
<<<<<<< HEAD
=======
    Action[] actions ={
        new Action("Back To Home", 1),
    };
>>>>>>> 49cbf485cec498b743b5f15002cf077bdaf7390e

     SuggestionViewController svc;

    public SuggestionDeleteView(SuggestionViewController b){
		this.svc =b;
	}

    public void handleInput(int selection) {
        switch(selection){
            //case 1: this.svc.inputToViewControllerController(2);
            default:
                System.out.println("Invalid Selection");
        }
    }
    
    @Override
    public void render() {
        // TODO Auto-generated method stub
        
    }
}
