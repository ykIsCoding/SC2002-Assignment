package Models;

import java.util.ArrayList;

public class EnquiryList {

    //Creating array list for enquiryList
    ArrayList<Enquiry> enquiryList = new ArrayList<>();

    //Get method for enquiryList 
    public ArrayList<Enquiry> getEnquiryList(){
        return this.enquiryList;
    }

    //Editing enquiry from enquiryList by using enquiryID, passing in newContent and newTitle which are the edited info
    public void editEnquiry(String enquiryID, String newContent, String newTitle){
        for(int b=0;b<this.enquiryList.size();b++){
            if(this.enquiryList.get(b).getEnquiryID()==enquiryID){
                enquiryList.edit(this.enquiryList.get(b));
            }
        }
    }

    //Get method for specific enquiry from enquiryList using enquiryID
    public Enquiry getEnquiry(String enquiryID){
        return this.enquiryList.get(enquiryID);
    }

    //Remove specifc enquiry from enquiryList using enquiryID
    public void removeEnquiry(String enquiryID){
        for(int b=0;b<this.enquiryList.size();b++){
            if(this.enquiryList.get(b).getEnquiryID()==enquiryID){
                enquiryList.remove(this.enquiryList.get(b));
            }
        }
    }

    //Adding enquiry into enquiryList
    public void addEnquiry(Enquiry e){
        this.enquiryList.add(e);
    }
}