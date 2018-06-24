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
public class Grade {
     static ResultSet rs = null;
    static PreparedStatement pst = null;
    static Connection con = null;
 public static void assignclass(String sid, String name,String sub,String grade)
     {
         String q = "INSERT INTO itp.grade(SID,F_Name,subject,GradeID) values('"+sid+"','"+name+"','"+sub+"','"+grade+"')"; 
            try {
//               
                pst=con.prepareStatement(q);
                pst.execute();
              
            } 
            catch (SQLException ex) {
                System.out.println(ex);
               
            }
            
            
     }
    
    
}
