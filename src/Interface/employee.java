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

public class employee {
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    static Connection con = null;
   

    static ResultSet updateemployee(String fname,String lname,String add,int MobileNo,String DOB,String jdate,String subj,String JType,String gen,String em,String basicSal,String s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         String sql ="Update itp.employee SET F_Name ='"+fname+"',L_Name ='"+lname+"',Address ='"+add+"',MobileNo ='"+MobileNo+"',DOB ='"+DOB+"',JoinDate='"+jdate+"',subject='"+subj+"',JobType='"+JType+"',Gender='"+gen+"',Email='"+em+"',BasicSalary='"+ basicSal+"' where SID = '"+s+"'";
            
            try {
                 // int suces=pst.executeUpdate(); 
//         if(suces>0){
//               
//                JOptionPane.showMessageDialog(null,"Details are sucessfully updated");
//              } 
//              else{
//              
//                JOptionPane.showMessageDialog(null,"Unable to update data");
//              }
                pst=con.prepareStatement(sql);
                System.out.println(pst);
                pst.execute();
               //Main.tableload();
            } catch (SQLException ex) {
                System.out.println(ex);//Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
            }
          return rs;
    }

  static ResultSet deleteemployee(String s)
  {
      //String s =sid.getText();
            
            String sql = "DELETE FROM `employee` WHERE SID ='"+s+"'";
            
            try {
                pst= con.prepareStatement(sql);
                 pst.execute();
                 //tableload();
                 
            } catch (SQLException ex) {
                System.out.println(ex);//Logger.getLogger(DeleteMember.class.getName()).log(Level.SEVERE, null, ex);
            }
              return rs;
      
  }
  
  static ResultSet searchemployee(String ST)
  {
      
       String q1="Select * From itp.employee  where SID LIKE '%"+ST+"%'";
       try
      {
           pst = con.prepareStatement(q1);
          rs = pst.executeQuery();
         // staff.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(Exception e)
       {
           System.out.println(e.getMessage());
     //  }
    } 
       return rs;
  }
  
            
    }
