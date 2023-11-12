package Models.Interface;

import java.sql.Date;
import java.time.LocalDate;

public interface ICampInformation {
    String getCampName();
    LocalDate getDate();
    LocalDate getRegistrationClosingDate();
    String getUserGroup();
    int getTotalSlots();
    int getCampCommitteeSlots();
    String getDescription();
    boolean getVisibility();

    void editCampDetails(String campName, LocalDate date, LocalDate registrationClosingDate, String userGroup, int totalSlots, int campCommitteeSlots, String description);
}
