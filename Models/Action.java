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

    /**
     * gets the number corresponding to the action
     * @return int corresponding to the action
     */
    public int getActionNo(){
        return this.actionNo;
    }

    /**
     * gets name of the action
     * @return action name
     */
    public String getActionName(){
        return this.actionName;
    }
}
