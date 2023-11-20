package Views.Camps;

import Controllers.ViewControllerController;
import Models.*;
import Utils.DatabaseUtils;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Enquiries.EnquiryListView;
import Views.Interfaces.IView;
import Views.Suggestions.SuggestionListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class CampView implements IView{

    ViewControllerController vcc;
    Camp c;
    
    ArrayList<Action> actions = new ArrayList();
    public CampView(Camp c, ViewControllerController vcc){
        this.vcc = vcc;
        this.c = c;
        setup();
    }
    
    private void setup(){
        actions = new ArrayList<>();
        c.loadAttendees();
        this.actions.add(new Action("Back", 1)); 
        
        if(vcc.getCurrentUser() instanceof Staff){
            if(this.c.getStaffInCharge().equals(this.vcc.getCurrentUser().getUserID())){
                actions.add(new Action("View Enquiries",4));
                actions.add(new Action("View Suggestions",5));
                this.actions.add(new Action("Get Attendence Report",8));
            this.actions.add(new Action("Get Camp Committee Performance Report",9));
            this.actions.add(new Action("Get Enquiry Report",13));
                
            }
            
            
            
        }else if(vcc.getCurrentUser() instanceof Student){
            
            if(this.c.isCampCommitteeMember(this.c.getCampID(), this.vcc.getCurrentUser().getUserID())){
                this.actions.add(new Action("View Enquiries",4));
                this.actions.add(new Action("View Suggestions",5));
                this.actions.add(new Action("Make Suggestion",7));
                this.actions.add(new Action("Get Attendence Report",8));
            }else{

                if(this.c.getAttendees().isAttendee((Student) vcc.getCurrentUser())){
                    this.actions.add(new Action("Quit Camp", 11));
                }else{
                    this.actions.add(new Action("Register as Camp Committee", 12));
                    this.actions.add(new Action("Register For Camp", 2));
                }

            }
            if(this.c.hasEnquiriesByUserID(this.vcc.getCurrentUser().getUserID())){
                this.actions.add(new Action("View My Enquiries",4));
            }
            this.actions.add(new Action("Make Enquiry",6));
        }
        
    }
    
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
            case 1:
                this.vcc.navigate(2);
                break;
            case 2:
                boolean registerResult = this.c.registerAttendee((Student)this.vcc.getCurrentUser());
                render();
                break;
            case 3: break;
            case 4: 
                EnquiryListView elv= new EnquiryListView( this.vcc,this.c.getCampID());
                elv.render();
                break;
            case 5:
                SuggestionListView svl = new SuggestionListView(this.c.getCampID(), this.vcc);
                svl.render();
                break;
            case 6:
                System.out.println("Enter Enquiry:");
                String enquiry = InputUtils.tryGetString();
                EnquiryList cel = c.getEnquiryList();
                Enquiry ne = new Enquiry( enquiry,PageUtils.localDateToString(LocalDate.now()),UUID.randomUUID().toString(),c.getCampID(),this.vcc.getCurrentUser().getUserID());
                cel.addEnquiry(ne);
                c.setEnquiryList(cel);
                System.out.println("Enquiry added!");
                render();
                break;
            case 7:
            String campName;
            String date;
            String closingDate;
            String usergroup;
            String location;
            int slots;
            int ccslots;
            String description;
            System.out.println("You are making a suggestion. Enter the details.");
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
                this.c.getCampID(),
                campName,
                PageUtils.stringToLocalDate(date),
                PageUtils.stringToLocalDate(closingDate),
                usergroup,
                location,
                slots,
                ccslots,
                description,
                this.c.getStaffInCharge()
                );
                tempCamp.setVisibility(this.c.getVisibility());
                Suggestion tempSuggestion = new Suggestion(tempCamp,"processing",this.vcc.getCurrentUser().getUserID(), UUID.randomUUID().toString());
                this.c.getSuggestionList().addSuggestion(tempSuggestion);
                if(DatabaseUtils.checkIfStudentIsCampCommitteeMember(vcc.getCurrentUser().getUserID(),this.c.getCampID())){
                    CampCommiteeMember nccm = new CampCommiteeMember(this.vcc.getCurrentUser().getUserID(), this.vcc.getCurrentUser().getFaculty());
                    nccm.addPoints(1);
                }
                System.out.println("Suggestion added!");
                render();
                break;
            case 8:
                AttendanceReport ar = new AttendanceReport(this.c.getCampID());
                System.out.println("Press 1 for txt, 2 for csv. -1 to cancel");
                int ft2 = InputUtils.tryGetIntSelection(-1,2);
                if(ft2==1||ft2==2){
                    ar.generateReport(ft2);
                    System.out.println("Attendance Report generated in Exports folder");
                }
                render();
                break;
            case 9:
                PerformanceReport pr = new PerformanceReport(this.c.getCampID());
                System.out.println("Press 1 for txt, 2 for csv. -1 to cancel");
                int ft = InputUtils.tryGetIntSelection(-1,2);
                if(ft==1||ft==2){
                    pr.generateReport(ft);
                    System.out.println("Performance Report generated in Exports folder");
                }
                render();
                break;
            case 11:
                this.c.withdrawAttendee((Student)this.vcc.getCurrentUser());
                render();
                break;
            case 12:
                this.c.registerCampCommitteeMember((Student) this.vcc.getCurrentUser());
                render();
                break;
            case 13:
                EnquiryReport er = new EnquiryReport(this.c.getCampID());
                System.out.println("Press 1 for txt, 2 for csv. -1 to cancel");
                int ft3 = InputUtils.tryGetIntSelection(-1,2);
                if(ft3==1||ft3==2){
                    er.generateReport(ft3);
                    System.out.println("Enquiry Report generated in Exports folder");
                }
                render();
                break;
            
                //this.vcc.navigate(2);
                
        }
        
    }
    @Override
    public void render() {
        setup();
        // TODO Auto-generated method stub
         PageUtils.printTitle(c.getCampName());
         System.out.print(c.getCampInformation());
         if(vcc.getCurrentUser() instanceof Staff){
            if(this.c.getVisibility()){
                System.out.println("Visibility: Camp is currently visible");
            }else{
                System.out.println("Visbility: Camp is currently not visible");
            }
         }
         c.loadAttendees();
         this.c.printCurrentSlotsFill();
         PageUtils.printActionBox(actions);
         int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
