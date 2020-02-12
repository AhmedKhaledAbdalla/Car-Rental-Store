/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental ;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Car  extends Vechils  implements Serializable  {
  
    public int NumSeat;

    public String Control;
    //delet CarType
    
    private  int CarId ;
    
    public Car()
    {
        Vechils.id ++;
        CarId = GenerateId();
    }
       
    public void CarInfo() {
        System.out.println("Vechile Type Is: "+" Car Color Is : "+this.Color+
                " Number Of Seats Is : "+NumSeat+" Control Is : "+Control
                +" Car Rent Price Is : "+this.RentPrice +"Car Buy Price = "+ this.BuyPrice + "Car ID Is : "+ CarId +"\n"+" From " +this.VFrom +" To "+this.VTo 
        );
    }
    public int GenerateId() {
        
        int id = 1;
        ArrayList< Car> Cars = new ArrayList< Car>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");        
        if ( file.readFromBin() != null ) {
            Cars = (ArrayList<Car>) file.readFromBin();
          id = Cars.size() + 1;
        }
      return id;
    }
    public int GetcarId ()
    {
        return CarId ;
    }
    public void SetcarId( int id )
    {
        CarId = id ;
    }
}