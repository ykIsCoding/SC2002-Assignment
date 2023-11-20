package Views.Camps;

import java.util.ArrayList;
import java.util.UUID;

import Controllers.CampViewController;
import Controllers.SuggestionViewController;
import Controllers.ViewControllerController;
import Models.Action;
import Models.Camp;
import Models.CampCommiteeMember;
import Models.CampList;
import Models.Staff;
import Models.Student;
import Models.Abstract.AUser;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class CampListStaffMenuView extends CampList implements IView {
    ViewControllerController vcc;


    private final ArrayList<Action> actions = new ArrayList<>();

    

    
    
    public CampListStaffMenuView(ViewControllerController vcc){
        this.vcc=vcc;
        retrieveCampsFromDB();
        
        
        if(vcc.getCurrentUser() instanceof Staff){
            this.actions.add(new Action("Back To Home", 1)); 
            this.actions.add(new Action("See Camp Information", 2));
            this.actions.add(new Action("Edit Camp", 3));
            this.actions.add(new Action("Delete Camp", 4));
            this.actions.add(new Action("Toggle Visibility",5));
            
        }else if(vcc.getCurrentUser() instanceof CampCommiteeMember){
            

        }else if(vcc.getCurrentUser() instanceof Student){

        }

	}

    public void handleInput(int selection) {
        switch(selection){
            case 1:this.vcc.navigate(3);break;
            case 2: 
                System.out.println("Enter the camp to view:");
                int choiceOfCamp = InputUtils.tryGetIntSelection(0, getCampList().size()-1);
                System.out.println(getCampByIndex(choiceOfCamp).getCampInformation());
                PageUtils.printActionBox(actions);
                int choice = InputUtils.tryGetIntSelection(1, this.actions.size());
                handleInput(choice);
                break;
            case 3:
                String campName;
                String date;
                String closingDate;
                String usergroup;
                String location;
                int slots;
                int ccslots;
                String description;
                String campID;

                System.out.println("Select the number corresponding to the camp:");
                int campsel = InputUtils.tryGetIntSelection(0,getCampListByStaff(this.vcc.getCurrentUser().getUserID()).size()-1);
                System.out.println("You are editing "+getCampListByStaff(this.vcc.getCurrentUser().getUserID()).get(campsel).getCampName());
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
                    getCampListByStaff(this.vcc.getCurrentUser().getUserID()).get(campsel).getCampID(),
                    campName,
                    PageUtils.stringToLocalDate(date),
                    PageUtils.stringToLocalDate(closingDate),
                    usergroup,
                    location,
                    slots,
                    ccslots,
                    description,
                        vcc.getCurrentUser().getUserID()
                    );
                boolean res = editCamp(tempCamp);
                if(res){
                    System.out.println("Camp Edited!");
                }else{
                System.out.println("Sorry an error occurred. Please try again.");
                }
                render();
                break;
            case 4:
                System.out.println("Select the number corresponding to the camp:");
                int campToDel = InputUtils.tryGetIntSelection(0,getCampListByStaff(this.vcc.getCurrentUser().getUserID()).size()-1);
                boolean deleteResult = removeCamp(getCampListByStaff(this.vcc.getCurrentUser().getUserID()).get(campToDel).getCampID());
                if(deleteResult){
                    System.out.println("Camp Deleted!");
                }else{
                    System.out.println("Camp not deleted, something went wrong");
                }
                render();
            case 5:
                System.out.println("Select the number corresponding to camp to toggle the visibiliy:");
                int campToSV = InputUtils.tryGetIntSelection(0,getCampListByStaff(this.vcc.getCurrentUser().getUserID()).size()-1);
                boolean cv = getCampListByStaff(this.vcc.getCurrentUser().getUserID()).get(campToSV).getVisibility();
                if(cv){
                    System.out.println("This camp is currently visible.");
                }else{
                    System.out.println("This camp is currently not visible.");
                }
                System.out.println("Press 1 to toggle the visbility. 0 to cancel process.");
                int changeVisibility = InputUtils.tryGetIntSelection(0,1);
                if(changeVisibility==1){
                    Camp campForVisibilityChange = getCampListByStaff(this.vcc.getCurrentUser().getUserID()).get(campToSV);
                    campForVisibilityChange.setVisibility(!cv);
                    boolean result = editCamp(campForVisibilityChange);
                    if(result){
                        System.out.println("Camp Visbility Changed!");
                    }else{
                        System.out.println("Something went wrong!");
                    }
                    
                }
                PageUtils.printActionBox(actions);
                int choicet = InputUtils.tryGetIntSelection(1, this.actions.size());
                handleInput(choicet);
                break;
            default:
                System.out.println("Invalid Selection");
        }
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        PageUtils.printTitle("All Camps I Created");
        if(getCampListByStaff(this.vcc.getCurrentUser().getUserID()).size()==0){
            PageUtils.printTitle("There are currently no camps");
        }else{
            for(int h=0;h<getCampListByStaff(this.vcc.getCurrentUser().getUserID()).size();h++){
                PageUtils.printRow(h,getCampList().get(h).getCampName(),getCampList().get(h).getUserGroup());
            }
        }
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(1, this.actions.size());
        handleInput(choice);
    }
}
