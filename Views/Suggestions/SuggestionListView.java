package Views.Suggestions;

import java.util.ArrayList;
import java.util.UUID;

import Controllers.SuggestionViewController;
import Controllers.ViewControllerController;
import Models.Action;
import Models.Camp;
import Models.CampCommiteeMember;
import Models.Staff;
import Models.Student;
import Models.Suggestion;
import Models.SuggestionList;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionListView extends SuggestionList implements IView {
    ArrayList<Action> actions = new ArrayList<>();
    
    String campid;

    

    
    ViewControllerController vcc; //= new SuggestionViewController();
    //SuggestionViewController svc;
    public SuggestionListView(String campid, ViewControllerController vcc){
        super(campid);
        this.campid = campid;
        this.vcc =vcc;
	}

    private void setup(){
        actions = new ArrayList<>();
        retrieveSuggestionsFromDatabase();
        this.actions.add(new Action("Back To Home", 1));
        if(vcc.getCurrentUser() instanceof Staff){
            if(getSuggestionList().size()>0){
                this.actions.add(new Action("Approve Suggestion", 2));
            }
        }else if(vcc.getCurrentUser() instanceof Student){
            if(DatabaseUtils.checkIfStudentIsCampCommitteeMember(vcc.getCurrentUser().getUserID(),campid)){
                ArrayList<Suggestion> unapprovedSuggestions = getSuggestionList();
                unapprovedSuggestions.removeIf((Suggestion info)->info.getStatus().equals("approved") || !info.getUserID().equals(vcc.getCurrentUser().getUserID()));
                this.actions.add(new Action("Edit Suggestion", 3));
                this.actions.add(new Action("Delete Suggestion", 4));
            }
        }
    }

    public void handleInput(int selection) {
        switch(selection){
            case 1: 
                this.vcc.navigate(3); 
                break;
            case 2: 
                System.out.println("Choose the suggestion to approve by their number:");
                int sn = InputUtils.tryGetIntSelection(0, getSuggestionCount()-1);
                approveSuggestion(getSuggestionByIndex(sn).getSuggestionID());
                render();
                break;
            case 3: 
                System.out.println("Choose the suggestion to edit by their number:");
                int se = InputUtils.tryGetIntSelection(0, getSuggestionCount()-1);
                  String campName;
                    String date;
                    String closingDate;
                    String usergroup;
                    String location;
                    int slots;
                    int ccslots;
                    String description;
                    System.out.println("You are editing a suggestion. Enter the details.");
                    System.out.println("Enter camp name:");
                    campName = InputUtils.tryGetString();
                    System.out.println("Enter camp date (DD/MM/YYYY):");
                    date = InputUtils.tryGetDate();
                    System.out.println("Enter camp registration closing date (DD/MM/YYYY):");
                    closingDate = InputUtils.tryGetDate();
                    System.out.println("Enter usergroup:");
                    usergroup = InputUtils.tryGetString().toUpperCase();
                    System.out.println("Enter location:");
                    location = InputUtils.tryGetString();
                    System.out.println("Enter Attendee slots:");
                    slots = InputUtils.tryGetIntSelection(1,10000);
                    System.out.println("Enter camp committee slots:");
                    ccslots = InputUtils.tryGetIntSelection(1,10);
                    System.out.println("Enter Description:");
                    description = InputUtils.tryGetString();

            Camp tempCamp = new Camp(
                getSuggestionByIndex(se).getSuggestionCamp().getCampID(),
                campName,
                PageUtils.stringToLocalDate(date),
                PageUtils.stringToLocalDate(closingDate),
                usergroup,
                location,
                slots,
                ccslots,
                description,
                getSuggestionByIndex(se).getSuggestionCamp().getStaffInCharge()
                );
                tempCamp.setVisibility(getSuggestionByIndex(se).getSuggestionCamp().getVisibility());
                Suggestion tempSuggestion = new Suggestion(tempCamp,"processing",this.vcc.getCurrentUser().getUserID(),getSuggestionByIndex(se).getSuggestionID());
                
                editSuggestion(tempSuggestion);
                System.out.println("Suggestion Edit Completed");
                render();
                break;
            case 4:
                System.out.println("Choose the suggestion to remove:");
                int suggestionToDelete = InputUtils.tryGetIntSelection(0, getSuggestionCount()-1);
                removeSuggestion(getSuggestionByIndex(suggestionToDelete).getSuggestionID());
                render();
                break;
                //svc = new SuggestionViewController(getSuggestion(sn));
                //this.svc.inputToWithinViewController(5);
                //this.svc.inputToWithinViewController(5, getSuggestion(sn));break;
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        setup();
        // TODO Auto-generated method stub
        PageUtils.printTitle("All Suggestions");

        ArrayList<Suggestion> allsuggestions = getSuggestionList();
        for(int n=0;n<allsuggestions.size();n++){
            System.out.println("Suggestion "+n);
            System.out.println(getSuggestionByIndex(n).getSuggestionCamp().getCampInformation());
            System.out.println("Suggestion Status: "+getSuggestionByIndex(n).getStatus());
            System.out.println("Suggested By: "+DatabaseUtils.getUserByID(getSuggestionByIndex(n).getUserID())[1]);
            System.out.println("====================================================================");
        }
        if(allsuggestions.size()==0){
            PageUtils.printTitle("There are currently no suggestions");
        }
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
