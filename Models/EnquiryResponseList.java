package Models;

import java.util.ArrayList;
import java.util.Comparator;

import Utils.DatabaseUtils;


public class EnquiryResponseList {
    
    //Creating array list for enquiryResponseList
    private ArrayList<EnquiryResponse> enquiryResponseList = new ArrayList<>();
    private String enquiryID;
    public EnquiryResponseList(String eid){
        this.enquiryID = eid;
        getEnquiriesResponsesFromDatabase();
    }

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

    public void saveEnquiryResponseList(){
        DatabaseUtils.setEnquiriesRepliesByEnquiryID(this.enquiryResponseList,this.enquiryID);
    }

    public int getResponseCount(){
        return enquiryResponseList.size();
    }

    public EnquiryResponse getResponseByIndex(int idx){
        return this.enquiryResponseList.get(idx);
    }

    public boolean addEnquiryResponse(EnquiryResponse er){
        boolean result = this.enquiryResponseList.add(er);
        saveEnquiryResponseList();
        return result;
    }
}
