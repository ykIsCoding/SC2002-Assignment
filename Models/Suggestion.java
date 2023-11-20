package Models;

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

}
