package Models;

import Utils.DatabaseUtils;

import java.util.ArrayList;

/**
 * This class contains the list of all the Enquiries.
 */
public class EnquiryList {

    //Creating array list for enquiryList
    ArrayList<Enquiry> enquiryList = new ArrayList<>();
    ArrayList<String[]> es = new ArrayList<>();
    String campID;

    /**
     * The constructor of the Enquiry List by retrieving it from the database.
     * @param campid is the ID of the camp that is being enquired.
     */
    public EnquiryList(String campid){
        this.campID = campid;
        retrieveEnquiriesFromDatabase(campid);
        
    }

    /**
     * This method retrieves the Enquiries from the database.
     * @param campID is the ID of the camp that is being enquired
     */
    public void retrieveEnquiriesFromDatabase(String campID){
        es = DatabaseUtils.getEnquiriesByCampID(campID);
        enquiryList = new ArrayList<>();
        for(int b=0;b<es.size();b++){
                enquiryList.add(
                new Enquiry(
                    es.get(b)[3],
                    es.get(b)[5],
                    es.get(b)[0],
                    es.get(b)[1],
                    es.get(b)[2]
                    )
                );
        }
    }


    /**
     * Getter Method for the Enquiry List
     * @return the list of all the Enquiries
     */
    public ArrayList<Enquiry> getEnquiryList(){
        return this.enquiryList;
    }

    /**
     * Setter method for the Enquiry List
     * @param e is the array list of all the Enquiries
     * @return whether the Enquiry List is successfully set
     */
    public boolean setEnquiryList(ArrayList<Enquiry> e){
        this.enquiryList = e;
        return true;
    }

    /**
     * Getter method for the ID of the camp
     * @return the ID of the camp
     */
    public String getCampID(){return this.campID;}

    /**
     * Method to edit Enquiry
     * @param newEnquiry is the enquiry that is passed to be edited
     * @return whether it has been successfully edited.
     */
    public boolean editEnquiry(Enquiry newEnquiry){
        for(int v=0;v<this.enquiryList.size();v++){
            if(this.enquiryList.get(v).getEnquiryID().equals(newEnquiry.getEnquiryID())){
                enquiryList.set(v, newEnquiry);
                DatabaseUtils.setEnquiriesByCampID(this);
                return true;
            }
        }
        return false;
    }


    /**
     * Getter method to sort the Enquiries by index
     * @param idx is the index of the Enquiries that are to be sorted
     * @return the enquiry list
     */
    public Enquiry getEnquiryByIndex(int idx){
        return this.enquiryList.get(idx);
    }

    /**
     * This method attempts to remove an Enquiry from the List
     * @param enquiryID is the ID of the Enquiry
     * @return whether it is successful at removing the enquiry.
     */
    public boolean removeEnquiry(String enquiryID){
        for(int b=0;b<this.enquiryList.size();b++){
            if(this.enquiryList.get(b).getEnquiryID().equals(enquiryID)){
                enquiryList.remove(this.enquiryList.get(b));
                DatabaseUtils.deleteEnquiryByEnquiryID(enquiryID);
                return true;
            }
        }
        return false;
    }

    /**
     * This method attempts to add an Enquiry into the List
     * @param e is the Enquiry that will be added to the List
     */
    public void addEnquiry(Enquiry e){
        this.enquiryList.add(e);
        DatabaseUtils.setEnquiriesByCampID(this);
    }
}