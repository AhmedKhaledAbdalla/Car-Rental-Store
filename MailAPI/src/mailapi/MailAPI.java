/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapi;

import javax.swing.JOptionPane;

/**
 *
 * @author moham
 */
public class MailAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         String[] to={"","admin@snehits.com"};
if( mail_class.sendMail("ahmedsowar@gmail.com","ahmed.2016","your account password is ", to))
System.out.println("email sent succsessfuly");
else System.out.println("some error ocurred");
 JOptionPane.showMessageDialog(null,"Password is sent to your Email Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
  
