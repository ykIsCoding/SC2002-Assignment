package Views.Enquiries;

import Controllers.EnquiryViewController;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class EnquiryReplyView implements IView {

    EnquiryViewController evc;

    public EnquiryReplyView(EnquiryViewController evc){
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
         PageUtils.printTitle("Replying to Enquiry");
    }
}
