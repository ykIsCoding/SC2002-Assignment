package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Suggestion {
    int createdBy;
    String status;
    String content;
    String title;
    String suggestionID;
    SuggestionList sl;
    String timeStamp;

    public Suggestion(SuggestionList sl, String content, String title){
        this.sl = sl;
        this.content = content;
        this.title = title;
        this.suggestionID = UUID.randomUUID().toString();
        this.timeStamp = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(new java.util.Date());
    }

    public String getContent(){return this.content;}
    public String getTitle(){return this.title;}
    public String getTimestamp(){return this.timeStamp;}

    public String getSuggestionID(){
        return this.suggestionID;
    }

    public void approve(){

    }

    public void edit(){

    }

    public void delete(){

    }
}
