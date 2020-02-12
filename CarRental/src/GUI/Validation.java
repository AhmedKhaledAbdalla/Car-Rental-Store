/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Menna Allah Ayman
 */
public class Validation {
    public static boolean email_val(String email){
        boolean status = false;
      //  String em = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)(\\.[A-Za-z]{2,})$ ";
          String em ="@";
        Pattern pattern = Pattern.compile(em);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
        {
            status = true;
        }
        else
        {
            status= false;
        }
    return status;
    }
    
    public static boolean name_val(String name)
    {
         boolean status = false;
        String n = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(n);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches())
        {
         status = true;
        }
        return status;     
  }
}
