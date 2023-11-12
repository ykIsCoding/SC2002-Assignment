package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class EnquiryResponse {
    private int createdBy;
    private String content;
    private String title;
    private String enquiryResponseID;
    private String timeStamp;
    private int upvote;
    
    public EnquiryResponse(EnquiryResponseList erl, String title, String content, int upvote){
        this.erl = erl;
        this.content = content;
        this.title = title;
        this.enquiryResponseID = UUID.randomUUID().toString();
        this.upvote = upvote;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

    public String getContent(){return this.content;}
    
    public String getTitle(){return this.title;}
    
    public String getTimestamp(){return this.timeStamp;}

    public String getEnquiryResponseID(){
        return this.enquiryResponseID;
    }

    // Editing of EnquiryResponse
    public void edit(String newContent){
        this.content = newContent;
    }
   
    // Get Upvote Method
    public int getUpVote(){
        this.upvote = upvote;
    }
}
