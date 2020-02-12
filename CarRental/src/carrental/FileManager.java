/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Exception;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager implements Serializable {

    public String FileName;

    public String FilePath;

    public String FileType;

    public FileManager(String path, String name, String type) throws IOException {
        this.FileName = name;
        this.FilePath = path;
        this.FileType = type;             // E://ahmed.text   FileManager file =new FileManager("E://","ahmed","text");
        File myfile = new File(path + name + "." + type);
        // To Create And Write into File 
    }

    public FileManager() {

    }

    public FileManager(String path) {
        this.FilePath = path;

    }
// @samir

    public void ReadFromFile() throws FileNotFoundException {
        // Print All Content In The file   
        Scanner read = new Scanner(new File(this.FilePath));
        while (read.hasNext()) {
            String line = read.nextLine();
            System.out.println(line);
        }
    }
//  @samir

    public void WriteInFile(String message) throws IOException {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(this.FilePath, true));
            wr.write(message);
            wr.newLine();
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// @Khaled

    public void DeleteRecord(int id) throws IOException {
        String Wanted;
        Wanted = Search(id);

        File inputFile = new File("myFile.txt");
        File tempFile = new File("myTempFile.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String lineToRemove = Wanted;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("#"));
            }
        }
        boolean successful = tempFile.renameTo(inputFile);

    }
// @Abo Ali

    public String Search(int id) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(this.FilePath));
            String read = new String();
            String fullfile = new String();
            try {
                while ((r.ready() == true)) {
                    read = r.readLine();
                    String[] x = read.split("#");

                    if (id == Integer.parseInt(x[0])) {
                        return x[1];
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.print(e);
            }
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 // @abobakr
    public String ReturnBalance() throws FileNotFoundException {
        // Print Just One Line   By  Reciept 
        String line = null;
        try {
            File f = new File(this.FilePath);
            if (f.exists() && (f.length() != 0)) {
                BufferedReader read = new BufferedReader(new FileReader(this.FilePath));
                line = read.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return line;

    }
   
    public void UpdateBalance(String message) throws IOException {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(this.FilePath))) {
            wr.write(message);
            wr.newLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Object readFromBin() {
        Object ret;
        ret = null;
        try {
            File f = new File(this.FilePath);
            if (f.exists() && (f.length() != 0)) // FILE EXCISTS AND NOT EMPTY 
            {
                ObjectInputStream obj = new ObjectInputStream(new FileInputStream(this.FilePath));
                ret = obj.readObject();
            }
        } catch (IOException | ClassNotFoundException c) {

        }

        return ret;
    }

    public void WriteInBin(Object c) {
        try {
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(this.FilePath));
            obj.writeObject(c);
            obj.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not Found" + ex);
            // Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Can Not Access data in file" + ex);
            // Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}