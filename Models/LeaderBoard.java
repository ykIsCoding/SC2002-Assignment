package Models;


import java.util.ArrayList;
import java.util.Comparator;
import Utils.PageUtils;

public class LeaderBoard extends CampCommiteeMembers{
    ArrayList<CampCommiteeMember> ldb = new ArrayList<>();
    public LeaderBoard(String campid){
        super(campid);
        this.ldb = getCampCommiteeMembers();
    }

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
