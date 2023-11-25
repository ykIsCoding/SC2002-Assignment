package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * This is an Enquiry class that contains all the getters and setters for it.
 */
public class Enquiry {
    private int createdBy;
    private final String content;
    private final String userid;
    private final String enquiryID;
    private EnquiryList el;
    private final String timeStamp;
    private final String campID;
    private final EnquiryResponseList erl;

    /**
     * Constructor of Enquiry Class
     * @param content
     * @param ts
     * @param enquiryId
     * @param campID
     * @param uid
     */
    public Enquiry(String content, String ts, String enquiryId, String campID, String uid){

        this.content = content;
        this.campID = campID;
        this.enquiryID = enquiryId;
        this.userid = uid;
        this.timeStamp = ts;
        this.erl = new EnquiryResponseList(enquiryId);

    }

    /**
     * Getter method for the content of the Enquiry
     * @return the content of the Enquiry
     */
    public String getContent(){return this.content;}

    /**
     * Getter method for the time stamp of the Enquiry
     * @return the time stamp of the Enquiry
     */
    public String getTimestamp(){return this.timeStamp;}

    /**
     * Getter method for ID of the enquiry
     * @return the ID of the enquiry
     */
    public String getEnquiryID(){
        return this.enquiryID;
    }

    /**
     * Getter method of the ID of the user
     * @return the ID of the user
     */
    public String getUserID(){
        return this.userid;
    }

    /**
     * Getter method for the ID of the camp
     * @return the ID of the camp
     */
    public String getCampID(){
        return this.campID;
    }

    /**
     * Getter method for the Enquiry Response List
     * @return the list of all the responses
     */
    public EnquiryResponseList getEnquiryResponseList(){
        return this.erl;
    }

    /**
     * This method checks whether the Enquiry has a response or a reply
     * @return a boolean whether it has a reply or not.
     */
    public boolean hasReply(){
        return (this.erl.getResponseCount()>0);
    }

    /**
     * This method adds the response of the enquiry.
     */
    public boolean addReply(EnquiryResponse er){
        return this.erl.addEnquiryResponse(er);
    }
}
