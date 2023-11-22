package Models;

/**
 * Suggestion class for the suggestion
 */
public class Suggestion {
    Camp suggestion;
    String suggestionID;
    String userID;
    String status;

    /**
     * constructor to create the suggestion
     * @param x camp that has the suggestion
     * @param status status of the suggestion - approved, rejected or processing
     * @param userid user id of the user that made the suggestion
     * @param suggestionID suggestion id of the suggestion
     */
    public Suggestion(Camp x, String status,String userid,String suggestionID){
        this.suggestion = x;
        this.status = status;
        this.userID = userid;
        this.suggestionID = suggestionID;
    }

    /**
     * getter for the suggestion ID
     * @return the suggestionid
     */
    public String getSuggestionID(){return this.suggestionID;}

    /**
     * getter for status of suggestion
     * @return status
     */
    public String getStatus(){return this.status;}

    /**
     * getter for userid of suggestion
     * @return userid of suggestion
     */
    public String getUserID(){return this.userID;}

    /**
     * getter for camp of the suggestion
     * @return camp
     */
    public Camp getSuggestionCamp(){return this.suggestion;}

    /**
     * approve the suggestion
     */
    public void approve(){
        this.status = "approved";

    }

    /**
     * reject the suggestion
     */
    public void reject(){
        this.status = "rejected";
    }

}
