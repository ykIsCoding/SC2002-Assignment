package Views.Enquiries;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import Controllers.EnquiryViewController;
import Controllers.ViewControllerController;
import Models.Action;
import Models.Camp;
import Models.CampCommiteeMember;
import Models.Enquiry;
import Models.EnquiryList;
import Models.EnquiryResponse;
import Models.Staff;
import Models.Student;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class EnquiryView extends Enquiry implements IView{

    EnquiryViewController evc;
    ViewControllerController vcc;
    String currentViewerPosition;
    
    private ArrayList<Action> actions = new ArrayList<>();

    public EnquiryView(Enquiry e, ViewControllerController vcc){
        super(e.getContent(),e.getTimestamp(),e.getEnquiryID(),e.getCampID(),e.getUserID());
        
        this.vcc = vcc;
        this.actions.add(new Action("Back To Home", 1));
        if(getEnquiryResponseList().getResponseCount()>0){
            this.actions.add(new Action("Upvote Reply", 3));
        }
        if(vcc.getCurrentUser() instanceof Staff){
            this.currentViewerPosition = "Staff";
            this.actions.add(new Action("Answer Enquiry", 2));
        }else if(vcc.getCurrentUser() instanceof Student){
            this.currentViewerPosition = "Student";
            if(DatabaseUtils.checkIfStudentIsCampCommitteeMember(vcc.getCurrentUser().getUserID(),getCampID())){
                this.actions.add(new Action("Answer Enquiry", 2));
                this.currentViewerPosition = "Camp Committee Member";
            }
        }
    }

    public void handleInput(int selection) {
        switch(selection){
            case 1: 
                this.vcc.navigate(3);
                break;
            case 2:
                System.out.println("Enter a reply:");
                String reply = InputUtils.tryGetString();
                
                EnquiryResponse er = new EnquiryResponse(
                    this.vcc.getCurrentUser().getUserID(),
                    reply,
                    this.currentViewerPosition,
                    UUID.randomUUID().toString(),
                    LocalDateTime.now().toString(),
                    0,
                    getEnquiryID()
                    );
                
                addReply(er);
                if(DatabaseUtils.checkIfStudentIsCampCommitteeMember(vcc.getCurrentUser().getUserID(),getCampID())){
                    CampCommiteeMember nccm = new CampCommiteeMember(this.vcc.getCurrentUser().getUserID(), this.vcc.getCurrentUser().getFaculty());
                    nccm.addPoints(1);
                }
                render();
                break;
            case 3:
                System.out.println("Enter the message number of the reply you want to upvote!");
                int msgnum = InputUtils.tryGetIntSelection(0,getEnquiryResponseList().getResponseCount());
                getEnquiryResponseList().getResponseByIndex(msgnum).upvote();
                getEnquiryResponseList().saveEnquiryResponseList();
                render();
                break;
            case 4:
                render();
                break;
            case 5:
                render();
                break;
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
         PageUtils.printTitle(getContent());
         if(getEnquiryResponseList().getResponseCount()==0){
            PageUtils.printTitle("There are currently no answers");
         }else{
            for(int y=0;y<getEnquiryResponseList().getResponseCount();y++){
                System.out.println("Message Number "+ y);
                EnquiryResponse nn = getEnquiryResponseList().getResponseByIndex(y);
                PageUtils.printResponseBox(
                    nn.getContent(),
                    DatabaseUtils.getUserByID(nn.getUserID())[1],
                    nn.getPosiiton(),
                    nn.getUpvotes(),
                    nn.getTimestamp()
                    );
            }
         }
         PageUtils.printActionBox(actions);
         int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
