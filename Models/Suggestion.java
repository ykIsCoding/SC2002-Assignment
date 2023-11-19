package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Suggestion {
    Camp suggestion;
    String suggestionID;
    String userID;
    String status;

    public Suggestion(Camp x, String status,String userid,String suggestionID){
        this.suggestion = x;
        this.status = status;
        this.userID = userid;
        this.suggestionID = suggestionID;
    }

    public String getSuggestionID(){return this.suggestionID;}
    public String getStatus(){return this.status;}
    public String getUserID(){return this.userID;}
    public Camp getSuggestionCamp(){return this.suggestion;}


    public void approve(){
        this.status = "approved";

    }

    public void edit(){

    }

    public void delete(){

    }

    public boolean isApproved(){
        return this.status.equals("approved");
    }
}
