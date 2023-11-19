package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Enquiries.EnquiryCreateView;
import Views.Enquiries.EnquiryEditView;
import Views.Enquiries.EnquiryListView;
import Views.Enquiries.EnquiryReplyView;
import Views.Enquiries.EnquiryView;

public class EnquiryViewController implements IViewController{
    EnquiryCreateView ecv = new EnquiryCreateView(this);
    EnquiryEditView eev = new EnquiryEditView(this);
    
    EnquiryReplyView erv = new EnquiryReplyView(this);
    
    ViewControllerController vcc;

    

    public void inputToViewControllerController(int x){
        this.vcc.navigate(x);
    }

    public void inputToWithinViewController(int x){
        switch(x){
            case 1: 
                this.ecv.render();
                break;
            case 2: 
                this.eev.render();
                break;
            case 3: 
                this.erv.render();
                break;
            default:
                System.out.println("Invalid Selection."); 
        }
}
}

