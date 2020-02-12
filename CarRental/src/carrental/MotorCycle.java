/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

// Motor generate Id 

import java.util.ArrayList;

public class MotorCycle extends Vechils {
   
    public String HelmetType;

    public String MotorCycleType;

    private int MotorId = 1 ;
    public MotorCycle ()
    {
        
    }
    public void MotorCycleInfo() {
         System.out.println(" MotorCycle Color Is : "+this.Color+
                " MotorCycle Type Is : "+ this.MotorCycleType+" MotorCycle Rent Price Is : "+this.RentPrice +" MotorCycle Buy Price Is : "+this.BuyPrice);
    }
    
    public int GenerateId() {
        int id = 1;
        ArrayList< MotorCycle> MotorCycles = new ArrayList< MotorCycle >();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\MotorCycles.bin");        
        if (file.readFromBin() != null) {
            MotorCycles = (ArrayList<MotorCycle>) file.readFromBin();
            id = MotorCycles.size() + 1;
        }
      return id;
    }
    public int GetMotorId ()
    {
        return MotorId ;
    }
}
