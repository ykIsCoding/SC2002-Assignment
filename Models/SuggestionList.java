package Models;

import Utils.DatabaseUtils;
import Utils.PageUtils;

import java.util.ArrayList;

public class SuggestionList {
    ArrayList<Suggestion> suggestionList = new ArrayList();
    String campid;

    public SuggestionList(String campid){
        this.campid = campid;
        //retrieveSuggestionsFromDatabase();
    }

    public void retrieveSuggestionsFromDatabase(){
        ArrayList<String[]> rawSuggestions = DatabaseUtils.getSuggestionsByCampID(campid);
        for(String[] x: rawSuggestions){
            Camp tmpCmp = new Camp(
                x[0],
                x[4],
                PageUtils.stringToLocalDate(x[5]),
                PageUtils.stringToLocalDate(x[6]),
                x[7],
                x[8],
                Integer.valueOf(x[9]),
                Integer.valueOf(x[10]),
                x[11],
                x[12]
                );
            Suggestion tmp = new Suggestion(tmpCmp,x[2],x[1],x[3]);
            suggestionList.add(tmp);
        }
    }

    public ArrayList<Suggestion> getSuggestionList(){
        return this.suggestionList;
    }

    public boolean editSuggestion(Suggestion x){
        for(int b=0;b<this.suggestionList.size();b++){
            if(this.suggestionList.get(b).getSuggestionID().equals(x.getSuggestionID())){
                this.suggestionList.set(b, x);
                DatabaseUtils.setSuggestionsByCampID(this, campid);
                return true;
            }
        }
        return false;
    }

    public Suggestion getSuggestionByID(String suggestionID){
        for(int b=0;b<this.suggestionList.size();b++){
            if(this.suggestionList.get(b).getSuggestionID().equals(suggestionID)){
                return this.suggestionList.get(b);
            }
        }
        return null;
    }

    public void removeSuggestion(String suggestionID){
        for(int b=0;b<this.suggestionList.size();b++){
            if(this.suggestionList.get(b).getSuggestionID().equals(suggestionID)){
                suggestionList.remove(this.suggestionList.get(b));
                System.out.println("Suggestion Deleted!");
                DatabaseUtils.deleteSuggestionBySuggestionID(suggestionID);
            }
        }
        
    }

    public void addSuggestion(Suggestion s){
        this.suggestionList.add(s);
        DatabaseUtils.setSuggestionsByCampID(this, campid);
    }

    public int getSuggestionCount(){
        return this.suggestionList.size();
    }

    public boolean approveSuggestion(String suggestionID){
        Suggestion approvedSuggestion = getSuggestionByID(suggestionID);
        approvedSuggestion.approve();
        editSuggestion(approvedSuggestion);
        ArrayList<String[]> newCampData = new ArrayList<>();
        Camp sc = approvedSuggestion.getSuggestionCamp();
        String visibility = "0";
        if(sc.getVisibility()){
            visibility ="1";
        }
        String[] ncd = {
//to add
            sc.getCampID(),
            sc.getCampName(),
            PageUtils.localDateToString(sc.getDate()), 
            PageUtils.localDateToString(sc.getRegistrationClosingDate()), 
            sc.getUserGroup(), 
            sc.getLocation(), 
            String.valueOf(sc.getTotalSlots()), 
            String.valueOf(sc.getCampCommitteeSlots()),
            sc.getDescription(),  
            sc.getStaffInCharge(),
            visibility  
        };
        newCampData.add(ncd);
        DatabaseUtils.updateCamps(newCampData);
        return true;

    }

    public Suggestion getSuggestionByIndex(int idx){
        return this.suggestionList.get(idx);
    }

    
}
