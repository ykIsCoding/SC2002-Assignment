package Utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Models.Action;

public class InputUtils {
    private static String intOnly = "\\d+";
    private static String dateOnly = "\\s*(\\d{1,2})\\s*\\/\\s*(\\d{1,2})\\s*\\/\\s*(\\d{4})";
    private static String alphanumericOnly ="^[A-Za-z0-9!\"#$%&'()*+,-./:;<=>?@[\\]^_`{}~]+$";

    public static String tryGetDate(){
        Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        Pattern pattern = Pattern.compile(dateOnly);
        try{
        while(loop){
            String g = scnr.next().trim();
            Matcher matcher = pattern.matcher(g);
            if(!matcher.find()){
                System.out.println("Please enter the date in the format DD/MM/YYYY");
                continue;
            }else{
                LocalDate today = LocalDate.now();
                String day = matcher.group(0).toString().length()==1?"0"+matcher.group(0).toString():matcher.group(0).toString();
                String month = matcher.group(1).toString().length()==1?"0"+matcher.group(1).toString():matcher.group(1).toString();
                String year = matcher.group(2).toString();
                LocalDate inputDate = LocalDate.parse( 
                (day+"/"+month+"/"+year), 
                    DateTimeFormatter.ofPattern( "dd/MM/uuuu" ) 
                    );

                if(inputDate.isBefore(today)){
                    System.out.println("You cannot set a date in the past! Please re-enter.");
                    continue;
                }else{
                    return inputDate.toString();
                }
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetDate();
    }
    scnr.close();
    return "";
    }

    public static String tryGetPassword(String oldpassword){
        Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        Pattern pattern = Pattern.compile(alphanumericOnly);
        try{
        while(loop){
            String g = scnr.next().trim();
            Matcher matcher = pattern.matcher(g);
            if(!matcher.find()){
                System.out.println("Password has to have at least 1 special character and no spaces. Please re-enter.");
                continue;
            }else if(g.length()<8){
                System.out.println("Password has to be at least 8 characters. Please re-enter.");
                continue;
            }else{
                String pw = matcher.group().toString();
                if(pw.equals(oldpassword)){
                    System.out.println("New password cannot be the same as old password. Please re-enter.");
                    continue;
                }else{
                    return pw;
                }
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetPassword(oldpassword);
    }
    scnr.close();
    return "";
    }
    
    
    public static int tryGetIntSelection(int minNo, int maxNo){
        Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        Pattern pattern = Pattern.compile(intOnly, Pattern.CASE_INSENSITIVE);
        try{
        while(loop){
            String g = scnr.next().trim();
            Matcher matcher = pattern.matcher(g);
            if(!matcher.find()|| g.length()>3){
                System.out.println("Invalid input type. Please re-enter.");
                continue;
            }else{
                int b = Integer.valueOf(matcher.group());
                if(b>maxNo || b<minNo){
                    System.out.println("The number entered does not correspond to an option.");
                }else{
                    return b;
                }
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetIntSelection(minNo, maxNo);
    }
    scnr.close();
    return -1;
}}
