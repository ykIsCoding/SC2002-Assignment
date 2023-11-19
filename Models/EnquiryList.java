package Models;

import java.util.ArrayList;

import Utils.DatabaseUtils;

public class EnquiryList {

    //Creating array list for enquiryList
    ArrayList<Enquiry> enquiryList = new ArrayList<>();
    ArrayList<String[]> es = new ArrayList<>();
    String campID;

    public EnquiryList(String campid){
        this.campID = campid;
        retrieveEnquiriesFromDatabase(campid);
        
    }

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

    


    //Get method for enquiryList 
    public ArrayList<Enquiry> getEnquiryList(){
        return this.enquiryList;
    }

    public boolean setEnquiryList(ArrayList<Enquiry> e){
        this.enquiryList = e;
        return true;
    }

    public String getCampID(){return this.campID;}

    //Editing enquiry from enquiryList by using enquiryID, passing in newContent and newTitle which are the edited info
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

    //Get method for specific enquiry from enquiryList using enquiryID
    public Enquiry getEnquiry(String enquiryID){
        for(int u =0; u< this.enquiryList.size();u++){
            if(this.enquiryList.get(u).getEnquiryID().equals(enquiryID)){
                return this.enquiryList.get(u);
            }
        }
        return null;
    }

    public Enquiry getEnquiryByIndex(int idx){
        return this.enquiryList.get(idx);
    }

    //Remove specifc enquiry from enquiryList using enquiryID
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

    //Adding enquiry into enquiryList
    public void addEnquiry(Enquiry e){
        this.enquiryList.add(e);
        DatabaseUtils.setEnquiriesByCampID(this);
    }
}