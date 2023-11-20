package Models;

import java.time.LocalDate;
import java.util.ArrayList;

import Utils.DatabaseUtils;
import Utils.PageUtils;

public class CampList {
    ArrayList<Camp> campList = new ArrayList<>();

    public CampList(){
        
    } 

    public ArrayList<Camp> retrieveCampsFromDB(){
        ArrayList<String[]> allCamps = new ArrayList<>();
        allCamps = DatabaseUtils.readCamps();
        campList = new ArrayList<>();
        
        for(int v=0;v<allCamps.size();v++){
            
            Camp nn = new Camp(
                    allCamps.get(v)[0],
                    allCamps.get(v)[1],
                    PageUtils.stringToLocalDate(allCamps.get(v)[2]),
                    PageUtils.stringToLocalDate(allCamps.get(v)[3]),
                    allCamps.get(v)[4],
                    allCamps.get(v)[5],
                    Integer.valueOf(allCamps.get(v)[6]),
                    Integer.valueOf(allCamps.get(v)[7]),
                    allCamps.get(v)[8],
                    allCamps.get(v)[9]
                    );
            nn.setVisibility(Integer.valueOf(allCamps.get(v)[10]) == 1);
            this.campList.add(nn);
        }
        return this.campList;
    }
    

    public ArrayList<Camp> getCampList(){
        return this.campList;
    }

    public ArrayList<Camp> getCampListByStaff(String StaffID){
        ArrayList<Camp> newList = new ArrayList<>();
        for(int x=0;x<this.campList.size();x++){
            if(this.campList.get(x).getStaffInCharge().equals(StaffID)){
                newList.add(this.campList.get(x));
            }
        }
        this.campList= newList;
        return newList;
    }

    public ArrayList<Camp> getCampListByFacultyOrAll(String userGroup){
        ArrayList<Camp> newList = new ArrayList<>();
        for(int x=0;x<this.campList.size();x++){
            if(this.campList.get(x).getUserGroup().toLowerCase().equals(userGroup.toLowerCase()) || this.campList.get(x).getUserGroup().toLowerCase().equals("all")){
                newList.add(this.campList.get(x));
            }
        }
        this.campList= newList;
        return newList;
    }

    

   

    public boolean editCamp(Camp nc){
        
        for(int m =0;m<this.campList.size();m++){
            
            if(this.campList.get(m).getCampID().equals(nc.getCampID())){
                this.campList.set(m,nc);
                DatabaseUtils.updateCamps(convertCampToStringArr(this.campList));
                
                return true;
            }
        }
        
        return false;
    }

    public Camp getCamp(int campID){
        for(int x = 0 ; x<this.campList.size();x++){
            if(this.campList.get(x).getCampID().equals(campID)){
                return this.campList.get(x);
            }
        }
        return null;
    }

    public Camp getCampByIndex(int idx){
        return this.campList.get(idx);
    }

    public boolean removeCamp(String campID){
        ArrayList<Camp> campsToRemove = new ArrayList<>();
        for(int b=0;b<this.campList.size();b++){
            if(this.campList.get(b).getCampID()==campID){
                campsToRemove.add(this.campList.get(b));
                this.campList.remove(this.campList.get(b));
            }
        }
        return DatabaseUtils.deleteCamp(convertCampToStringArr(campsToRemove));
    }

    private ArrayList<String[]> convertCampToStringArr(ArrayList<Camp> cl){
        ArrayList<String[]>temp = new ArrayList();
        for(int p=0;p<cl.size();p++){
            String[] tarr = {
                cl.get(p).getCampID(),
                cl.get(p).getCampName(),
                cl.get(p).getDate().toString(),
                cl.get(p).getRegistrationClosingDate().toString(),
                cl.get(p).getUserGroup().toUpperCase(),
                cl.get(p).getLocation(),
                Integer.toString(this.campList.get(p).getTotalSlots()),
                Integer.toString(this.campList.get(p).getCampCommitteeSlots()),
                cl.get(p).getDescription(),
                cl.get(p).getStaffInCharge(),
                String.valueOf(cl.get(p).getVisibility()?1:0),
            };
            temp.add(tarr);
        }
        return temp;
    }
    
    

    public void addCamp(Camp s){
        this.campList.add(s);
        DatabaseUtils.writeCamps(convertCampToStringArr(this.campList));
    }
}
