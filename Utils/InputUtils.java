package Utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Models.Action;

public class InputUtils {
    private static final String intOnly = "\\d+";
    private static final String emailOnly = "\\w+@[e.]*ntu.edu.sg";
    private static final String dateOnly = "\\s*(\\d{1,2})\\s*\\/\\s*(\\d{1,2})\\s*\\/\\s*(\\d{4})";
    private static final String alphanumericOnly ="^[A-Za-z0-9]{8,}";

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
                String day = matcher.group(1).toString().length()==1?"0"+matcher.group(1).toString():matcher.group(1).toString();
                String month = matcher.group(2).toString().length()==1?"0"+matcher.group(2).toString():matcher.group(2).toString();
                String year = matcher.group(3).toString();
               
                LocalDate inputDate = LocalDate.parse( 
                (day+"/"+month+"/"+year), 
                    DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) 
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

    public static String tryGetEmail(){
        Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        Pattern pattern = Pattern.compile(emailOnly, Pattern.CASE_INSENSITIVE);
        try{
        while(loop){
            String g = scnr.next().trim();
            Matcher matcher = pattern.matcher(g);
            if(!matcher.find()){
                System.out.println("Please enter a valid NTU email");
                continue;
            }else{
                String inputEmail = matcher.group();
                if(DatabaseUtils.getUserByEmail(inputEmail)==null){
                    System.out.println("User does not exist. Please try again.");
                }else{
                    return inputEmail.toString();
                }
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetEmail();
    }
    scnr.close();
    return null;
    }

    public static String tryGetString(){
            Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        //Pattern pattern = Pattern.compile(stringOnly);
        try{
        while(loop){
            String g = scnr.nextLine().trim();
            //Matcher matcher = pattern.matcher(g);
            return g;
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetString();
    }
    scnr.close();
    return null;
    }

    public static String tryGetPassword(){
        Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        Pattern pattern = Pattern.compile(alphanumericOnly);
        try{
        while(loop){
            String g = scnr.next().trim();
            Matcher matcher = pattern.matcher(g);
            if(!matcher.find()){
                System.out.println("Password cannot have spaces. Please re-enter.");
                continue;
            }else if(g.length()<8){
                System.out.println("Password has to be at least 8 characters. Please re-enter.");
                continue;
            }else{
                String pw = matcher.group().toString();
                return pw;
                
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetPassword();
    }
    scnr.close();
    return null;
    }
    
    public static int tryGetIntSelection(ArrayList<Action> al){
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
                ArrayList<Integer> nummers = new ArrayList<>();
                for(int p=0;p<al.size();p++){
                    nummers.add(al.get(p).getActionNo());
                }
                if(!nummers.contains(b)){
                    System.out.println("The number entered does not correspond to an option.");
                }else{
                    return b;
                }
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetIntSelection(al);
    }
    scnr.close();
    return -1;
    }
    
    public static int tryGetIntSelection(int minNo, int maxNo){
        if(maxNo<0){
            maxNo =0;
        }
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
