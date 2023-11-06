package Views.Enquiries;

import Controllers.EnquiryViewController;
import Models.Action;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class EnquiryListView implements IView {
    EnquiryViewController evc;
    Action actions[] ={
        new Action("Back To Home", 1),
    };

    public EnquiryListView(EnquiryViewController evc){
        this.evc =evc;
    }

    public void handleInput(int selection) {
        switch(selection){
            case 1: this.evc.inputToViewControllerController(2);
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
         PageUtils.printTitle("All Enquiries");
         PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, 1);
        handleInput(choice);
    }
}
