/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Vechils implements Serializable {

    public int RentPrice;
    public int BuyPrice;// add RentPrice &Buypric
    public static int id = 1;

    public String Model;

    public String Color;

    public int MaxSpeed;

    public String State;

    public double RentPerDay;

    public ArrayList<Date> VFrom = new ArrayList  <Date>();

    public ArrayList<Date> VTo= new ArrayList  <Date>();

    public String image;

}
