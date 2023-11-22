package Models;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents the Enquiry response in the application.
 */
public class EnquiryResponse {
    private final String createdBy;
    private final String content;
    private final String position;
    private final String enquiryID;
    private final String enquiryResponseID;
    private final String timeStamp;
    private int upvote;

    /**
     * THe constructor of the EnquiryResponse Class
     * @param createdBy is the name of the User that created it
     * @param content is the response of the Enquiry
     * @param position is the position of the response
     * @param erID is the ID of the enquiry response
     * @param timestamp is the time that the response was generated
     * @param upvote is the number of upvotes the Enquiry received
     * @param enquiryID is the ID of the enquiry.
     */
    public EnquiryResponse(String createdBy, String content, String position,String erID,String timestamp, int upvote, String enquiryID){
        this.content = content;
        this.createdBy = createdBy;
        this.enquiryResponseID = erID;
        this.position = position;
        this.timeStamp = timestamp;
        this.upvote = upvote;
        this.enquiryID = enquiryID;
    
    }

    /**
     * Getter method for the enquiryResponse content
     * @return the content of the response
     */
    public String getContent(){return this.content;}

    /**
     * Getter method for the time that the response was generated.
     * @return the time of the response that was generated.
     */
    public String getTimestamp(){return this.timeStamp;}

    /**
     * Getter method for the ID of the EnquiryResponse
     * @return the ID of the EnquiryResponse
     */
    public String getEnquiryResponseID(){return this.enquiryResponseID;}

    /**
     * Getter method for the ID of the Enquiry
     * @return the ID of the Enquiry
     */
    public String getEnquiryID(){return this.enquiryID;}

    /**
     * Getter method for the position of the EnquiryResponse
     * @return the position of the EnquiryResponse
     */
    public String getPosiiton(){return this.position;}

    /**
     * Getter method for the ID of the User that created the EnquiryResponse
     * @return the ID of the User that created the EnquiryResponse
     */
    public String getUserID(){return this.createdBy;}

    /**
     * Getter method for the number of upvotes the EnquiryResponse received
     * @return the number of upvotes
     */
    public int getUpvotes(){return this.upvote;}

    /**
     * This method increases the vote of the Enquiry by 1, upvoting it once
     */
    public void upvote(){
        this.upvote++;
    }


}
