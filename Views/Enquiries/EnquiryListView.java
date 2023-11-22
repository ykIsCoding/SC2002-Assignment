package Views.Enquiries;

import Controllers.ViewControllerController;
import Models.*;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the EnquiryListView to show the list of enquiries to the user
 */
public class EnquiryListView extends EnquiryList implements IView {
    String campID;
    
    ViewControllerController vcc;

    ArrayList<Action> actions = new ArrayList();

    /**
     * EnquiryListView constructor takes in the ViewControllerController and campid as its parameters.
     * This is for navigation purposes within the app. The campid is to a way to refer to the camp whose enquiries we want to show.
     * @param vcc
     * @param campid
     */
    public EnquiryListView(ViewControllerController vcc, String campid){
        super(campid);
        this.campID = campid;
        this.vcc = vcc;
       
    }

    /**
     * setup is the function to set up the class. It is used for refreshing the class.
     */
    private void setup(){
        actions = new ArrayList();
         actions.add(new Action("Back To Home", 1));

        if(vcc.getCurrentUser() instanceof Staff){
            actions.add(new Action("See Enquiry",2));
        }else if(vcc.getCurrentUser() instanceof Student){
            if(DatabaseUtils.checkIfStudentIsCampCommitteeMember(vcc.getCurrentUser().getUserID(),getCampID())){
                actions.add(new Action("See Enquiry",2));
            }else{
                ArrayList<Enquiry> temp = new ArrayList<>();
                temp = getEnquiryList();
                temp.removeIf((Enquiry info)->!info.getUserID().equals(this.vcc.getCurrentUser().getUserID()));
                setEnquiryList(temp);
                actions.add(new Action("See Enquiry",2));
                if(temp.size()>0){
                    actions.add(new Action("Edit Enquiry",3));
                    actions.add(new Action("Delete Enquiry",4));
                }
            }
        }
    }

    /**
     * The handle input function takes in an integer based on what the users enter and controls what the application does based on the choice
     * @param selection is the integer input by the user
     */
    public void handleInput(int selection) {
        switch(selection){
            case 1: this.vcc.navigate(3);break;
            case 2: 
                System.out.println("Enter the number corresponding to the enquiry:");
                int se = InputUtils.tryGetIntSelection(0,getEnquiryList().size());
                EnquiryView ev = new EnquiryView(getEnquiryList().get(se),this.vcc);
                ev.render();
                break;
            case 3:
                System.out.println("You are editing enquiry. Enter the number corresponding to the enquiry to edit:");
                int enquiryToEditIdx = InputUtils.tryGetIntSelection(0,getEnquiryList().size()-1);
                if(getEnquiryByIndex(enquiryToEditIdx).hasReply()){
                    System.out.println("Sorry you cannot edit it as it already has replies");
                    render();
                    break;
                }
                System.out.println("Enter Updated Enquiry:");
                String enquiry = InputUtils.tryGetString();
                Enquiry prevEnquiry = getEnquiryByIndex(enquiryToEditIdx);
                Enquiry ne = new Enquiry( enquiry,PageUtils.localDateToString(LocalDate.now()),prevEnquiry.getEnquiryID(),prevEnquiry.getCampID(),this.vcc.getCurrentUser().getUserID());
                editEnquiry(ne);
                System.out.println("Enquiry updated!");
                retrieveEnquiriesFromDatabase(campID);
                render();
                //this.vcc.navigate(3);
                break;
            case 4:
                System.out.println("You are deleting enquiry. Enter the number corresponding to the enquiry to delete:");
                int enquiryToDeleteIdx = InputUtils.tryGetIntSelection(0,getEnquiryList().size()-1);
                if(getEnquiryByIndex(enquiryToDeleteIdx).hasReply()){
                    System.out.println("Sorry you cannot delete it as it already has replies");
                    render();
                    break;
                }
                removeEnquiry(getEnquiryByIndex(enquiryToDeleteIdx).getEnquiryID());
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
        setup();
        // TODO Auto-generated method stub
         PageUtils.printTitle("All Enquiries");
         for(int n=0;n<getEnquiryList().size();n++){
            PageUtils.printRow(n, getEnquiryList().get(n).getContent());
         }
         if(getEnquiryList().size()==0){
            PageUtils.printTitle("there are no enquiries");
         }
         PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
