package Views.Enquiries;

import Controllers.ViewControllerController;
import Models.*;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This is the EnquiryView to show 1 enquiry's information to the user.
 */
public class EnquiryView extends Enquiry implements IView{
    ViewControllerController vcc;
    String currentViewerPosition;
    
    private final ArrayList<Action> actions = new ArrayList<>();

    /**
     * Enquiry constructor takes in the ViewControllerController as its parameter for navigation purposes within the app.
     * The constructor also takes in an Enquiry class to retrieve the data of the enquiry
     * @param e
     * @param vcc
     */
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

    /**
     * The handle input function takes in an integer based on what the users enter and controls what the application does based on the choice
     * @param selection is the integer input by the user
     */
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

    /**
     * The render function outputs what is shown to the user and also sets up the business logic of getting an input from the user.
     */
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
