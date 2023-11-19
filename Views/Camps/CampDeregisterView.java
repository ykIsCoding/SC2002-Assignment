package Views.Camps;

import Controllers.CampViewController;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class CampDeregisterView implements IView{
    CampViewController cvc;
    public CampDeregisterView(CampViewController cvc){
        this.cvc =cvc;
    }
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
         PageUtils.printTitle("Unregister From Camp");
    }
}
