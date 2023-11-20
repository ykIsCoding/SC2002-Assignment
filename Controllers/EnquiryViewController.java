package Controllers;

import Controllers.Interfaces.IViewController;
import Views.Enquiries.EnquiryCreateView;
import Views.Enquiries.EnquiryEditView;
import Views.Enquiries.EnquiryListView;
import Views.Enquiries.EnquiryReplyView;
import Views.Enquiries.EnquiryView;

public class EnquiryViewController implements IViewController{

    ViewControllerController vcc;

    

    public void inputToViewControllerController(int x){
        this.vcc.navigate(x);
    }


}

