package Views.Suggestions;

import java.util.ArrayList;

import Controllers.SuggestionViewController;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class SuggestionListView implements IView {
    SuggestionViewController svc;

    public SuggestionListView(SuggestionViewController b){
		this.svc =b;
	}
    @Override
    public void render() {
        // TODO Auto-generated method stub
        PageUtils.printTitle("All Suggestions");

        ArrayList<String> actions = new ArrayList<String>();
        actions.add("Create");
        actions.add("Approve");
        actions.add("Create");
        actions.add("Edit");
        actions.add("Delete");

        PageUtils.printActionBox(actions);
    }
}
