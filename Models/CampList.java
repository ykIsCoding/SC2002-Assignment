package Models;

import Utils.DatabaseUtils;
import Utils.PageUtils;

import java.util.ArrayList;

/**
 * This class contains the list of all the Camps.
 */

public class CampList {
    ArrayList<Camp> campList = new ArrayList<>();

    public CampList(){
        
    }

    /**
     * This method retrieves all the camps from the Database
     * @return the list of all the camps
     */
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

    /**
     * Getter method for the camp list
     * @return the camp list
     */
    public ArrayList<Camp> getCampList(){
        return this.campList;
    }


    /**
     * This method gets the camp list sorting it by staff
     * @param StaffID is the ID of the staff
     * @return the new sorted list of the camps by staff
     */
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

    /**
     * This method gets the Camp List sorting it by faculty
     * @param userGroup is the user group such as Staff or Student.
     * @return the new sorted list of the Camps by faculty.
     */
    public ArrayList<Camp> getCampListByFacultyOrAll(String userGroup){
        ArrayList<Camp> newList = new ArrayList<>();
        for(int x=0;x<this.campList.size();x++){
            if(this.campList.get(x).getUserGroup().equalsIgnoreCase(userGroup) || this.campList.get(x).getUserGroup().equalsIgnoreCase("all")){
                newList.add(this.campList.get(x));
            }
        }
        this.campList= newList;
        return newList;
    }


    /**
     * This method attempts to edit the camp details
     * @param nc is the name of the camp
     * @return whether the camp is successfully edited
     */
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

    /**
     * Getter method for the index of the camp
     * @param idx is the index of the camp
     * @return the index of the camp in the camp list
     */
    public Camp getCampByIndex(int idx){
        return this.campList.get(idx);
    }


    /**
     * This method attempts to remove the camp from the Camp list
     * @param campID is the ID of the camp that is to be removed
     * @return whether it is successfully removed or not.
     */
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

    /**
     * This method converts the camp list to a string array
     * @param cl is the list of all the camps
     * @return the string array
     */
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

    /**
     * This method adds a camp into the Camp List
     * @param s is the camp that is to be added.
     */

    public void addCamp(Camp s){
        this.campList.add(s);
        DatabaseUtils.writeCamps(convertCampToStringArr(this.campList));
    }
}
