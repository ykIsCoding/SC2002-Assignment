package Models;

import java.util.ArrayList;

public class SuggestionList {
    ArrayList<Suggestion> suggestionList = new ArrayList();

    public SuggestionList(){
        this.suggestionList.add(new Suggestion(this,"testing","suggestion 1"));
    }

    public ArrayList<Suggestion> getSuggestionList(){
        return this.suggestionList;
    }

    public void editSuggestion(){

    }

    public Suggestion getSuggestion(int suggestionNo){
        return this.suggestionList.get(suggestionNo);
    }

    public void removeSuggestion(String suggestionID){
        for(int b=0;b<this.suggestionList.size();b++){
            if(this.suggestionList.get(b).getSuggestionID()==suggestionID){
                suggestionList.remove(this.suggestionList.get(b));
            }
        }
    }

    public void addSuggestion(Suggestion s){
        this.suggestionList.add(s);
    }
}
