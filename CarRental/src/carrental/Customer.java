package carrental;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class Customer extends person implements Serializable {

    Date date = new Date();
    public int Licence;
    public String Address;
    private int ID = 1;
    int CarId;
    public String VecType;

    // Complete Abo Ali
    // creat outomatic file
    public void SignUp(String Name, String Gender, String Password, String email, int Licence, String Address) {
        Customer c = new Customer();
        c.Name = Name;
        c.Gender = Gender;
        c.SetPassword(Password);
        c.email = email;
        c.Licence = Licence;
        c.Address = Address;
        c.ID = GenerateId();
        ArrayList<Customer> Customers = new ArrayList< Customer>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
        if (file.readFromBin() != null) {
            Customers = (ArrayList<Customer>) file.readFromBin();
            Customers.add(c);
            file.WriteInBin(Customers);
        } else {
            Customers.add(c);
            file.WriteInBin(Customers);
        }
        admin ad = new admin();
        String[] To = {email};
        try {
            if (ad.sendMail(" Your ID Is : " + GenerateId(), To)) {
                JOptionPane.showMessageDialog(null, "Your Id Is : " + GenerateId(), "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Send Your Id To Mail ", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Samir
    public int GenerateId() {
        int id = 1;
        ArrayList< Customer> Customers = new ArrayList< Customer>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
        if (file.readFromBin() != null) {
            Customers = (ArrayList<Customer>) file.readFromBin();
            id = Customers.size() + 1;
        }
        return id;
    }

    // Khaled W Samir
    public boolean SelectSchedule(Date CFrom, Date CTo, int CarId) throws IOException {
        boolean chick = false;
        FileManager fm = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
        if (VecType.equalsIgnoreCase("Car")) {
            ArrayList< Car> Cars = new ArrayList<>();
            Cars = AccessCarsFile();
            Date VecFrom;
            Date VecTo;
            if (CFrom.before(CTo)) {

                for (Car c : Cars) {
                    if (c.VFrom.isEmpty()) {
                        c.VFrom.add(CFrom);
                        c.VTo.add(CTo);
                        fm.WriteInBin(Cars);
                        Request(this.ID, CarId, CFrom, CTo);
                        Save(ID + "#" + "Select Schedual At " + date.toString());
                        return true;
                    } else {
                        for (int i = 0; i < c.VFrom.size(); i++) {
                            VecFrom = c.VFrom.get(i);
                            VecTo = c.VTo.get(i);
                            if (CFrom.after(VecFrom) && CFrom.after(VecTo)) {
                                c.VFrom.add(CFrom);
                                c.VTo.add(CTo);
                                fm.WriteInBin(Cars);
                                Request(this.ID, CarId, CFrom, CTo);
                                Save(ID + "#" + "Select Schedual At " + date.toString());
                                return true;
                            } else if (CFrom.before(VecFrom) && CTo.before(VecFrom)) {
                                c.VFrom.add(CFrom);
                                c.VTo.add(CTo);
                                fm.WriteInBin(Cars);
                                Request(this.ID, CarId, CFrom, CTo);
                                Save(ID + "#" + "Select Schedual At " + date.toString());
                                return true;
                            }
                        }
                    }

                }

            }
        } else {
            ArrayList< MotorCycle> MotorCycles = new ArrayList<>();
            MotorCycles = AccessMotorFile();
            Date VecFrom;
            Date VecTo;
            if (CFrom.before(CTo)) {
                for (MotorCycle c : MotorCycles) {
                    if (c.VFrom.isEmpty()) {
                        c.VFrom.add(CFrom);
                        c.VTo.add(CTo);
                        fm.WriteInBin(MotorCycles);
                        Request(this.ID, CarId, CFrom, CTo);
                        Save(ID + "#" + "Select Schedual At " + date.toString());
                        return true;
                    } else {
                        for (int i = 0; i < c.VFrom.size(); i++) {
                            VecFrom = c.VFrom.get(i);
                            VecTo = c.VTo.get(i);
                            if (CFrom.after(VecFrom) && CFrom.after(VecTo)) {
                                c.VFrom.add(CFrom);
                                c.VTo.add(CTo);
                                fm.WriteInBin(MotorCycles);
                                Request(this.ID, CarId, CFrom, CTo);
                                Save(ID + "#" + "Select Schedual At " + date.toString());
                                return true;
                            } else if (CFrom.before(VecFrom) && CTo.before(VecFrom)) {
                                c.VFrom.add(CFrom);
                                c.VTo.add(CTo);
                                fm.WriteInBin(MotorCycles);
                                Request(this.ID, CarId, CFrom, CTo);
                                Save(ID + "#" + "Select Schedual At " + date.toString());
                                return true;
                            }
                        }
                    }
                }

            }
        }

        return chick;
    }

    public boolean Reschedule(Date NewCfrom, Date NewCto, Date OldCfrom, Date OldCto, int VecId) throws IOException {
        if (VecType.equalsIgnoreCase("Car")) {
            FileManager fm = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
            ArrayList< Car> Cars = new ArrayList<>();
            Cars = AccessCarsFile();
            // Date VecFrom;
            // Date VecTo;
            if (NewCfrom.before(NewCto)) {
                for (Car c : Cars) {
                    if (VecId == c.GetcarId()) {
                        for (int i = 0; i < c.VFrom.size(); i++) {
                            if (c.VFrom.get(i).equals(OldCfrom) && c.VTo.get(i).equals(OldCto)) {
                                c.VFrom.remove(i);
                                c.VTo.remove(i);
                                c.VFrom.add(NewCfrom);
                                c.VTo.add(NewCto);
                            }
                        }
                    }
                }
                fm.WriteInBin(Cars);
            }
            Request(this.ID, CarId, NewCfrom, NewCto);
            Save(ID + "#" + "Select Schedual At " + date.toString());
            return true;
        } else if (VecType.equalsIgnoreCase("MotorCycle")) {
            FileManager fm = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
            ArrayList< MotorCycle> MotorCycles = new ArrayList<>();
            MotorCycles = AccessMotorFile();
            // Date VecFrom;
            // Date VecTo;
            if (NewCfrom.before(NewCto)) {
                for (MotorCycle c : MotorCycles) {
                    if (VecId == c.GetMotorId()) {
                        for (int i = 0; i < c.VFrom.size(); i++) {
                            if (c.VFrom.get(i).equals(OldCfrom) && c.VTo.get(i).equals(OldCto)) {
                                c.VFrom.remove(i);
                                c.VTo.remove(i);
                                c.VFrom.add(NewCfrom);
                                c.VTo.add(NewCto);
                            }
                        }
                    }
                }
                fm.WriteInBin(MotorCycles);
            }
        }
        Save(ID + "#" + "Select Schedual At " + date.toString());
        return true;
    }

    public void CancelBooking(int DEcust_id, int CarId) throws IOException {
        Car b = new Car();
        ArrayList<Car> Cars = new ArrayList<Car>();
        Cars = AccessCarsFile();
        for (Car c : Cars) {
            if (c.GetcarId() == CarId) {
                c.VFrom.remove(c);
                c.VTo.remove(c);

                //PaymentMethod p = new PaymentMethod(-Cars.get(CarId - 1).Price);
                PaymentMethod price = new PaymentMethod();
                price.Cash("cancel", ID, c.RentPrice);
                ArrayList<String> Data = new ArrayList<>();
                FileManager file = new FileManager("E:\\Pl2Project\\Files\\Rent.bin");
                if (file.readFromBin() != null) {
                    Data = (ArrayList<String>) file.readFromBin();
                }
                for (int i = 0; file.readFromBin() != null; i++) {

                    String[] chick = Data.get(i).split("#");

                    if (Integer.parseInt(chick[0]) == DEcust_id) {
                        Data.remove(Data.get(i));
                    }
                }
                file.WriteInBin(Data);

                Save(ID + "#" + "Cancel At " + date.toString());
            }
        }
    }
    // Write Query In Query File

    public void WriteText(int CustID, String method, String text) throws IOException {                            // Make Query Done 
        String s = new String();
        Customer c = new Customer();
        s = CustID + "#" + text + "\n";

        if (method.equalsIgnoreCase("MakeQuery")) {
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\Query.txt");
            file.WriteInFile(s);
        } else if (method.equalsIgnoreCase("GiveFeedBack")) {
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\FeedBack.txt");
            file.WriteInFile(s);
        }
        Save(CustID + "#" + method + date.toString());

    }

    /*
    public void GiveFeedBack(String Feedback) throws IOException {                                     // Give FeedBack Done 
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\FeedBack.text");
        String s = new String();
        s = ID + "#" + Feedback + "\n";
        file.WriteInFile(s);

        Save(ID + "#" + "Give Feedback At " + date.toString());
    }
     */
    private ArrayList< Car> AccessCarsFile() {
        ArrayList< Car> Cars = new ArrayList<>();
        try {
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
            if (file.readFromBin() != null) {
                Cars = (ArrayList<Car>) file.readFromBin();
            }
            return Cars;
        } catch (Exception e) {
            System.out.println("Fail to Access File ");
            return null;
        }
    }

    private ArrayList AccessMotorFile() {
        ArrayList<MotorCycle> MotorCycles = new ArrayList<>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\MotorCycles.obj");
        if (file.readFromBin() != null) {
            MotorCycles = (ArrayList<MotorCycle>) file.readFromBin();
        }
        return MotorCycles;
    }

    public ArrayList ViewallVec() throws IOException {                                      // View All Cars Done 

        if (VecType.equalsIgnoreCase("Car")) {
            ArrayList<Car> Cars = new ArrayList<>();
            Cars = AccessCarsFile();
            Save(ID + "#" + "View All " + VecType + " At " + date.toString());
            return Cars;
        } else {
            ArrayList<MotorCycle> MotorCycles = new ArrayList();
            MotorCycles = AccessMotorFile();
            Save(ID + "#" + "View All " + VecType + " At " + date.toString());
            return MotorCycles;
        }
    }

    public ArrayList SearchByprice(double From, double To) throws IOException {
        if (VecType.equalsIgnoreCase("MotorCycle")) {
            ArrayList<MotorCycle> RecMotorCycles = new ArrayList();
            ArrayList<MotorCycle> MotorCycles = new ArrayList();
            MotorCycles = AccessMotorFile();
            for (MotorCycle c : MotorCycles) {
                if (c.BuyPrice >= From && c.BuyPrice <= To) {
                    RecMotorCycles.add(c);
                }
            }
            Save(ID + "#" + "Select " + VecType + " Price At " + date.toString());
            return RecMotorCycles;
        } else {
            ArrayList<Car> RecCars = new ArrayList<>();
            ArrayList<Car> Cars = new ArrayList<>();
            Cars = AccessCarsFile();
            for (Car c : Cars) {
                if (c.BuyPrice >= From && c.BuyPrice <= To) {
                    RecCars.add(c);
                }
            }
            Save(ID + "#" + "Select " + VecType + " Price At " + date.toString());
            return RecCars;
        }
    }

    public ArrayList SearchByModel(String Model) throws IOException {
        ArrayList<MotorCycle> RecMotorCycles = new ArrayList();
        ArrayList<MotorCycle> MotorCycles = new ArrayList();
        if (VecType.equals("MotorCycle")) {
            MotorCycles = AccessMotorFile();

            for (MotorCycle c : MotorCycles) {
                if (c.Model.equalsIgnoreCase(Model)) {
                    RecMotorCycles.add(c);

                }
            }
            Save(ID + "#" + "Select Vechel model At " + date.toString());
            return RecMotorCycles;

        } else {
            ArrayList<Car> RecCars = new ArrayList<Car>();
            ArrayList<Car> Cars = new ArrayList<Car>();
            Cars = AccessCarsFile();
            for (Car c : Cars) {
                if (c.Model.equalsIgnoreCase(Model)) {
                    RecCars.add(c);
                }
            }
            Save(ID + "#" + "Select Vechel model At " + date.toString());
            return RecCars;
        }
    }

    // Pre Validate If This Vechile Is Car Or MotorCycle
    // After Customer Enter His Car Data . I Was Saving It in StoreFile 
    // Now Should I Get Id Of Accepted Car In StoreFile to Add It To The Car File (Agance) And Minus Balance Then Send Confrimation Mail
    public void Sell(Object c) throws IOException {
        if (VecType.equalsIgnoreCase("Car")) {
            ArrayList<Car> Cars = new ArrayList< Car>();
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\CarStore.obj");
            if (file.readFromBin() != null) {
                Cars = (ArrayList<Car>) file.readFromBin();
            }
            Cars.add((Car) c);
            file.WriteInBin(Cars);
        } else {
            ArrayList<MotorCycle> MotorCycles = new ArrayList<>();
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\MotorCycleStore.obj");
            if (file.readFromBin() != null) {
                MotorCycles = (ArrayList<MotorCycle>) file.readFromBin();
            }

            MotorCycles.add((MotorCycle) c);
            file.WriteInBin(MotorCycles);
        }
        Save(ID + "#" + "Give MotorCycle Info to System At " + date.toString());
    }

    private void Save(String s) throws IOException {
        ArrayList< String> His = new ArrayList<>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\History.bin");
        if (file.readFromBin() != null) {
            His = (ArrayList<String>) file.readFromBin();
        }
        His.add(s);
        file.WriteInBin(His);
    }

    ArrayList SearchBydate(Date CFrom, Date CTo) {

        if (VecType.equalsIgnoreCase("Car")) {
            ArrayList< Car> Cars = new ArrayList<>();
            ArrayList< Car> RecCars = new ArrayList<>();
            Cars = AccessCarsFile();
            Date VecFrom;
            Date VecTo;
            if (CFrom.before(CTo)) {
                for (Car c : Cars) {
                    for (int i = 0; i < c.VFrom.size(); i++) {
                        VecFrom = c.VFrom.get(i);
                        VecTo = c.VTo.get(i);
                        if (CFrom.after(VecFrom) && CFrom.after(VecTo)) {
                            RecCars.add(c);
                        } else if (CFrom.before(VecFrom) && CTo.before(VecFrom)) {
                            RecCars.add(c);
                        }
                    }
                }
                return RecCars;
            }
        } else {
            ArrayList< MotorCycle> MotorCycles = new ArrayList<>();
            ArrayList< MotorCycle> RecMotorCycles = new ArrayList<>();
            MotorCycles = AccessMotorFile();
            Date VecFrom;
            Date VecTo;
            for (MotorCycle m : MotorCycles) {
                for (int i = 0; i < m.VFrom.size(); i++) {
                    VecFrom = m.VFrom.get(i);
                    VecTo = m.VTo.get(i);
                    if (CFrom.after(VecFrom) && CFrom.after(VecTo)) {
                        RecMotorCycles.add(m);
                    } else if (CFrom.before(VecFrom) && CTo.before(VecFrom)) {
                        RecMotorCycles.add(m);
                    }
                }
            }
            return RecMotorCycles;
        }
        return null;
    }

    public boolean Buy(int id) throws IOException {
        boolean Successfull = false;
        ArrayList<Car> Carss = new ArrayList<Car>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
        if (file.readFromBin() != null) {
            Carss = (ArrayList<Car>) file.readFromBin();
            for (Car a : Carss) {
                if (a.GetcarId() == id) {
                    PaymentMethod p = new PaymentMethod();
                    p.Cash("buy", ID, a.BuyPrice);
                    Carss.remove(id - 1);
                    Successfull = true;
                    Save(ID + "#" + "By At " + "#" + date.toString());
                }
            }
            file.WriteInBin(Carss);
        }
        return Successfull;
    }

    // Pre Search By Date Should Called Then Choose Wanted Car To Rent Then Pass It's ID And Customers wanted Date to Rent From And to 
    public void Rent(String respond, String line) {

        if (respond.equals("true")) {
            String Formater = "dd/MM/yyyy";
            String spl[] = line.split("#");
            int VecId = Integer.parseInt(spl[1]);
            String from = spl[2];
            String to = spl[3];

            String split[] = from.split("/");
            int yearFrom, dayFrom, monthFrom;
            dayFrom = Integer.parseInt(split[0]);
            monthFrom = Integer.parseInt(split[1]);
            yearFrom = Integer.parseInt(split[2]);

            //int rtrnMonFrom = calculateMonth(monthFrom);
            // split To
            String splitt[] = to.split("/");
            int yearTo, dayTo, monthTo;
            dayTo = Integer.parseInt(splitt[0]);
            monthTo = Integer.parseInt(splitt[1]);
            yearTo = Integer.parseInt(splitt[2]);

            String sfrom = dayFrom + "/" + monthFrom + "/" + yearFrom;
            Date Vfrom = new Date();
            try {
                Vfrom = new SimpleDateFormat(Formater).parse(sfrom);
            } catch (ParseException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sto = dayTo + "/" + monthTo + "/" + yearTo;
            Date Vto = new Date();
            try {
                Vto = new SimpleDateFormat(Formater).parse(sto);
            } catch (ParseException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (VecType.equalsIgnoreCase("Car")) {

                ArrayList<Car> Cars = new ArrayList<>();
                Cars = AccessCarsFile();
                for (Car Car1 : Cars) {
                    if (VecId == Car1.GetcarId()) {
                        ArrayList<Date> Listfrom = new ArrayList<>();
                        ArrayList<Date> Listto = new ArrayList<>();
                        Car1.VFrom.add(Vfrom);
                        Car1.VTo.add(Vto);
                        //Cars.add(Listto);
                    }
                }
                FileManager file = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
                file.WriteInBin(Cars);

            }

            double Price = calculatePrice(monthTo, monthFrom, dayTo, dayFrom, VecId);
            PaymentMethod ca = new PaymentMethod();
            try {
                ca.Cash("rent", VecId, Price);
            } catch (IOException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void Request(int Cid, int CarId, Date Cfrom, Date Cto) throws IOException {
        String Formater = "dd/MM/yyyy";
        FileManager fm = new FileManager("E:\\Pl2Project\\Files\\Manage.bin");
        //ArrayList<Car> Cars = new ArrayList<Car>();
        ArrayList<String> data = new ArrayList<>();
        String Date1 = new SimpleDateFormat(Formater).format(Cfrom);
        String Date2 = new SimpleDateFormat(Formater).format(Cto);
        String Booking = GetID() + "#" + CarId + "#" + Date1 + "#" + Date2;
        if (fm.readFromBin() != null) {

            data = (ArrayList<String>) fm.readFromBin();
            data.add(Booking);
        } else {
            data.add(Booking);
        }
        fm.WriteInBin(data);
        Save(ID + "#" + "Rent At " + date.toString());
    }

    public int calculatePrice(int monthTo, int monthFrom, int dayTo, int dayFrom, int vecId) {
        int DayNum = 0;
        if (monthFrom == monthTo) {
            DayNum = (dayTo - dayFrom) + 1;
        } else if (monthTo > monthFrom) //
        {
            DayNum = 31 + (dayTo - dayFrom);
        }
        int TotalPrice = 0;

        if (VecType.equalsIgnoreCase("Car")) {
            ArrayList<Car> Cars = new ArrayList<>();
            Cars = AccessCarsFile();

            for (Car Car1 : Cars) {
                if (vecId == Car1.GetcarId()) {
                    TotalPrice = DayNum * Car1.RentPrice;
                }
            }
        } else if (VecType.equalsIgnoreCase("MotorCycle")) {
            ArrayList<MotorCycle> MotorCycles = new ArrayList<>();
            MotorCycles = AccessMotorFile();
            for (MotorCycle MotorCycle1 : MotorCycles) {
                if (vecId == MotorCycle1.GetMotorId()) {
                    TotalPrice = DayNum * MotorCycle1.RentPrice;
                }
            }

        }
        return TotalPrice;
    }

    public int GetID() {
        return this.ID;
    }
}
