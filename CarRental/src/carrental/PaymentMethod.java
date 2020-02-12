// @abobakr
package carrental;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.BuddhistCalendar;

public class PaymentMethod implements Serializable {

    // public int balance=200;
    public static double Balance;

    public void Cash(String OperationType, int IDD, double Price) throws IOException {
        Recite recit = new Recite();
        if (OperationType.equalsIgnoreCase("Rent") || OperationType.equalsIgnoreCase("Buy")) {
            recit.GetReceipt(OperationType, IDD, Price);
            UpdatePrice(IDD, Price);
        } else if (OperationType.equalsIgnoreCase("Sell")) {

            UpdatePrice(IDD, -Price);
        } else if (OperationType.equalsIgnoreCase("cancel")) {
            recit.RemoveRecipt(Price, IDD);

            UpdatePrice(IDD, -Price);
        } 
    }

    public void UpdatePrice(int id, double price) throws FileNotFoundException, IOException {
        FileManager FM = new FileManager("E:\\Pl2Project\\Files\\CurrentBalance.txt");

        if (FM.ReturnBalance() != null) {
            Balance = Double.parseDouble(FM.ReturnBalance());
            double increse = Balance + price;
            FM.UpdateBalance("" + increse);
        } else {
            Balance = 1000000;
            double increse2 = Balance + price;
            FM.UpdateBalance("" + increse2);
        }
    }

    public double ViewBalance() {
        double CurrentBalance = 0;
        try {
            FileManager FM = new FileManager("E:\\Pl2Project\\Files\\CurrentBalance.txt");

            if (FM.ReturnBalance() != null) {
                CurrentBalance = Double.parseDouble(FM.ReturnBalance());
            } else {
                CurrentBalance = 1000000;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaymentMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CurrentBalance;
    }
}