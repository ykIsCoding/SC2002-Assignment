package Views.Apps;

import java.util.ArrayList;

import Controllers.ViewControllerController;
import Models.Action;
import Models.LeaderBoard;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class LeaderBoardView extends LeaderBoard implements IView {
    ViewControllerController vcc;
    
    ArrayList<Action> actions = new ArrayList<>();

    public LeaderBoardView(ViewControllerController vcc, String campid){
        super(campid);
        this.vcc = vcc;
        actions.add(new Action("Back To Home",1));
    }

    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch (selection) {
            case 1:
                this.vcc.navigate(2);
                break;
        }
        
    }

    @Override
    public void render() {
        PageUtils.printTitle("Leaderboard");
        printLeaderboard();
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
