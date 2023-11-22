package Models;

import Utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class contains the list of Enquiry Responses.
 */

public class EnquiryResponseList {
    
    //Creating array list for enquiryResponseList
    private ArrayList<EnquiryResponse> enquiryResponseList = new ArrayList<>();
    private final String enquiryID;

    /**
     * The constructor for the Enquiry Response List
     * @param eid is the unique identifier of the list which stores the responses
     */
    public EnquiryResponseList(String eid){
        this.enquiryID = eid;
        getEnquiriesResponsesFromDatabase();
    }

    /**
     * This method is accessing the EnquiriesResponse from the database.
     */
    private void getEnquiriesResponsesFromDatabase(){
        enquiryResponseList = new ArrayList<>();
        ArrayList<String[]> ers = DatabaseUtils.getEnquiriesRepliesByEnquiryID(this.enquiryID);
        for(int k=0;k<ers.size();k++){
            enquiryResponseList.add(
                new EnquiryResponse(
                    ers.get(k)[1],
                    ers.get(k)[3],
                    ers.get(k)[2],
                    ers.get(k)[6],
                    ers.get(k)[5],
                    Integer.valueOf(ers.get(k)[4]),
                    ers.get(k)[0]
                    )
            );
        }
        enquiryResponseList.sort(Comparator.comparing((EnquiryResponse er)->er.getUpvotes()).reversed());
    }

    /**
     * This method is saving the most updated list of the Enquiry Response
     */
    public void saveEnquiryResponseList(){
        DatabaseUtils.setEnquiriesRepliesByEnquiryID(this.enquiryResponseList,this.enquiryID);
    }

    /**
     * Getter method for the number of Enquiry Responses
     * @return the number or size of the Enquiry Responses
     */
    public int getResponseCount(){
        return enquiryResponseList.size();
    }

    /**
     * Getter to sort the Enquiry Response by its index
     * @param idx is the index of the Enquiry Response
     * @return the updated list of the Enquiry Response
     */
    public EnquiryResponse getResponseByIndex(int idx){
        return this.enquiryResponseList.get(idx);
    }

    /**
     * This method attempts to add the Enquiry Response to the list
     * @param er is the Enquiry Response that is going to be added
     * @return whether it has been successfully added or not.
     */
    public boolean addEnquiryResponse(EnquiryResponse er){
        boolean result = this.enquiryResponseList.add(er);
        saveEnquiryResponseList();
        return result;
    }
}
