package Models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Interface.ICampInformation;
import Utils.DatabaseUtils;
import Utils.PageUtils;

/**
 *This class represents 1 single camp in our application that implements an interface with
 * the following key attributes.
 */

public class Camp implements ICampInformation{
    private final String campID;
    private final String campName;
    private final LocalDate date;
    private final LocalDate registrationClosingDate;
    private final String userGroup;
    private final int totalSlots;
    private final int campCommitteeSlots;
    private final String description;
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

    /**
     * Constructors
     * @param id is the Camp ID
     * @param campName is the name of this Camp
     * @param date is the date of this Camp.
     * @param registrationClosingDate is the deadline for the registration.
     * @param userGroup is the group of users.
     * @param location is this camp location.
     * @param totalSlots is the total slots of this camp
     * @param campCommitteeSlots is the total slots allocated for Camp Committee
     * @param description is the description of this camp
     * @param staffInChargeID is the ID of the staff that is in charge
     */
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

    /**
     *Initialises the attendees and camp committee members
     */
    public void loadAttendees(){
        this.attendees = new Attendees(campID);
        this.campCommitteeMembers = new CampCommiteeMembers(campID);
    }

    /**
     * Check in the Database whether the user is a Camp Committee Member
     * @param campID is the ID of the camp.
     * @param userID is the ID of the user
     * @return true or false whether the usr is a camp committee member
     */

    public boolean isCampCommitteeMember(String campID, String userID){
        return DatabaseUtils.checkIfStudentIsCampCommitteeMember(userID,campID);
    }

    /**
     * Getter for the Suggestion list
     * @return the suggestions
     */
    public SuggestionList getSuggestionList(){
        return this.suggestions;
    }

    /**
     * Getter for the Enquiry List
     * @return the enquiries
     */
    public EnquiryList getEnquiryList(){
        return this.enquiries;
    }

    /**
     * Print out the current slot for Total and Camp Committee.
     */
    public void printCurrentSlotsFill(){
        System.out.println("Current Total Slots: "+Integer.valueOf(this.attendees.getAttendeeCount())+"/"+Integer.valueOf(this.totalSlots+this.campCommitteeSlots));
        System.out.println("Current Camp Committee Slots: "+Integer.valueOf(this.campCommitteeMembers.getCampCommitteeCount())+"/"+this.campCommitteeSlots);
    }

    /**
     * Setter for the Suggestion List
     * @param sl is the Suggestion List
     */
    public void setSuggestionList(SuggestionList sl){
        this.suggestions=sl;
        
    }

    /**
     * Setter for the Enquiry List
     * @param el is the Enquiry List
     */
    public void setEnquiryList(EnquiryList el){
        this.enquiries = el;
        DatabaseUtils.setEnquiriesByCampID(el);
    }

    /**
     * Getter for the location of the camp
     * @return the location of the camp
     */
    public String getLocation(){
        return this.location;
    }

    /**
     * Getter method for the ID of the staff in charge
     * @return the ID of the staff in charge
     */
    public String getStaffInCharge() {
        return staffInChargeID;
    }

    /**
     * Getter method for the name of this camp
     * @return the name of the camp
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Getter method for the ID of the camp
     * @return the ID of the camp
     */
    public String getCampID(){
        return this.campID;
    }

    /**
     * Getter method for the date of the camp
     * @return the date of the camp
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter method for the deadline of the registration
     * @return the deadline of the registration
     */
    @Override
    public LocalDate getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    /**
     * Getter method for the user group
     * @return the user group
     */
    @Override
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Getter method for the total slots of the camp
     * @return the total slots in this camp
     */
    @Override
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * Getter methods for the Camp Committee slots
     * @return the total slots for Camp Committee Members
     */
    @Override
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * Getter method for the description of the camp
     * @return the description of the camp
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for the visibility of the camp, whether it is turned on or off
     * @return a boolean on the visibility of the camp
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * Setter Method for the visibility of the camp
     * @param visibility sets whether the camp can be seen or not
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * Method to register an attendee to the camp
     * @param attendee is the student that is being registered
     * @return a boolean on whether it is successful registering the attendee.
     */
    public boolean registerAttendee(Student attendee) {
        try {
           for (Camp camp : attendee.getRegisteredCamps()) {
            if (camp.getDate().equals(this.date)) {
                System.err.println("Error: Student is already registered for a camp on the same date.");
                return false;
                }
            }


            if (this.attendees.getAttendeeCount() >= this.totalSlots) {
                System.err.println("Error: Camp is already full. Cannot register attendee.");
                return false;
            }


            if (LocalDate.now().isAfter(this.registrationClosingDate)) {
                System.err.println("Error: Registration deadline has passed. Cannot register attendee.");
                return false;
}
            

            this.attendees.addAttendee(attendee);
            System.out.println("Attendee registered successfully.");
            return true;
            
        } catch (Exception e) {
            System.err.println("Error registering attendee: " + e.getMessage());
            return false;
            }
        }

    /**
     * Method to register the student as a camp committee member
     * @param committeeMember is the student that is being registered as a camp committee member
     * @return the boolean whether it is successful in registering the camp committee member
     */

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

    /**
     * Getter method for Attendees
     * @return the attendees
     */
    public Attendees getAttendees() {
        return attendees;
    }

    /**
     * Method to withdraw the attendee out of the camp
     * @param student is the student that is being withdrawn
     */
    public void withdrawAttendee(Student student) {
        if (attendees.isAttendee(student)) {
            attendees.removeAttendee(student);
            updateRemainingSlots();
            System.out.println("withdrawn successfully!");
        } else {
            System.err.println("Error: Student is not registered for this camp.");
        }
    }

    /**
     * Method to update the remaining sots left in the camp
     */
    public void updateRemainingSlots() {
        // Assuming you have a method to get the maximum capacity of the camp
        int maxCapacity = getTotalSlots() - getCampCommitteeSlots();

         // Update remaining slots based on the current number of attendees
        int remainingSlots = maxCapacity - attendees.getAttendeeCount();
    
        //System.out.println("Remaining slots: " + remainingSlots);
    }

    /**
     * Getter method to get the camp information
     * @return the camp information in the particular format
     */
    public String getCampInformation() {
        String campInfo = "Camp Name: " + campName + "\n" +
                "Date: " + PageUtils.localDateToFullLocalDateString(date) + "\n" +
                "Registration Closing Date: " + PageUtils.localDateToFullLocalDateString(registrationClosingDate) + "\n" +
                "User Group: " + userGroup + "\n" +
                "Total Slots: " + totalSlots + "\n" +
                "Camp Committee Slots: " + campCommitteeSlots + "\n" +
                "Description: " + description + "\n";

        // Add more information as needed...

        return campInfo;
    }

    /**
     * Check whether the User has enquiries
     * @param userid is the ID of the User
     * @return the boolean whether the student has enquiries or not
     */
    public boolean hasEnquiriesByUserID(String userid){
        ArrayList<String[]> x = DatabaseUtils.getEnquiriesByCampID(campID);
        for(int u=0;u<x.size();u++){
            if(x.get(u)[2].equals(userid)){
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for the camp committee members
     * @return the members that are camp committee members
     */
    public CampCommiteeMembers getCampCommitteeMembers(){
        return this.campCommitteeMembers;
    }
    
    
}
