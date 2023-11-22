package Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

enum ThemeMode {DARK, LIGHT}

/**
 * the ThemeController controls the theme of the app. Sets the light and dark mode.
 */
public class ThemeController {
    ThemeMode currentTheme = ThemeMode.DARK;


    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";

    /**
     * ThemeController constructor checks the current time and decides whether to set to dark or light mode
     */
    public ThemeController(){
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Calendar cal = Calendar.getInstance();
        String curTime = dateFormat.format(cal.getTime());
        this.currentTheme=(Integer.valueOf(curTime)<18 && Integer.valueOf(curTime)>7)? ThemeMode.LIGHT:ThemeMode.DARK;
    }

    /**
     * initialise the theme controller for setting the colors
     */
    public void start(){
        if(this.currentTheme==ThemeMode.DARK){
            System.out.println(YELLOW+BLACK_BACKGROUND);
        }else{
            System.out.println(WHITE_BACKGROUND+BLUE);
        }
    }

    /**
     * reset the colors of the theme
     */
    public static void end(){
        System.out.println(COLOR_RESET);
    }

    /**
     * toggle the current theme
     */
    public void toggleCurrentTheme(){
        this.currentTheme=this.currentTheme==ThemeMode.DARK?ThemeMode.LIGHT:ThemeMode.DARK;
        start();
    }

    /**
     * check if the current mode is dark mode
     * @return true if it is dark mode, else false
     */
    public boolean isDarkMode(){
        return this.currentTheme==ThemeMode.DARK;
    }

}
