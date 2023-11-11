package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Enquiry {
    int createdBy;
    String status;
    String content;
    String title;
    String enquiryID;
    SuggestionList el;
    String timeStamp;
    
    public Enquiry(EnquiryList el, String content, String title){
        this.el = el;
        this.content = content;
        this.title = title;
        this.enquiryID = UUID.randomUUID().toString();
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

    public String getContent(){return this.content;}
    public String getTitle(){return this.title;}
    public String getTimestamp(){return this.timeStamp;}

    public String getEnquiryID(){
        return this.enquiryID;
    }

    public void approve(){

    }

    public void edit(String newTitle, String newContent){
        this.title = newTitle;
        this.content = newContent;
    }

    public void delete(){

    }    
}
