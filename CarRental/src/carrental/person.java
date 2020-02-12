package carrental;

import java.io.Serializable;
import java.util.ArrayList;

public class person implements Serializable {

    public String Name;

    public String Gender;
    private String Password;

    public String email;

    //  public static int ID ;
    public boolean Login(int id, String pass) {
        boolean found = false;
        if (id == 0000 && pass.equalsIgnoreCase("admin")) {
            found = true;
        } else {
            ArrayList<Customer> Customerss = new ArrayList<>();
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
            if (file.readFromBin() != null) {
                Customerss = (ArrayList<Customer>) file.readFromBin();
                for (Customer c : Customerss) {
                    if (c.GetID() == id && pass.equalsIgnoreCase(c.GetPassword())) {
                        found = true;
                    }

                }
            }
        }
        return found;
    }

    /* public int Getid ()
    {
        return person.ID;
    }
     */
    public void SetPassword(String pass) {
        this.Password = pass;
    }

    public String GetPassword() {
        return this.Password;
    }

}
