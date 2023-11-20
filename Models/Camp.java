package Models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Interface.ICampInformation;
import Utils.DatabaseUtils;
import Utils.PageUtils;

public class Camp implements ICampInformation{
    private final String campID;
    private String campName;
    private LocalDate date;
    private LocalDate registrationClosingDate;
    private String userGroup;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private final String location;
    private boolean visibility;

    //List of attendees and Camp Commitee Members
    private Attendees attendees;
    private CampCommiteeMembers campCommitteeMembers;

    //list of suggestions
    private SuggestionList suggestions;
    //list of enquiries
    private EnquiryList enquiries;

    private final String staffInChargeID;

    public Camp(String id,String campName, LocalDate date, LocalDate registrationClosingDate, String userGroup,String location, int totalSlots, 
    int campCommitteeSlots, String description, String staffInChargeID) {
        this.campName = campName;
        this.campID = id;
        this.date = date;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInChargeID = staffInChargeID;
        this.location = location;
        this.attendees = new Attendees(id);
        this.campCommitteeMembers = new CampCommiteeMembers(id);
        this.visibility = false; // Default visibility is false
        ArrayList<String[]> ss = DatabaseUtils.getSuggestionsByCampID(campID);
        this.suggestions = new SuggestionList(id);
        this.enquiries = new EnquiryList(id);
    }

    public void loadAttendees(){
        this.attendees = new Attendees(campID);
        this.campCommitteeMembers = new CampCommiteeMembers(campID);
    }

    public boolean isCampCommitteeMember(String campID, String userID){
        return DatabaseUtils.checkIfStudentIsCampCommitteeMember(userID,campID);
    }
    
    public SuggestionList getSuggestionList(){
        return this.suggestions;
    }

    public EnquiryList getEnquiryList(){
        return this.enquiries;
    }

    public void printCurrentSlotsFill(){
        System.out.println("Current Total Slots: "+Integer.valueOf(this.attendees.getAttendeeCount())+"/"+Integer.valueOf(this.totalSlots+this.campCommitteeSlots));
        System.out.println("Current Camp Committee Slots: "+Integer.valueOf(this.campCommitteeMembers.getCampCommitteeCount())+"/"+this.campCommitteeSlots);
    }

    public void setSuggestionList(SuggestionList sl){
        this.suggestions=sl;
        
    }

    public void setEnquiryList(EnquiryList el){
        this.enquiries = el;
        DatabaseUtils.setEnquiriesByCampID(el);
    }

    public String getLocation(){
        return this.location;
    }


    //Implement CampInformation interface methods
    
    public String getStaffInCharge() {
        return staffInChargeID;
    }
    public String getCampName() {
        return campName;
    }

    public String getCampID(){
        return this.campID;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public LocalDate getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    @Override
    public String getUserGroup() {
        return userGroup;
    }
    @Override
    public int getTotalSlots() {
        return totalSlots;
    }

    @Override
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void editCampDetails(String newCampName, LocalDate newDate, LocalDate newRegistrationClosingDate,
    String newUserGroup, int newTotalSlots, int newCampCommitteeSlots, String newDescription) {

        this.campName = newCampName;
        this.date = newDate;
        this.registrationClosingDate = newRegistrationClosingDate;
        this.userGroup = newUserGroup;
        this.totalSlots = newTotalSlots;
        this.campCommitteeSlots = newCampCommitteeSlots;
        this.description = newDescription;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    // Add methods for managing attendees and camp committee members
    public boolean registerAttendee(Student attendee) {
        try {
            // Check if the student is already registered for another camp on the same date
           for (Camp camp : attendee.getRegisteredCamps()) {
            if (camp.getDate().equals(this.date)) {
                System.err.println("Error: Student is already registered for a camp on the same date.");
                return false;
                }
            }

            // Check if the camp is already full
            if (this.attendees.getAttendeeCount() >= this.totalSlots) {
                System.err.println("Error: Camp is already full. Cannot register attendee.");
                return false;
            }

            // Check if the registration deadline has passed
            if (LocalDate.now().isAfter(this.registrationClosingDate)) {
                System.err.println("Error: Registration deadline has passed. Cannot register attendee.");
                return false;
}
            
            // Attempt to add the attendee to the list
            this.attendees.addAttendee(attendee);
            System.out.println("Attendee registered successfully.");
            return true;
            
        } catch (Exception e) {
            // Handle the exception (e.g., log, print an error message)
            System.err.println("Error registering attendee: " + e.getMessage());
            return false;
            }
        }


    public boolean registerCampCommitteeMember(Student committeeMember) {
        try {
            // Check if the committee member is already registered for another camp on the same date
            for (Camp camp : committeeMember.getRegisteredCamps()) {
                if (camp.getDate() == this.date) {
                    System.err.println("Error: Committee member is already registered for a camp on the same date.");
                    return false;
                }
            }

            // Check if the camp committee slots are already full
            if (this.campCommitteeMembers.getCampCommitteeCount() >= this.campCommitteeSlots) {
                System.err.println("Error: Camp Committee slots are already full. Cannot register committee member.");
                return false;
             }

             if(DatabaseUtils.checkIfStudentHasCampCommittee(committeeMember.getUserID())){
                System.err.println("Error: You are already in a camp committee!");
                return false;
             }
            

            // Attempt to add the committee member to the list
            CampCommiteeMember ccm = new CampCommiteeMember(committeeMember.getUserID(), committeeMember.getFaculty());
            this.campCommitteeMembers.addCampCommitteeMember(ccm);
            System.out.println("Committee member registered successfully.");
            return true;

        } catch (Exception e) {
            // Handle the exception (e.g., log, print an error message)
            System.err.println("Error registering committee member: " + e.getMessage());
            return false;
        }
    }


    public Attendees getAttendees() {
        return attendees;
    }


    public void withdrawAttendee(Student student) {
        if (attendees.isAttendee(student)) {
            attendees.removeAttendee(student);
            updateRemainingSlots();
            System.out.println("withdrawn successfully!");
        } else {
            System.err.println("Error: Student is not registered for this camp.");
        }
    }

    public void updateRemainingSlots() {
        // Assuming you have a method to get the maximum capacity of the camp
        int maxCapacity = getTotalSlots() - getCampCommitteeSlots();

         // Update remaining slots based on the current number of attendees
        int remainingSlots = maxCapacity - attendees.getAttendeeCount();
    
        //System.out.println("Remaining slots: " + remainingSlots);
    }

    public String getCampInformation() {
        StringBuilder campInfo = new StringBuilder();
        campInfo.append("Camp Name: ").append(campName).append("\n");
        campInfo.append("Date: ").append(PageUtils.localDateToFullLocalDateString(date)).append("\n");
        campInfo.append("Registration Closing Date: ").append(PageUtils.localDateToFullLocalDateString(registrationClosingDate)).append("\n");
        campInfo.append("User Group: ").append(userGroup).append("\n");
        campInfo.append("Total Slots: ").append(totalSlots).append("\n");
        campInfo.append("Camp Committee Slots: ").append(campCommitteeSlots).append("\n");
        campInfo.append("Description: ").append(description).append("\n");

        // Add more information as needed...

        return campInfo.toString();
    }

    public boolean hasEnquiriesByUserID(String userid){
        ArrayList<String[]> x = DatabaseUtils.getEnquiriesByCampID(campID);
        for(int u=0;u<x.size();u++){
            if(x.get(u)[2].equals(userid)){
                return true;
            }
        }
        return false;
    }
    
    
}
