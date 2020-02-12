package carrental;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class admin extends person implements Serializable {

    public void AddCar(Car c) throws IOException {
        try {
            ArrayList<Car> Cars = new ArrayList< Car>();
            String Formatter = "dd/mm/yyyy";
            String d = "30/2/1960";
            Date StaticDate = new Date();
            StaticDate = new SimpleDateFormat(Formatter).parse(d);
            
            
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
            if (file.readFromBin() != null) {
                Cars = (ArrayList<Car>) file.readFromBin();
            }
            c.VFrom.add(StaticDate);
            c.VTo.add(StaticDate);
            Cars.add(c);
            file.WriteInBin(Cars);
        } catch (ParseException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AddMotorCycle(MotorCycle c) throws IOException {

        ArrayList<MotorCycle> MotorCycles = new ArrayList< MotorCycle>();

        FileManager file = new FileManager("E:\\Pl2Project\\Files\\MotorCycles.obj");
        if (file.readFromBin() != null) {
            MotorCycles = (ArrayList<MotorCycle>) file.readFromBin();
        }

        MotorCycles.add(c);

        file.WriteInBin(MotorCycles);

    }

    public void AddFromStore(String type, int CarId) throws IOException {
        if (type.equalsIgnoreCase("Car")) {
            ArrayList<Car> Cars = new ArrayList< Car>();
            FileManager fm = new FileManager("E:\\Pl2Project\\Files\\CarStore.bin");
            Car c = new Car();
            if (fm.readFromBin() != null) {
                Cars = (ArrayList<Car>) fm.readFromBin();
                for (Car Car1 : Cars) {
                    if (Car1.GetcarId() == CarId) {
                        ArrayList<Car> CarMod = new ArrayList< Car>();
                        FileManager file = new FileManager("E:\\Pl2Project\\Files\\Cars.bin");
                        if (file.readFromBin() != null) {
                            CarMod = (ArrayList<Car>) file.readFromBin();
                            CarMod.add(Car1);
                            file.WriteInBin(CarMod);
                            Cars.remove(Car1);
                        }

                    }
                }
                           }
            if (type.equalsIgnoreCase("Car")) {
                ArrayList<MotorCycle> MotorCycles = new ArrayList< MotorCycle>();
                FileManager file = new FileManager("E:\\Pl2Project\\Files\\CarStore.bin");
                if (fm.readFromBin() != null) {
                MotorCycles = (ArrayList<MotorCycle>) fm.readFromBin();
                for (MotorCycle MotorCycle1 : MotorCycles) {
                    if (MotorCycle1.GetMotorId() == CarId) {
                        ArrayList<MotorCycle> MotorCycleMod = new ArrayList< MotorCycle>();
                        FileManager f = new FileManager("E:\\Pl2Project\\Files\\MotorCycles.bin");
                        if (f.readFromBin() != null) {
                            MotorCycleMod = (ArrayList<MotorCycle>) file.readFromBin();
                            MotorCycleMod.add(MotorCycle1);
                            file.WriteInBin(MotorCycleMod);
                            MotorCycles.remove(MotorCycle1);
                        }

                    }
                }
               
            }

            }

        }
    }

    public ArrayList<String> ManageBooking() {
        FileManager fm = new FileManager("E:\\Pl2Project\\Files\\Manage.bin");
        ArrayList<String> rec = null;
        ArrayList <String> temp = null;
        if (fm.readFromBin() != null) {
            rec = new ArrayList<>();
            rec = (ArrayList<String>) fm.readFromBin();
            temp = new ArrayList();
            for (int i = 0 ; i < rec.size() ; i++) {
                String spl[] = rec.get(i).split("#");
                String line = "Customer ID: " + spl[0] + " Car ID: " + spl[1] + " Date from : " + spl[2] + " Date To :" + spl[3];
                temp.add(line);
                Customer c = new Customer();
                c.Rent("true", rec.get(i));
            }
        }
        return temp;
    }

    public ArrayList ViewInquiry() {
        FileManager FMQ = new FileManager("E:\\Pl2Project\\Files\\Query.bin");
        ArrayList<String> quiries = new ArrayList<>();
        quiries = (ArrayList<String>) FMQ.readFromBin();
        ArrayList<String> EmotyQuiries = new ArrayList<>();
        FMQ.WriteInBin(EmotyQuiries);
        return quiries;
    }

    public void takeAnswer(ArrayList quiries) {
        for (Object quiry : quiries) {

        }
        //return "this  ID " + s[0] + " make this query: " + s[1]; 
    }

    public void SolveInquiry(int CustId, String Ans) throws MessagingException {
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
        ArrayList<Customer> cust = new ArrayList<>();
        cust = (ArrayList<Customer>) file.readFromBin();
        for (int a = 0; a < cust.size(); a++) {
            if (CustId == (cust.get(a).GetID())) {
                String[] mail = {cust.get(a).email};
                sendMail(Ans, mail);
            }
        }

    }

    //  Customer cus = new Customer();
    // int IDinfile = 0;
    /*for (int i = 0; i < quiries.size(); i++) {
                String s[] = quiries.get(i).split("#");
                return "this  ID " + s[0] + " make this query: " + s[1];
                IDinfile = Integer.parseInt(s[0]);
                //search of Email using id 
                FileManager FMC = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
                ArrayList<Customer> cust = new ArrayList<>();
                cust = (ArrayList<Customer>) FMC.readFromBin();
                for (int a = 0; a < cust.size(); a++) {
                    if (IDinfile == (cust.get(a).GetID())) {
                        String[] mail = {cust.get(a).email};
                        sendMail(Ans, mail);
                    }
                }
            }
            quiries.clear();            //clear all query that we are replay of it.
            FMQ.WriteInBin(quiries);
        } catch (Exception e) {
            e.printStackTrace();
     */
    public String CustHistory(int id) throws IOException {
        ArrayList< String> His = new ArrayList<>();
        FileManager file = new FileManager("E:\\Pl2Project\\Files\\History.bin");
        if (file.readFromBin() != null) {
            His = (ArrayList<String>) file.readFromBin();

            for (String s : His) {
                String[] ss = s.split("#");
                int x = Integer.parseInt(ss[0]);
                if (x == id) {
                    return ss[1];
                }
            }
        }

        return "Not Fuonded";
    }

    public boolean sendMail(String message, String to[]) throws javax.mail.MessagingException {
        String from = "carrental.4am848@gmail.com";
        String password = "Asdahmed20150044";

        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }
        for (int i = 0; i < toAddress.length; i++) {
            mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
            mimeMessage.setSubject("Clinic Support");
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        }
        return false;
    }

    public void ViewAllReport() {

        FileManager f = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
        ArrayList<Customer> Customers = new ArrayList<>();
        if (f.readFromBin() != null) {
            Customers = (ArrayList<Customer>) f.readFromBin();
        }
        ArrayList<String> Report = new ArrayList<>();
        for (Customer a : Customers) {
            try {
                Report.add(CustHistory(a.GetID()) + a.Name);
            } catch (IOException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList dailyReport() {
        Date d = new Date();
        FileManager f = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
        ArrayList<Customer> Customers = new ArrayList<>();
        if (f.readFromBin() != null) {
            Customers = (ArrayList<Customer>) f.readFromBin();
        }
        ArrayList<String> DReport = new ArrayList<>();
        for (Customer a : Customers) {
            try {
                String[] x = CustHistory(a.GetID()).split("#");
                if (d.toString().equals(x[2])) {
                    DReport.add(CustHistory(a.GetID()) + a.Name);
                }

            } catch (IOException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return DReport;
    }


    /*

    public ArrayList monthlyReport() {
        Date d = new Date();
        FileManager f = new FileManager("E:\\Pl2Project\\Files\\Customers.bin");
        ArrayList<Customer> Customers = new ArrayList<>();
        if (f.readFromBin() != null) 
        {
            Customers = (ArrayList<Customer>) f.readFromBin();
        }
        ArrayList<String> MReport = new ArrayList<>();
        for (Customer a : Customers) {
            try {
                String[] x = CustHistory(a.GetID()).split("#");
                if (d.toString().s(x[2])) {
                    MReport.add(CustHistory(a.GetID()) + a.Name);
                }

            } catch (IOException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return MReport;
    }

     */
}
