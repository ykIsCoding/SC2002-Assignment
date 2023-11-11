package Controllers.Interfaces;

public interface CampInformation {
    String getCampName();
    int getDate();
    int getRegistrationClosingDate();
    String getUserGroup();
    int getTotalSlots();
    int getCampCommitteeSlots();
    String getDescription();

    void editCampDetails(String campName, int date, int registrationClosingDate, String userGroup, int totalSlots, int campCommitteeSlots, String description);
}
