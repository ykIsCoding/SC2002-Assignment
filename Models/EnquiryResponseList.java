package Models;

import java.util.ArrayList;


public class EnquiryResponseList {
    
    //Creating array list for enquiryResponseList
    private ArrayList<EnquiryResponse> enquiryResponseList = new ArrayList<>();
    
    //Get method for enquiryResponseList
    public ArrayList<EnquiryResponse> getEnquiryResponseList(){
        return this.enquiryResponseList;
    }

    //Editing EnquiryResponse
    public void editEnquiryResponse(String enquiryResponseID, String newContent){
        for(int b=0;b<this.enquiryResponseList.size();b++){
            if(this.enquiryResponseList.get(b).getEnquiryResponseID()==enquiryResponseID){
                enquiryResponseList.edit(this.enquiryResponseList.get(b));
            }
        }
    }

    //Get specific enquiryResponse from enquiryResponseList using enquiryResponseID
    public EnquiryResponse getEnquiryResponse(String enquiryResponseID){
        return this.enquiryResponseList.get(enquiryResponseID);
    }

    //Removing enquiryResponse from enquiryResponseList using .remove
    public void removeEnquiryResponse(String enquiryResponseID){
        for(int b=0;b<this.enquiryResponseList.size();b++){
            if(this.enquiryResponseList.get(b).getEnquiryResponseID()==enquiryResponseID){
                enquiryResponseList.remove(this.enquiryResponseList.get(b));
            }
        }
    }

    //Adding enquiryResponse into the enquiryResponseList
    public void addEnquiryResponse(EnquiryResponse er){
        this.enquiryResponseList.add(er);
    }
}
