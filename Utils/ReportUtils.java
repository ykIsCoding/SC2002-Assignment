package Utils;

import java.io.File;


public class ReportUtils {
    public static void openFile(String fn){
        try{
         File file = new File(fn);
         
        if(file.exists()) Runtime.getRuntime().exec(new String[] { "/c",fn });
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
