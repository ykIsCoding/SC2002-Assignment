package Models;

public class Action {
    String actionName;
    int actionNo;

    public Action(String an,int anum){
        this.actionName = an;
        this.actionNo = anum;
    }

    public int getActionNo(){
        return this.actionNo;
    }

    public String getActionName(){
        return this.actionName;
    }
}
