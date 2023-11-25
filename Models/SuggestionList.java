package Models;

import Utils.DatabaseUtils;
import Utils.PageUtils;

import java.util.ArrayList;

/**
 * SuggestionList class to show the list of suggestions
 */
public class SuggestionList {
    ArrayList<Suggestion> suggestionList = new ArrayList();
    String campid;

    /**
     * constructor takes in the campid to identify the camp that has this suggestion list
     * @param campid campid of the camp
     */
    public SuggestionList(String campid){
        this.campid = campid;
        //retrieveSuggestionsFromDatabase();
    }

    /**
     * get the suggestions from the database and set suggestionList
     */
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

    /**
     * get this class's suggestion list
     * @return
     */
    public ArrayList<Suggestion> getSuggestionList(){
        return this.suggestionList;
    }

    /**
     * edit the suggestion in the suggestionlist
     * @param x suggestion to edit
     * @return true if edited, else false
     */
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

    /**
     * get the suggestion from the suggestionlist by the suggestion id
     * @param suggestionID suggestion id to get the suggestion
     * @return suggestion, null if not found
     */
    public Suggestion getSuggestionByID(String suggestionID){
        for(int b=0;b<this.suggestionList.size();b++){
            if(this.suggestionList.get(b).getSuggestionID().equals(suggestionID)){
                return this.suggestionList.get(b);
            }
        }
        return null;
    }

    /**
     * remove suggestion by suggestion id from the suggestionlist
     * @param suggestionID suggestion id of suggestion to remove
     */
    public void removeSuggestion(String suggestionID){
        for(int b=0;b<this.suggestionList.size();b++){
            if(this.suggestionList.get(b).getSuggestionID().equals(suggestionID)){
                suggestionList.remove(this.suggestionList.get(b));
                System.out.println("Suggestion Deleted!");
                DatabaseUtils.deleteSuggestionBySuggestionID(suggestionID);
            }
        }
        
    }

    /**
     * add a suggestion to the suggestion list
     * @param s suggestion to add to suggestion list
     */
    public void addSuggestion(Suggestion s){
        this.suggestionList.add(s);
        DatabaseUtils.setSuggestionsByCampID(this, campid);
    }

    /**
     * get the number of suggestions in the suggestion list
     * @return int number of suggestions in suggestionlist
     */
    public int getSuggestionCount(){
        return this.suggestionList.size();
    }

    /**
     * reject a suggestion in the suggestion list, selected by the suggestionID
     * @param suggestionID suggestionid of the suggestion to reject
     * @return true if rejected, else false.
     */
    public boolean rejectSuggestion(String suggestionID){
        Suggestion rejectedSuggestion = getSuggestionByID(suggestionID);
        rejectedSuggestion.reject();
        editSuggestion(rejectedSuggestion);
        return true;
    }

    /**
     * approve a suggestion in the suggestion list, selected by suggestionID
     * @param suggestionID suggestion id of the suggestion to approve
     * @return true if approved, else false
     */
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

    /**
     * get a suggestion by its index in suggestion list
     * @param idx of the suggestion in the suggestion list
     * @return suggestion selected by the index
     */
    public Suggestion getSuggestionByIndex(int idx){
        return this.suggestionList.get(idx);
    }

    
}
