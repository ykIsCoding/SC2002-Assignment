package Utils;

import java.util.Scanner;

import Models.Action;

public class InputUtils {
    
    public static int tryGetIntSelection(int minNo, int maxNo){
        Scanner scnr = new Scanner(System.in);
        boolean loop = true;
        try{
        while(loop){
            
            int b = scnr.nextInt();
            if(b>maxNo || b<minNo){
                System.out.println("The number entered does not correspond to an option.");
            }else{
                return b;
            }
        }
    }catch(Exception e){
        System.out.println(e);
        return tryGetIntSelection(minNo, maxNo);
    }
    scnr.close();
    return -1;
}}
