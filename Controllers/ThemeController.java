package Controllers;

import Utils.ThemeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

enum ThemeMode {DARK, LIGHT}

public class ThemeController {
    ThemeMode currentTheme = ThemeMode.DARK;


    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";

    public ThemeController(){
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Calendar cal = Calendar.getInstance();
        String curTime = dateFormat.format(cal.getTime());
        this.currentTheme=(Integer.valueOf(curTime)<18 && Integer.valueOf(curTime)>7)? ThemeMode.LIGHT:ThemeMode.DARK;
    }

    public void start(){
        if(this.currentTheme==ThemeMode.DARK){
            System.out.println(YELLOW+BLACK_BACKGROUND);
        }else{
            System.out.println(WHITE_BACKGROUND+BLUE);
        }
    }
    public static void end(){
        System.out.println(COLOR_RESET);
    }

    public void toggleCurrentTheme(){
        this.currentTheme=this.currentTheme==ThemeMode.DARK?ThemeMode.LIGHT:ThemeMode.DARK;
        start();
    }

    public void printSwitch(){
        if(this.currentTheme==ThemeMode.DARK){
            ThemeUtils.darkSwitch();
        }else{
            ThemeUtils.lightSwitch();
        }
    }

    public boolean isDarkMode(){
        return this.currentTheme==ThemeMode.DARK;
    }

}
