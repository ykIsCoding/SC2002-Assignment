package Models;


import java.util.ArrayList;
import java.util.Comparator;
import Utils.PageUtils;

/**
 * This is the leaderboard for each Camp Committee Member points within each camp.
 * It inherits from the Camp Committee Members class
 */

public class LeaderBoard extends CampCommiteeMembers{
    ArrayList<CampCommiteeMember> ldb = new ArrayList<>();

    /**
     * The constructor of this Leaderboard Class
     * @param campid is the ID of the camp
     */
    public LeaderBoard(String campid){
        super(campid);
        this.ldb = getCampCommiteeMembers();
    }


    /**
     * This method prints out all the leaderboard points for the Camp Committee Members.
     */
    public void printLeaderboard(){
        this.ldb.sort(Comparator.comparing((CampCommiteeMember ccm)->ccm.getAccumulatedPoints()).reversed());
        System.out.println("ROW NUMBER  NAME   POINTS");
        int pos =1;
        for(CampCommiteeMember ccm:this.ldb){
            //System.out.println(this.ldb.get(v).getName());
            PageUtils.printRow(pos,ccm.getName(),String.valueOf(ccm.getAccumulatedPoints()));
            pos++;
        }
    }
}
