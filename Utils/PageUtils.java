package Utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Models.Action;
import Models.Suggestion;

public class PageUtils {
    static final String topLeftCorner = "╔";
    static final String topRightCorner = "╗";
    static final String bottomLeftCorner = "╚";
    static final String bottomRightCorner = "╝";
    static final String horizontalDoubleLine = "═";
    static final String verticalDoubleLine = "║";
    static final String horizontalSingleLine = "━";
    static final String TLeftLine = "╠";
    static final String TRightLine = "╣";
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


    public static void printActionBox(ArrayList<Action> actions){
        if(actions.size()==0 || actions==null) return;
        System.out.println("Enter the number corresponding to the following options:\n");
        for(int i =0;i<actions.size();i++){
            printRow(actions.get(i).getActionNo(),actions.get(i).getActionName());
        }
    }

    





    public static void printResponseBox(String content, String sender, String senderPosition, int upvotes, String ts){
        int colWidth = 108;
        String topLine = topLeftCorner+horizontalDoubleLine.repeat(colWidth)+topRightCorner+"\n";
        String infoLine = verticalDoubleLine+center(( "Upvotes: "+Integer.valueOf(upvotes)), colWidth)+verticalDoubleLine+"\n";
        String tsLine = verticalDoubleLine+center(localDateTimeStringToFullLocalDateString(ts),colWidth)+verticalDoubleLine+"\n";
        String bottomLine = bottomLeftCorner+horizontalDoubleLine.repeat(colWidth)+bottomRightCorner+"\n";
        String suggestionTitle = verticalDoubleLine+center(sender +" ("+senderPosition+")" + " says:", colWidth)+verticalDoubleLine+"\n";
        String centerLine = TLeftLine+horizontalDoubleLine.repeat(colWidth)+TRightLine+"\n";
        int multiplier = (int) Math.ceil((content.length())/colWidth)+1;
        content = padder(content,colWidth,multiplier);
        String finalString = "";
        int h = 0;
        for(int n=0;n<multiplier;n++){
            finalString+=verticalDoubleLine+space+content.substring(h,h+colWidth-2)+space+verticalDoubleLine+"\n";
            h+=colWidth;
        }

        finalString = topLine + suggestionTitle + centerLine +finalString+infoLine+tsLine+ bottomLine;
        System.out.println(finalString);
    }

    public static String localDateToString(LocalDate d){
        try{
            return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static String localDateToFullLocalDateString(LocalDate date){
        try{
            
            return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)).toString();
        }catch(Exception e){
            return "";
        }
    }

    public static String localDateTimeStringToFullLocalDateString(String date){
        try{
            
            LocalDateTime dt = LocalDateTime.parse(date);
            return dt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)).toString();
        }catch(Exception e){
            return "";
        }
    }

    public static LocalDate stringToLocalDate(String s){
        
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.ENGLISH);  
            return LocalDate.parse(s,formatter);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Date is invalid.");
            return null;
        }
    }

}


