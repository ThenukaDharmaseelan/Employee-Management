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
public class attendance {
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    static Connection con = null;
    
    static ResultSet listattendance(String id, String date)
    {
            String sql1 = "insert into itp.attendance(SID,dat,attendance)"+ "values('"+id+"','"+date+"','IN')";
     
     try {
                pst=con.prepareStatement(sql1);
                pst.execute();
               
            } catch (SQLException ex) {
                System.out.println(ex);
                //Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
            } 
     return rs;
    }
    
}
