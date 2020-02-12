package carrental;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import sun.util.BuddhistCalendar;

public class Recite implements Serializable {

    public String GetReceipt(String TypeOperation, int ID, double Price) throws IOException {
        int day, month, year;
        int second, minite, hour;
        GregorianCalendar date = new BuddhistCalendar();

        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        year = date.get(Calendar.YEAR);

        second = date.get(Calendar.SECOND);
        minite = date.get(Calendar.MINUTE);
        hour = date.get(Calendar.HOUR);

        //            Get receipt By GUI 
        System.out.println("the customer that her id is: " + ID + "  " + TypeOperation + " the car of price :" + Price);
        System.out.println("at " + day + "/" + (month + 1) + "/" + year);
        System.out.println("and this time" + hour + ":" + minite + ":" + second);

        String data = ID + "#" + Price + "#" + TypeOperation;
        String sdate = day + "/" + (month + 1) + "/" + year + "\t";
        String stime = hour + ":" + minite + ":" + second;
        String str = data + "#" + sdate + "#" + stime+"\n";
        //stor in file 
        FileManager recipt = new FileManager("E:\\Pl2Project\\Files\\recipts.bin");
        ArrayList<String> rec = new ArrayList<>();
        //add all data in array list

        if (recipt.readFromBin() != null) {
            rec = (ArrayList<String>) recipt.readFromBin();
            rec.add(str);
            recipt.WriteInBin(rec);

        } else {
            rec.add(str);
            recipt.WriteInBin(rec);
        }
return str;
    }

    public ArrayList<String> ViewAll() {
        ArrayList<String> A=new ArrayList<>();
        ArrayList<String> str=new ArrayList<>();
        ArrayList<String> ret=new ArrayList<>();
        try {
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\recipts.bin");
            if (file.readFromBin() != null) {
                str = (ArrayList<String>)file.readFromBin();
                for (String strg : str) {
                    String spl[] = strg.split("#");
                    for (int i = 0; i < spl.length; i++) {
                        A.add(spl[i]);
                    }
                    
                    ret = A;
                }
                return ret;
            }
        } catch (Exception e) {
            System.out.println("you don't have eny Recits ");
        }
        return ret;
    }

    public ArrayList<String> ViewOneRecite(int ID) {
        ArrayList<String> Reset=new ArrayList<>();
        ArrayList<String> A=new ArrayList<>();
        try {
            FileManager file = new FileManager("E:\\Pl2Project\\Files\\recipts.bin");
            ArrayList<String> rest = new ArrayList<>();
            if (file.readFromBin() != null) {

                rest = (ArrayList<String>) file.readFromBin();
                for (String str : rest) {
                    String spl[] = str.split("#");
                    if (ID == Integer.parseInt(spl[0])){
                    String spp[]=str.split("#");
                    for (int i = 0; i < spp.length; i++) {
                        A.add(spp[i]);
                    }}
                    Reset = A;
                }
                return Reset;
            }
        } catch (Exception e) {
            System.out.println("this recite not found ");
        }
        return Reset;
    }

    public void RemoveRecipt(double price, int ID) {
        FileManager delet = new FileManager("E:\\Pl2Project\\Files\\recipts.bin");
        ArrayList<String> modify = new ArrayList<>();
        modify = (ArrayList<String>) delet.readFromBin();
        for (int i = 0; i < modify.size(); i++) {
            String spl[] = modify.get(i).split("#");
            if ((ID == Integer.parseInt(spl[0])) && (price == Double.parseDouble(spl[1]))) {
                modify.remove(i);
                delet.WriteInBin(modify);

            }
        }

    }
}
