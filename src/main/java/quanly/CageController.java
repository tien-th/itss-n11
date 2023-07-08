package quanly;

import entity.*;

import java.util.List;


public class CageController {
   private List<Cage> cages;
   public  CageController(List<Cage> cages){
       this.cages = cages;
   }
    public void addCage(Cage cage){
         cages.add(cage);
    }
    public void removeCage(Cage cage){
        cages.remove(cage);
    }
    public void updateCage(Cage cage){
        for (Cage c : cages){
            if (c.getId_cage() == cage.getId_cage()){
                c.setStatus(cage.isStatus());
                c.setPet(cage.getPet());
            }
        }
    }
    public void showCage(){
        for (Cage c : cages){
            System.out.println(c.toString());
        }
    }
    public void showCageEmpty(){
        for (Cage c : cages){
            if (c.isStatus()){
                System.out.println(c.toString());
            }
        }
    }
    public void showCageFull(){
        for (Cage c : cages){
            if (!c.isStatus()){
                System.out.println(c.toString());
            }
        }
    }

}
