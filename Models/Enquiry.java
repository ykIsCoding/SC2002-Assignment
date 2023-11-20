package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Enquiry {
    private int createdBy;
    private String content;
    private final String userid;
    private final String enquiryID;
    private EnquiryList el;
    private final String timeStamp;
    private final String campID;
    private final EnquiryResponseList erl;
    
    public Enquiry(String content, String ts, String enquiryId, String campID, String uid){

        this.content = content;
        this.campID = campID;
        this.enquiryID = enquiryId;
        this.userid = uid;
        this.timeStamp = ts;
        this.erl = new EnquiryResponseList(enquiryId);

    }

    //Get method for content,title and timestamp respectively
    public String getContent(){return this.content;}
    public String getTimestamp(){return this.timeStamp;}

    //Get method for the enquiry's enquiryID
    public String getEnquiryID(){
        return this.enquiryID;
    }

    public String getUserID(){
        return this.userid;
    }

    public String getCampID(){
        return this.campID;
    }

    public EnquiryResponseList getEnquiryResponseList(){
        return this.erl;
    }

    public boolean hasReply(){
        return (this.erl.getResponseCount()>0);
    }

    public boolean addReply(EnquiryResponse er){
        return this.erl.addEnquiryResponse(er);
    }

    //Editing enquiry
    public void edit( String newContent){
        this.content = newContent;
    } 

    public boolean isEnquirer(String userID){
        return this.userid.equals(userID);
    }
}
