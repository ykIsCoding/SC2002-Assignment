package Models;

/**
 * It is a class for the buttons.
 */
public class Action {
    String actionName;
    int actionNo;

    /**
     * Constructor for the class
     * @param an
     * @param anum
     */
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
