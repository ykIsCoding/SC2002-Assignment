package Utils;
import java.util.ArrayList;

import Models.Action;

public class PageUtils {
    static final String topLeftCorner = "╔";
    static final String topRightCorner = "╗";
    static final String bottomLeftCorner = "╚";
    static final String bottomRightCorner = "╝";
    static final String horizontalDoubleLine = "═";
    static final String verticalDoubleLine = "║";
    static final String horizontalSingleLine = "━";
    static final String TTopLine = "╦";
    static final String TBottomLine = "╩";
    static final String space =" ";

    
    public static void printRow(int rowNum, String content){
        int col1Width = 6;
        int col2Width = 100;
        int multiplier = (int) Math.ceil((content.length()/col2Width))+1;
        String col1Content = padder(Integer.toString(rowNum),col1Width,multiplier);
        String col2Content = padder(content, col2Width,multiplier);
        String topLine = topLeftCorner+horizontalDoubleLine.repeat(col1Width)+TTopLine+horizontalDoubleLine.repeat(col2Width)+topRightCorner+"\n";
        String bottomLine = bottomLeftCorner+horizontalDoubleLine.repeat(col1Width)+TBottomLine+horizontalDoubleLine.repeat(col2Width)+bottomRightCorner+"\n";
        String finalString = "";
        int h = 0;
        int k = 0;
        for(int n=0;n<multiplier;n++){
            finalString+=verticalDoubleLine+space+col1Content.substring(h,h+col1Width-2)+space+verticalDoubleLine+space+col2Content.substring(k,k+col2Width-2)+space+verticalDoubleLine+"\n";
            h+=col1Width;
            k+=col2Width;
        }
        finalString = topLine + finalString + bottomLine;
        System.out.print(finalString);
    }


    public static void printRow(int rowNum, String content, String content2){
        int col1 = 6;
        int col2 = 80;
        int col3 = 20;
        
        int largestContentInput = Math.max(content.length(),content2.length());
        int multiplier = (int) Math.ceil((largestContentInput/col2))+1;

        String col1Content = padder(Integer.toString(rowNum),col2,multiplier);
        String col2Content = padder(content, col2,multiplier);
        String col3Content = padder(content2,col3,multiplier);

        String topLine = topLeftCorner+horizontalDoubleLine.repeat(col1)+TTopLine+horizontalDoubleLine.repeat(col2)+TTopLine+horizontalDoubleLine.repeat(col3)+topRightCorner+"\n";
        String bottomLine = bottomLeftCorner+horizontalDoubleLine.repeat(col1)+TBottomLine+horizontalDoubleLine.repeat(col2)+TBottomLine+horizontalDoubleLine.repeat(col3)+bottomRightCorner+"\n";
        String finalString = "";

        int h = 0;
        int k = 0;
        int i = 0;
        for(int n=0;n<multiplier;n++){
            finalString+=verticalDoubleLine+space+col1Content.substring(h,h+col1-2)+space+verticalDoubleLine+space+col2Content.substring(k,k+col2-2)+space+verticalDoubleLine+space+col3Content.substring(i,i+col3-2)+space+verticalDoubleLine+"\n";
            h+=col1;
            k+=col2;
            i+=col3;
        }
        finalString = topLine + finalString + bottomLine;
        System.out.print(finalString);
    }

    public static void printRowWithAdditionalInstructions(int rowNum, String content,String additionalInstructions){
        System.out.println(additionalInstructions);
        printRow(rowNum,content);
    }

    public static void printRowWithAdditionalInstructions(int rowNum, String content, String content2,String additionalInstructions){
        System.out.println(additionalInstructions);
        printRow(rowNum, content,content2);
    }

    private static String padder(String content, int maxColWidth, int multiplier){
        String space = " ";
        int fullLength = multiplier * maxColWidth;
        content = content.concat(space.repeat(fullLength-content.length()));
        return content;
    }

    private static String center(String content, int maxColWidth){
        if(maxColWidth-content.length()<=0) return content;
        int leftCount = (int)Math.floor((maxColWidth-content.length())/2);
        
        int rightCount = maxColWidth-content.length()-(leftCount);
        return space.repeat(leftCount)+content+space.repeat(rightCount);
    }

    

    public static void printTitle(String title){
        int col1 = 108;

        int multiplier = (int) Math.ceil((title.length()/col1))+1;
        String topLine = topLeftCorner+horizontalDoubleLine.repeat(col1)+topRightCorner+"\n"+verticalDoubleLine+space.repeat(col1)+verticalDoubleLine+"\n";
        String bottomLine = verticalDoubleLine+space.repeat(col1)+verticalDoubleLine+"\n"+bottomLeftCorner+horizontalDoubleLine.repeat(col1)+bottomRightCorner+"\n";
        String titleContent = title.length()>=col1-2? padder(title,col1,multiplier):center(title,col1);
        String finalString = "";

        int h = 0;
        for(int n=0;n<multiplier;n++){
            finalString+=verticalDoubleLine+titleContent.substring(h,h+col1)+verticalDoubleLine+"\n";
            h+=col1;
        }

        System.out.print(topLine+finalString+bottomLine);
    }

    public static void printHeader(){
        
    }

    public static void printActionBox(Action[] actions){
        if(actions.length==0 || actions==null) return;
        System.out.println("Enter the number corresponding to the following options:\n");
        for(int i =0;i<actions.length;i++){
            printRow(actions[i].getActionNo(),actions[i].getActionName());
        }
    }

}
