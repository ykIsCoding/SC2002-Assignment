package Models;

import java.util.ArrayList;
import java.util.List;

import Controllers.Interfaces.CampInformation;

public class Camp implements CampInformation{
    private String campName;
    private int date;
    private int registrationClosingDate;
    private String userGroup;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private boolean visibility;

    //List of attendees and Camp Commitee Members
    private List<Student> attendees;
    private List<CampCommiteeMember> campCommitteeMembers;

    private Staff staffInCharge;

    public Camp(String campName, int date, int registrationClosingDate, String userGroup, int totalSlots, 
    int campCommitteeSlots, String description, Staff staffInCharge) {
        this.campName = campName;
        this.date = date;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;

        this.attendees = new ArrayList<>();
        this.campCommitteeMembers = new ArrayList<>();
        this.visibility = false; // Default visibility is false
    }
    


    //Implement CampInformation interface methods
    
    public Staff getStaffInCharge() {
        return staffInCharge;
    }
    public String getCampName() {
        return campName;
    }

    @Override
    public int getDate() {
        return date;
    }

    @Override
    public int getRegistrationClosingDate() {
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
    public void editCampDetails(String newCampName, int newDate, int newRegistrationClosingDate,
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
            if (camp.getDate() == this.date) {
                System.err.println("Error: Student is already registered for a camp on the same date.");
                return false;
                }
            }

            // Check if the camp is already full
            if (this.attendees.size() >= this.totalSlots) {
                System.err.println("Error: Camp is already full. Cannot register attendee.");
                return false;
            }

            // Check if the registration deadline has passed
            if (this.date > this.registrationClosingDate) {
                System.err.println("Error: Registration deadline has passed. Cannot register attendee.");
                return false;
}
            
            // Attempt to add the attendee to the list
            this.attendees.add(attendee);
            System.out.println("Attendee registered successfully.");
            return true;
            
        } catch (Exception e) {
            // Handle the exception (e.g., log, print an error message)
            System.err.println("Error registering attendee: " + e.getMessage());
            return false;
            }
        }

    public boolean registerCampCommitteeMember(CampCommiteeMember committeeMember) {
        try {
            // Check if the committee member is already registered for another camp on the same date
            for (Camp camp : committeeMember.getRegisteredCamps()) {
                if (camp.getDate() == this.date) {
                    System.err.println("Error: Committee member is already registered for a camp on the same date.");
                    return false;
                }
            }

            // Check if the camp committee slots are already full
            if (this.campCommitteeMembers.size() >= this.campCommitteeSlots) {
                System.err.println("Error: Camp Committee slots are already full. Cannot register committee member.");
                return false;
             }

            // Attempt to add the committee member to the list
            this.campCommitteeMembers.add(committeeMember);
            System.out.println("Committee member registered successfully.");
            return true;

        } catch (Exception e) {
            // Handle the exception (e.g., log, print an error message)
            System.err.println("Error registering committee member: " + e.getMessage());
            return false;
        }
    }


    public List<Student> getAttendees() {
        return attendees;
    }


    public void withdrawAttendee(Student student) {
        if (attendees.contains(student)) {
            attendees.remove(student);
            updateRemainingSlots();
            //System.out.println(student.getName() + " has been withdrawn from the camp: " + campName);
        } else {
            System.err.println("Error: Student is not registered for this camp.");
        }
    }

    public void updateRemainingSlots() {
        // Assuming you have a method to get the maximum capacity of the camp
        int maxCapacity = getTotalSlots() - getCampCommitteeSlots();

         // Update remaining slots based on the current number of attendees
        int remainingSlots = maxCapacity - attendees.size();
    
        //System.out.println("Remaining slots: " + remainingSlots);
    }

    public String getCampInformation() {
        StringBuilder campInfo = new StringBuilder();
        campInfo.append("Camp Name: ").append(campName).append("\n");
        campInfo.append("Date: ").append(date).append("\n");
        campInfo.append("Registration Closing Date: ").append(registrationClosingDate).append("\n");
        campInfo.append("User Group: ").append(userGroup).append("\n");
        campInfo.append("Total Slots: ").append(totalSlots).append("\n");
        campInfo.append("Camp Committee Slots: ").append(campCommitteeSlots).append("\n");
        campInfo.append("Description: ").append(description).append("\n");

        // Add more information as needed...

        return campInfo.toString();
    }

    
    
}
