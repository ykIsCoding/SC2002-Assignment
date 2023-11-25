package Views.Apps;

import java.util.ArrayList;

import Controllers.ViewControllerController;
import Models.Action;
import Models.LeaderBoard;
import Utils.InputUtils;
import Utils.PageUtils;
import Views.Interfaces.IView;

/**
 * This is the LeaderBoardView to show the leaderboards of the camp committee members for each camp to the camp committee member.
 */
public class LeaderBoardView extends LeaderBoard implements IView {
    ViewControllerController vcc;
    
    ArrayList<Action> actions = new ArrayList<>();

    /**
     * The constructor that will take in the ViewControllerController and the campid to set up the class.
     * @param vcc is the ViewControllerController
     * @param campid is the campID for the camp whose camp committee members are in the leaderboard
     */
    public LeaderBoardView(ViewControllerController vcc, String campid){
        super(campid);
        this.vcc = vcc;
        actions.add(new Action("Back To Home",1));
    }

    /**
     *The handle input function takes in an integer based on what the users enter and controls what the application does based on the choice
     * @param selection is the integer that the user inputs
     */
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch (selection) {
            case 1:
                this.vcc.navigate(2);
                break;
        }
        
    }

    /**
     * The render function outputs what is shown to the user and also sets up the business logic of getting an input from the user.
     */
    @Override
    public void render() {
        PageUtils.printTitle("Leaderboard");
        printLeaderboard();
        PageUtils.printActionBox(actions);
        int choice = InputUtils.tryGetIntSelection(actions);
        handleInput(choice);
    }
}
