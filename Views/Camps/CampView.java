package Views.Camps;

import Controllers.CampViewController;
import Utils.PageUtils;
import Views.Interfaces.IView;

public class CampView implements IView{

    CampViewController cvc;
    public CampView(CampViewController cvc){
        this.cvc =cvc;
    }
    
    @Override
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
         PageUtils.printTitle("Camp");
    }
}
