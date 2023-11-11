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
    private List<Student> campCommitteeMembers;

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

    public void printCampInformation() {
        System.out.println("Camp Name: " + getCampName());
        System.out.println("Date: " + getDate());
        System.out.println("Registration Closing Date: " + getRegistrationClosingDate());
        System.out.println("User Group: " + getUserGroup());
        System.out.println("Total Slots: " + getTotalSlots());
        System.out.println("Camp Committee Slots: " + getCampCommitteeSlots());
        System.out.println("Description: " + getDescription());
        System.out.println("Visibility: " + getVisibility());

        // System.out.println("Staff In Charge: " + getStaffInCharge().getName());

        // Print attendees and camp committee members
        // Print attendees
        System.out.println("Attendees: ");
        for (Student attendee : attendees) {
            System.out.println(attendee.getName() + " ");
        }
        System.out.println(); // Move to the next line

        // Print camp committee members
        System.out.println("Camp Committee Members: ");
        for (Student committeeMember : campCommitteeMembers) {
            System.out.println(committeeMember.getName() + " ");
        }
        System.out.println(); // Move to the next line
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

        System.out.println("Camp details updated successfully.");
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    // Add methods for managing attendees and camp committee members
    public void registerAttendee(Student attendee) {
        this.attendees.add(attendee);
    }

    public void registerCampCommitteeMember(Student committeeMember) {
        this.campCommitteeMembers.add(committeeMember);
    }


}
