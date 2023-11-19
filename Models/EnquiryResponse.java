package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class EnquiryResponse {
    private String createdBy;
    private String content;
    private String position;
    private String enquiryID;
    private String enquiryResponseID;
    private String timeStamp;
    private int upvote;
    
    public EnquiryResponse(String createdBy, String content, String position,String erID,String timestamp, int upvote, String enquiryID){
        this.content = content;
        this.createdBy = createdBy;
        this.enquiryResponseID = erID;
        this.position = position;
        this.timeStamp = timestamp;
        this.upvote = upvote;
        this.enquiryID = enquiryID;
    
    }

    public String getContent(){return this.content;}
    public String getTimestamp(){return this.timeStamp;}
    public String getEnquiryResponseID(){return this.enquiryResponseID;}
    public String getEnquiryID(){return this.enquiryID;}
    public String getPosiiton(){return this.position;}
    public String getUserID(){return this.createdBy;}
    public int getUpvotes(){return this.upvote;}

    // Editing of EnquiryResponse
    public void edit(String newContent){
        this.content = newContent;
    }

    public void upvote(){
        this.upvote++;
    }
   
    
}
