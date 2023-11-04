package Views.Apps;
import Utils.PageUtils;
import Views.Interfaces.IView;

import java.util.ArrayList;

public class HomeView implements IView {
    
    public void render(){
        PageUtils.printTitle("Welcome");
    }
}
