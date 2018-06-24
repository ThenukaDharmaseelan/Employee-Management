/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author DELL
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
public class leave {
     static ResultSet rs = null;
    static PreparedStatement pst = null;
    static Connection con = null;
    static ResultSet leaverequest(String s,String r,String from,String c)
    {
          try{ 
       String q = "INSERT INTO lea(SID,Reason,fm,T) values('"+s+"','"+r+"','"+from+"','"+c+"')";
         pst = con.prepareStatement(q);
         pst.execute();
         
          System.out.println("success");
    }
    catch(Exception e)
    {
        System.out.println("hello");
    }
          return rs;
    }
    
}
