package Views.Camps;

import Utils.PageUtils;
import Views.Interfaces.IView;

public class RegisterCampStatusView implements IView{
    
    public void handleInput(int selection) {
        // TODO Auto-generated method stub
        switch(selection){
        case 0: break;
        case 1: break;
        default:
            System.out.println("Invalid selection.");
        }
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
         PageUtils.printTitle("Camp Registration Status");
         
    }
}
