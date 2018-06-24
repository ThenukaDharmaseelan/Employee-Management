/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import admin.Dbconnection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.view.*;
/**
 *
 * @author DELL
 * 
 */













public class Main extends javax.swing.JFrame {
 Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    ResultSet rs1=null;
   ResultSet rs2=null;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        con = Dbconnection.connect();
        tableload();

        tableload2();
        LeaveAccOrregTableLoad();
                  
      String ab = test.getNextID("SID", "employee","ST", con);
           sid.setText(ab);
            sid.setEditable(false);
       StaffRegister.setVisible(false);
       Request_Leave.setVisible(false);
       Accept_Reject.setVisible(false);
       AssignClass.setVisible(false);  
       Home.setVisible(true);
        
      a.setEditable(false);
      //jbType2.setEditable(false);
      jTextArea2.setEditable(false);
      jTextField1.setEditable(false);
      jTextField6.setEditable(false);
       Attendance.setVisible(false);
      
       
    }
    
    
       private void LeaveAccOrregTableLoad1() {
       try{
            String sql1="Select SID,Reason,fm,T From lea ";
           pst=con.prepareStatement(sql1);
           rs=pst.executeQuery();
            
         
           lea.setModel(DbUtils.resultSetToTableModel(rs));
       }
      catch(Exception e){
           System.out.println(e);
      } //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    private void LeaveAccOrregTableLoad() {
       try{
            String sql1="Select * From lea ";
           pst=con.prepareStatement(sql1);
           rs=pst.executeQuery();
            
         
           leave.setModel(DbUtils.resultSetToTableModel(rs));
       }
      catch(Exception e){
           System.out.println(e);
      } //To change body of generated methods, choose Tools | Templates.
    }
    private void attendTableLoad() {
       try{
            String sql1="Select * From attendances_s ";
           pst=con.prepareStatement(sql1);
           rs=pst.executeQuery();
            
         
           attend.setModel(DbUtils.resultSetToTableModel(rs));
       }
      catch(Exception e){
           System.out.println(e);
      } //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
        public static boolean validateName(String name){
    
        boolean b= true;
        String namePattern="^[a-zA-Z]+$";
        Pattern pattern=Pattern.compile(namePattern);
        Matcher matcher=pattern.matcher(name);
        
        if(matcher.matches())
          b=true;
        else
            b=false;
       
           return b;
        
    }
        
         public static boolean validateContact(String tp){
 
      boolean d=true;
      String conpattern="^[0][7][0-9]{8}";
      Pattern pattern=Pattern.compile(conpattern);
      Matcher matcher=pattern.matcher(tp);
        
      if(matcher.matches())
        d=true;
      
       else
          d=false;
      
       return d;
  }
    
    
    public  boolean search_validate(String validname){
          boolean ser = false; 
          String name = null;
       
         try{
            
              String sql_st="select F_Name from employee";
              rs=pst.executeQuery(sql_st);
              while(rs.next()){
                  
                          name=rs.getString("F_Name");
                          //System.out.println(name);
                           if(validname.equals(name)) {
                               ser=true;
                             }
                       } 
              
                         
             }catch(Exception e){
         
             System.out.println("serach in firstname"+e.getMessage());  
             JOptionPane.showConfirmDialog(null,"invalid name");
        }
           return ser;
       
     }
    
    public boolean checkMobile(){
       
 boolean b=false;
 
 try{       
 
     String sql="select *  from employee";
    
     rs=pst.executeQuery(sql);
    
     while(rs.next()){
    
        if(!rs.getString("MobileNo").equals(M_no.getText())){
            
           
           b=true;
             
       }
       
        else{
         
         b=false;
         JOptionPane.showMessageDialog(null,M_no.getText()+"  already existing","Warning",JOptionPane.WARNING_MESSAGE);
        } 
     
     }
    
    
  }  catch(Exception ex){
     System.out.println(ex);
     JOptionPane.showConfirmDialog(null,"invalid no");


}
    return b;
    
    

}
    public boolean  addEmployeeValidate(){
          
boolean b=false;
   
 try{
      String sql="select SID from employee";
      rs=pst.executeQuery(sql);
    
       while(rs.next()){
    
          if(rs.getString("SID").equals(sid.getText())){
                b=true;
                JOptionPane.showMessageDialog(null,sid.getText()+" already enrolled update aviable");
              }}
    
     }catch(Exception ex){
      
           System.out.println("Error in addStaff table validate"+ex.getMessage());
            JOptionPane.showConfirmDialog(null,"already assigned");
          }  
    
      return b;
    }
    
    
     public boolean validfields(){
       //--------fname  
     
      boolean b=false;
      boolean b1,b2,b3,b4,b5,b6;
      
      b1=validateName(f_name.getText());
        
        if(b1==true){
        
           First_Name.setVisible(true);
           
        }
        
        else {
           First_Name.setVisible(false);
            JOptionPane.showConfirmDialog(null, "invalid name");
                    
         }
        
       b3= isValidEmailAddress(jTextField3.getText());
       if(b3=true)
       {
           jLabel27.setVisible(true);
       }
       else
       {
           jLabel27.setVisible(false); 
            JOptionPane.showConfirmDialog(null, "invalid email");
                    
       }
        b2=validateName(l_name.getText());
        
        if(b2==true){
        
          Last_Name.setVisible(true);
           
        }
        
        else {
           Last_Name.setVisible(false);
            JOptionPane.showConfirmDialog(null, "invalid lastname");
                    
           
         b5 =validateContact(M_no.getText());
        
         if(b5==true)
         {
             jLabel1.setVisible(true);
         }
         else
         {
             jLabel1.setVisible(false);
              JOptionPane.showConfirmDialog(rootPane, "invalid mobile no");
                    
         }
         
        }
        
       
        
       
        
        //--------Mobile
        
//         b4=validateContact(M_no.getText());
//        
//        if(b4==true){
//        
//           jLabel1.setVisible(true);
//           
//        }
//        
//        else {
//          jLabel1.setVisible(false);
//          
//           JOptionPane.showConfirmDialog(null, "invalid no");
//                    
//         }
        
        
            b1=validateName(f_name.getText());
        
        if(b1==true){
        
           First_Name.setVisible(true);
           
        }
        
        else {
           First_Name.setVisible(false);
            JOptionPane.showConfirmDialog(null, "invalid f_name");
                    
         }
           b6=validateSal(jTextField7.getText());
        
        if(b6==true){
        
           jLabel28.setVisible(true);
           
        }
        
        else {
           jLabel28.setVisible(false);
            JOptionPane.showConfirmDialog(null, "invalid salary");
                    
         }
        
        if(b1==true && b2 == true  && b3 == true && b6 == true){
        
            b=true;
         }
        
        else{
          
           b=false;
         }
        return b;
 }

    
//   public boolean check_all_Logical(){
//          
//   
//    
//   
//     if(checkMobile()==true && addEmployeeValidate()==false&&validfields()==true && check_emptyFields_sfaff()==true&&check_Bdateby_Joindate()==true)
//     {
//        return true;
//          
//    }
//    
//    else
//     {
//       return false;
//      }
//
//  } 
   public boolean check_Bdateby_Joindate(){

 boolean c=false;
  
   try{
  
         DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
           //String date1 = sdf.format(calendar.getTime()); 
         Calendar calendar = new GregorianCalendar();
          int day= (Calendar.YEAR);
           
         String d1=sdf.format(jDateChooser3.getDate());
         String d2=sdf.format(jDateChooser4.getDate());
                
         Date date1 = sdf.parse(d1);
         Date date2 = sdf.parse(d2);
                
                
        //Date1 dt1 = new Date1(date1);
        //Date dt2 = new DateTime(date2);

         
                
         if((date2.getYear()-date1.getYear())>18)
         {
                
                c=true;
                DOB.setVisible(false);
                jLabel3.setVisible(false);
                    
               }
                
                
           else
          {
                  c=false;
                  DOB.setVisible(true);
                  jLabel3.setVisible(true);
                    
                }
            }catch(Exception ex){
       
         System.out.println("Error in date checking"+ex.getMessage());
         JOptionPane.showConfirmDialog(null,"invalid date");
       
       }
   
  return c;  


}
   public static boolean isValidEmailAddress(String email) {
    boolean stricterFilter = true; 
    String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
    String emailRegex = stricterFilter ? stricterFilterString : laxString;
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
    java.util.regex.Matcher m = p.matcher(email);
    return m.matches();
}
    public boolean check_emptyFields_sfaff(){

    boolean b=false;

    
    
    
     if((sid.getText().equals("")) | (f_name.getText().equals("") )| l_name.getText().equals("") | address.getText().equals("") | (jDateChooser3.getDate()).equals("") |
           (jDateChooser4.getDate()).equals("") | jTextField3.getText().equals("") && M_no.getText().equals("") | jComboBox1.getSelectedItem().toString().equals("Select")|jbType.getSelectedItem().toString().equals("Select")|jbType.getSelectedItem().toString().equals("Select"))
           
//                             JOptionPane.showMessageDialog(null, "Please fillup all fields", "Alert", JOptionPane.INFORMATION_MESSAGE);
    
//    if(a1||a2||a3||a4||a5||a6||a7||date1==null || date2==null)
{
    
       b=false;
       JOptionPane.showMessageDialog(null,"No empty fields");
       
       
    }
    
    else{
          b=true;
    }
    
    return b;
 
 }
     public static boolean validateSal(String sal){
     boolean g= false;
     String sal1="^[0-9]*$";
     Pattern pattern=Pattern.compile(sal1);
     Matcher matcher=pattern.matcher(sal);
     if(matcher.matches())
         g=true;     
       else
         g=false;
           
     return g;
  }
    /* public void assign_class()
     {
 
      if(jTextField2.getText().isEmpty()){
     
        JOptionPane.showMessageDialog(null,"Please fill class");
       
      }
     
     else{
          
//        if(!jTextField2.getText().matches("^[a-z]+$")){
//        
//           lab_assignJob.setVisible(true);
//        }
        
      
//        else{ 
//            lab_assignJob.setVisible(false);
         
         try{
             String sql="select * from cla";
             rs=pst.executeQuery(sql);
               while(rs.next()){
               
                 String jCheck = rs.getString("SID");
                 String jchek  = rs.getString("class");
               } 
               String aa=jTextField4.getText();
               String abc =jTextField2.getText();
                if(aa.equals(rs.getString("SID"))&&abc.equals(rs.getString("class"))){
                 
                  JOptionPane.showMessageDialog(null,"Already assigned class");
                  
                }
                
                else{
                   pst=con.prepareStatement("insert into cla (class) values(?)");
                   pst.setString(1,jTextField2.getText());
                   int sucss_cl=pst.executeUpdate();
                   
                   if(sucss_cl>0){
                      JOptionPane.showMessageDialog(null,"Succesfully assigned class");
                      //fillCombolast();
                      jTextField2.setText("");
                    }
                   
                   else{
                      JOptionPane.showMessageDialog(null,"Not Successfully assighned class");
                   }
                   
                }
             
         }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
         }
        } 
     
 }/*
    
//public void CurrentDate(){
//    try
//    {
//     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                //get current date time with Date()
////                Date date = new Date();
////                pst.setString(10,dateFormat.format(date));
//
//                
//    }
//    catch(Exception ex)
//    {
//        System.out.println(ex);
//    }
//     //pst.setString(1,((JTextField)dob.getDateEditor().getUiComponent()).getText());
//     
//    /*Date currentDate = new Date();
//    int month=currentDate.getMonth();
//    int year=currentDate.getYear();
//    int day=currentDate.getDate();*/
////    dob.setText(java.time.LocalDate.now().toString());
////    j_date.setText(java.time.LocalDate.now().toString());
////    From.setText(java.time.LocalDate.now().toString());
////    To.setText(java.time.LocalDate.now().toString());
//    
//    
//}
     
     
public void currentdate()
     {
         jTextField9.setText(java.time.LocalDate.now().toString());
     }
     
     

public class tableloadS {
    


 
    



    public void assigndate()
     {
 
      if(jTextField8.getText().isEmpty()){
     
        JOptionPane.showMessageDialog(null,"Please fill class");
       
      }
     
     else{
          
//        if(!jTextField2.getText().matches("^[a-z]+$")){
//        
//           lab_assignJob.setVisible(true);
//        }
        
      
//        else{ 
//            lab_assignJob.setVisible(false);
         
         try{
             String sql="select * from attendance";
             rs=pst.executeQuery(sql);
               while(rs.next()){
               
                 String jCheck = rs.getString("SID");
               } 
         
                if(jTextField4.getText().equals(rs.getString("SID"))&&jTextField2.getText().equals(rs.getString("dat"))){
                 
                  JOptionPane.showMessageDialog(null,"Already assigned class");
                  
                }
                
                else{
                   pst=con.prepareStatement("insert into cla (class) values(?)");
                   pst.setString(1,jTextField2.getText());
                   int sucss_cl=pst.executeUpdate();
                   
                   if(sucss_cl>0){
                      JOptionPane.showMessageDialog(null,"Succesfully assigned class");
                      //fillCombolast();
                      jTextField2.setText("");
                    }
                   
                   else{
                      JOptionPane.showMessageDialog(null,"Not Successfully assighned class");
                   }
                   
                }
             
         }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error in  class type");
         }
        } 
     
 }

private void tableload16(String value)
{
   
    
        try{
            
            
            Statement st = con.createStatement();
             String sql = "SELECT SID,F_Name,subject FROM employee Where subject = '"+value+"';";
             
             /*
             st.getResultSet().getRow();
             con.close();
             */
             
             pst=con.prepareStatement(sql);
           rs=pst.executeQuery();
            
           abc.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e){
        } 
   
    
}


public class PendingDays extends javax.swing.JPanel{
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
   ResultSet rs2=null;
    
       
    public PendingDays() {
      initComponents();
      con = Dbconnection.connect();
    }


//    ResultSet find(String text) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

   
    
  

     public class Function{
    public ResultSet find(String s){
   
        try{
            //String x = "";
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lbms","root","");
        pst=con.prepareStatement("select *from lea where SID=?");
        pst.setString(1, s);
        rs=pst.executeQuery(); 
       // pst2=con.prepareStatement("select *from borrow_book where bookno=?");
        //pst2.setString(2,x);
       // rs2=pst.executeQuery(); 
        
         
        }
        catch(Exception e){
        System.out.print(e.getMessage());
        }
        return rs;
       
        
    }
     }
}







    









    }
public void tableload2()
{
   
    
        try{
            
            Statement st = con.createStatement();
String sql = ("SELECT SID,subject,F_Name FROM employee;");
st.getResultSet().getRow();
con.close();
            
           abc.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
        } 
    
    
}
//public void tableload1()
//{
//   
//    
//        try{
//            String sql1="Select SID,JobType,Reason,fm,T From lea ";
//            pst=con.prepareStatement(sql1);
//            rs=pst.executeQuery();
//            
//           staff.setModel(DbUtils.resultSetToTableModel(rs));
//           //leave.setModel(DbUtils.resultSetToTableModel(rs));
//        }
//        catch(Exception e){
//        } 
//    
//    
//}
   public void tableload()
    {
        try{
            String sql="Select*From employee ";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            
           staff.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
        }
    }

    private void getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
//   

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TopPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        LeftPanel = new javax.swing.JPanel();
        staffRegister = new javax.swing.JButton();
        Leave_Request = new javax.swing.JButton();
        ACPT = new javax.swing.JButton();
        Assign_Class = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        MiddlePannel = new javax.swing.JPanel();
        StaffRegister = new javax.swing.JPanel();
        SID = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        First_Name = new javax.swing.JLabel();
        f_name = new javax.swing.JTextField();
        Last_Name = new javax.swing.JLabel();
        l_name = new javax.swing.JTextField();
        Address = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        M_no = new javax.swing.JTextField();
        DOB = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        subject = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jbType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        staff = new javax.swing.JTable();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        search = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jTextField10 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        Request_Leave = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        sID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        S_Approval = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lea = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        AssignClass = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        as = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        abc = new javax.swing.JTable();
        Accept_Reject = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        leave = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        a = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        Approve = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        Home = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Attendance = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        attend = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TopPanel.setBackground(new java.awt.Color(0, 0, 102));

        jLabel14.setBackground(new java.awt.Color(153, 153, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 102));
        jLabel14.setText("SUSSEX COLLEGE");

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        LeftPanel.setBackground(new java.awt.Color(153, 153, 153));

        staffRegister.setBackground(new java.awt.Color(0, 0, 0));
        staffRegister.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        staffRegister.setForeground(new java.awt.Color(255, 255, 255));
        staffRegister.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_User_35px.png")); // NOI18N
        staffRegister.setText("Staff Register");
        staffRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffRegisterActionPerformed(evt);
            }
        });

        Leave_Request.setBackground(new java.awt.Color(0, 0, 0));
        Leave_Request.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Leave_Request.setForeground(new java.awt.Color(255, 255, 255));
        Leave_Request.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_Send_Mass_Email_35px.png")); // NOI18N
        Leave_Request.setText("Leave Request");
        Leave_Request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Leave_RequestActionPerformed(evt);
            }
        });

        ACPT.setBackground(new java.awt.Color(0, 0, 0));
        ACPT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ACPT.setForeground(new java.awt.Color(255, 255, 255));
        ACPT.setText("Accept or Reject");
        ACPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACPTActionPerformed(evt);
            }
        });

        Assign_Class.setBackground(new java.awt.Color(0, 0, 0));
        Assign_Class.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        Assign_Class.setForeground(new java.awt.Color(255, 255, 255));
        Assign_Class.setText("Assign Class");
        Assign_Class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_ClassActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 0, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/images/icons8_Home_35px.png"))); // NOI18N
        jButton3.setText("HOME");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/images/icons8_School_Director_96px_1.png"))); // NOI18N
        jLabel24.setText("jLabel24");

        jButton11.setBackground(new java.awt.Color(51, 51, 51));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Attendance");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staffRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
            .addComponent(Leave_Request, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ACPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Assign_Class, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LeftPanelLayout.setVerticalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staffRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Leave_Request, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ACPT, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Assign_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 309, Short.MAX_VALUE))
        );

        StaffRegister.setBackground(new java.awt.Color(219, 181, 110));

        SID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SID.setText("SID");

        sid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        First_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        First_Name.setText("First Name");

        f_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Last_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Last_Name.setText("Last Name");

        l_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Address.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Address.setText("Address");

        address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mobile No");

        M_no.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        M_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_noActionPerformed(evt);
            }
        });

        DOB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DOB.setText("DOB");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("JoinDate");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Subject");

        subject.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        subject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "Maths", "Science", "Drama", "Art", "Music", "Dance", "Chemistry", "Physics", "Combiend Maths", "Biology", "Social", "History", "Geography", "Civics", "Commerce", "ICT", "GIT", "English", " " }));
        subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Job Type");

        jbType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "Teacher", "NonAccademic" }));
        jbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTypeActionPerformed(evt);
            }
        });

        staff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                staffMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                staffMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(staff);

        add.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_Plus_35px.png")); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_Left_3_35px.png")); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_Trash_Can_35px.png")); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        search.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_Search_35px.png")); // NOI18N
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Gender");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Female", "Male" }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Email");

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("BasicSalary");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StaffRegisterLayout = new javax.swing.GroupLayout(StaffRegister);
        StaffRegister.setLayout(StaffRegisterLayout);
        StaffRegisterLayout.setHorizontalGroup(
            StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffRegisterLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Last_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(First_Name)
                    .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGap(93, 93, 93)
                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sid)
                    .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(f_name)
                        .addComponent(l_name)
                        .addComponent(address)
                        .addComponent(M_no)
                        .addComponent(jbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(subject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField7)))
                .addGap(79, 79, 79)
                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(update)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(547, Short.MAX_VALUE))
        );
        StaffRegisterLayout.setVerticalGroup(
            StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffRegisterLayout.createSequentialGroup()
                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SID, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(StaffRegisterLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(First_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(search)
                                            .addComponent(l_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(Last_Name)))
                                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(Address))
                                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffRegisterLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(add)
                                .addGap(3, 3, 3)))
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(M_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(StaffRegisterLayout.createSequentialGroup()
                                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56))
                            .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(StaffRegisterLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(StaffRegisterLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(update)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(delete))
                        .addGap(27, 27, 27)
                        .addComponent(jButton1)))
                .addGap(36, 36, 36)
                .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StaffRegisterLayout.createSequentialGroup()
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(StaffRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );

        Request_Leave.setBackground(new java.awt.Color(219, 181, 110));
        Request_Leave.setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("SID");

        sID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Reason");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        S_Approval.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        S_Approval.setText("Send Approval");
        S_Approval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_ApprovalActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("From");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("TO");

        jLabel21.setBackground(new java.awt.Color(153, 255, 255));

        lea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        lea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leaMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(lea);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton6.setText("Pending Days");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 204, 204));
        jLabel7.setLabelFor(jLabel7);
        jLabel7.setText("jLabel7");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton4.setText("View");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Request_LeaveLayout = new javax.swing.GroupLayout(Request_Leave);
        Request_Leave.setLayout(Request_LeaveLayout);
        Request_LeaveLayout.setHorizontalGroup(
            Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Request_LeaveLayout.createSequentialGroup()
                .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Request_LeaveLayout.createSequentialGroup()
                        .addGap(593, 593, 593)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Request_LeaveLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Request_LeaveLayout.createSequentialGroup()
                                .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(7, 7, 7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Request_LeaveLayout.createSequentialGroup()
                                .addComponent(S_Approval)
                                .addGap(57, 57, 57))
                            .addGroup(Request_LeaveLayout.createSequentialGroup()
                                .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sID)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(210, 210, 210)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Request_LeaveLayout.createSequentialGroup()
                .addGap(484, 484, 484)
                .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Request_LeaveLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Request_LeaveLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        Request_LeaveLayout.setVerticalGroup(
            Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Request_LeaveLayout.createSequentialGroup()
                .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Request_LeaveLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(S_Approval, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(Request_LeaveLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(Request_LeaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)))
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jLabel12)
                .addGap(242, 242, 242))
        );

        AssignClass.setBackground(new java.awt.Color(219, 181, 110));
        AssignClass.setPreferredSize(new java.awt.Dimension(1280, 768));

        jLabel15.setBackground(new java.awt.Color(153, 255, 51));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Teacher Name");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Subject");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\Desktop\\Project\\Admin\\src\\Interface\\images\\icons8_Save_35px.png")); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        as.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        as.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one subject", "Social", "History", "Geography", "Maths", "Science", "English", "Commerce", "Physics", "Chemistry", "Combined Maths", "Civics", "Art", "Dance", "Music", "Drama", "Biology" }));
        as.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("SID");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select class", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", " ", " " }));

        abc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        abc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abcMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                abcMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(abc);

        javax.swing.GroupLayout AssignClassLayout = new javax.swing.GroupLayout(AssignClass);
        AssignClass.setLayout(AssignClassLayout);
        AssignClassLayout.setHorizontalGroup(
            AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssignClassLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(as, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AssignClassLayout.createSequentialGroup()
                        .addGroup(AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(75, 75, 75)
                        .addGroup(AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))))
                .addContainerGap(657, Short.MAX_VALUE))
        );
        AssignClassLayout.setVerticalGroup(
            AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssignClassLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(as, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AssignClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton2)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        Accept_Reject.setBackground(new java.awt.Color(219, 181, 110));

        leave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        leave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaveMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leaveMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                leaveMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(leave);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("SID");

        a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Reason");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("From");

        Approve.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        Approve.setText("Approve");
        Approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApproveActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton10.setText("Decline");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 0));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("To");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Login");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Accept_RejectLayout = new javax.swing.GroupLayout(Accept_Reject);
        Accept_Reject.setLayout(Accept_RejectLayout);
        Accept_RejectLayout.setHorizontalGroup(
            Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Accept_RejectLayout.createSequentialGroup()
                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Accept_RejectLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Accept_RejectLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Accept_RejectLayout.createSequentialGroup()
                                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(Accept_RejectLayout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(240, 240, 240))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Accept_RejectLayout.createSequentialGroup()
                                        .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(49, 49, 49)
                                        .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(161, 161, 161)))
                                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Approve)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(Accept_RejectLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton7)))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        Accept_RejectLayout.setVerticalGroup(
            Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Accept_RejectLayout.createSequentialGroup()
                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Accept_RejectLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Accept_RejectLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))))
                .addGap(44, 44, 44)
                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Accept_RejectLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jButton10)
                        .addGap(25, 25, 25)
                        .addComponent(Approve))
                    .addGroup(Accept_RejectLayout.createSequentialGroup()
                        .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Accept_RejectLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Accept_RejectLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)))
                .addGap(50, 50, 50)
                .addGroup(Accept_RejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(411, Short.MAX_VALUE))
        );

        Home.setBackground(new java.awt.Color(219, 181, 110));
        Home.setPreferredSize(new java.awt.Dimension(2785, 885));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/images/Online-Business-Header.jpeg"))); // NOI18N
        jLabel25.setText("jLabel25");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 0, 51));
        jLabel22.setText("Welcome to Employee Mangagement");

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(569, Short.MAX_VALUE))
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGap(23, 23, 23)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(546, Short.MAX_VALUE))
        );

        Attendance.setBackground(new java.awt.Color(219, 181, 110));
        Attendance.setPreferredSize(new java.awt.Dimension(2785, 885));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("SID");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("DATE");

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton9.setText("Attendance");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton5.setText("IREPORT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        attend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        attend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                attendMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(attend);

        javax.swing.GroupLayout AttendanceLayout = new javax.swing.GroupLayout(Attendance);
        Attendance.setLayout(AttendanceLayout);
        AttendanceLayout.setHorizontalGroup(
            AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addGap(87, 87, 87)
                .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField8)
                        .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(89, 89, 89)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1921, Short.MAX_VALUE))
        );
        AttendanceLayout.setVerticalGroup(
            AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceLayout.createSequentialGroup()
                .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AttendanceLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(AttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                    .addGroup(AttendanceLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton5)
                .addContainerGap(524, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MiddlePannelLayout = new javax.swing.GroupLayout(MiddlePannel);
        MiddlePannel.setLayout(MiddlePannelLayout);
        MiddlePannelLayout.setHorizontalGroup(
            MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StaffRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Request_Leave, javax.swing.GroupLayout.DEFAULT_SIZE, 1452, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Accept_Reject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MiddlePannelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(AssignClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(160, Short.MAX_VALUE)))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, 1452, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Attendance, javax.swing.GroupLayout.DEFAULT_SIZE, 1452, Short.MAX_VALUE))
        );
        MiddlePannelLayout.setVerticalGroup(
            MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MiddlePannelLayout.createSequentialGroup()
                .addComponent(StaffRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 112, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Request_Leave, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Accept_Reject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MiddlePannelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(AssignClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(182, Short.MAX_VALUE)))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
            .addGroup(MiddlePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Attendance, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MiddlePannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(MiddlePannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void staffRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffRegisterActionPerformed

       
        
        StaffRegister.setVisible(true);
        Request_Leave.setVisible(false);
        Accept_Reject.setVisible(false);
        AssignClass.setVisible(false);
        Home.setVisible(false);
        Attendance.setVisible(false);
// TODO add your handlng code here:
    }//GEN-LAST:event_staffRegisterActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
       
       // TODO add your handling code here:
       if(this.validfields())
       try{ 
         if(checkMobile()==true && addEmployeeValidate()==false && check_emptyFields_sfaff()==true && check_Bdateby_Joindate()==true&&validfields())
         {
              int confirm = JOptionPane.showConfirmDialog(null,"Do you wish to Add details","Confirmation Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
         if (confirm == JOptionPane.YES_OPTION)
         {        
             String s = sid.getText();
        String fname = f_name.getText();
        String lname = l_name.getText();
        String add =  address.getText();
        String MobileNo = M_no.getText();
         SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
         String dob = formatter.format(jDateChooser3.getDate());
        
        String jdate =  formatter.format(jDateChooser4.getDate());
        String subj = subject.getSelectedItem().toString();
        String JType = jbType.getSelectedItem().toString();
        String gen = jComboBox1.getSelectedItem().toString();
        String em = jTextField3.getText();
        String bs = jTextField7.getText();

        
       String q = "INSERT INTO employee(SID,F_Name,L_Name,Address,MobileNo,DOB,JoinDate,subject,JobType,Gender,Email,BasicSalary) values('"+s+"','"+fname+"','"+lname+"','"+add+"','"+MobileNo+"','"+dob+"','"+jdate+"','"+subj+"','"+JType+"','"+gen+"','"+em+"','"+bs+"')";
         String q1 ="INSERT INTO teachers(SID,subject,JobType,Email,BasicSalary) values ('"+s+"','"+subj+"','"+JType+"','"+em+"','"+bs+"')";
         String q2 ="INSERT INTO nonaccademmic(SID,JobType,Email,BasicSalary) values ('"+s+"','"+JType+"','"+em+"','"+bs+"')";
         
         
          pst = con.prepareStatement(q);
        //pst = con.prepareStatement(q1);
         pst.execute();
         tableload();
          System.out.println("success");
         
         
         
         
         if(JType == "Teacher")
         {
             pst = con.prepareStatement(q1);
        //pst = con.prepareStatement(q1);
         pst.execute();
         System.out.println("success");
         
         }
         else if(JType == "NonAccademic")
         {
              pst = con.prepareStatement(q2);
        //pst = con.prepareStatement(q1);
         pst.execute();
         System.out.println("success");
         }
//       pst = con.prepareStatement(q);
//        //pst = con.prepareStatement(q1);
//         pst.execute();
//         tableload();
//          System.out.println("success");
         }
         }
         else
         {
             JOptionPane.showConfirmDialog(null,"invalid");
         }
         
         }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
          tableload();
       
//         if(jbType.getSelectedItem().toString().equals("Teacher"))
//         {
//             
//             //pst = con.prepareStatement(q1);
//        //pst = con.prepareStatement(q1);
//        // pst.execute();
//         }
         
        
    
      
      
    }//GEN-LAST:event_addActionPerformed

    private void Leave_RequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Leave_RequestActionPerformed
         StaffRegister.setVisible(false);
         Attendance.setVisible(false);
        Request_Leave.setVisible(true);
        Home.setVisible(false);
        Accept_Reject.setVisible(false);
        AssignClass.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_Leave_RequestActionPerformed

    private void ACPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACPTActionPerformed
       
        
       
         StaffRegister.setVisible(false);
         Attendance.setVisible(false);
        Request_Leave.setVisible(false);
        Home.setVisible(false);
        Accept_Reject.setVisible(true);
        AssignClass.setVisible(false); 
            leave.setEnabled(false);
    jLabel8.setEnabled(false);
    a.setEnabled(false);
    jLabel10.setEnabled(false);
    jTextArea2.setEnabled(false);
    jTextField1.setEnabled(false);
    jTextField6.setEnabled(false);
    jLabel11.setEnabled(false);
    jLabel23.setEnabled(false);
     jButton10.setEnabled(false);
    Approve.setEnabled(false);
             
// TODO add your handling code here:
          LeaveAccOrregTableLoad();
    }//GEN-LAST:event_ACPTActionPerformed

    private void Assign_ClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_ClassActionPerformed
              StaffRegister.setVisible(false);
        Request_Leave.setVisible(false);
        Accept_Reject.setVisible(false);
        AssignClass.setVisible(true);
        Home.setVisible(false);
        Attendance.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_Assign_ClassActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       int x=JOptionPane.showConfirmDialog(null,"Do you really want to update ");
        
        if(x==0)
        {
             
        String si = sid.getText();
        String fn = f_name.getText();
        String ln = l_name.getText();
        String addr =  address.getText();
        int MobileNo = Integer.parseInt(M_no.getText());//M_no.getText().;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String DOB = formatter.format(jDateChooser3.getDate());
        String jdate = formatter.format(jDateChooser4.getDate());
        String subje = subject.getSelectedItem().toString();
        String JType = jbType.getSelectedItem().toString();
        String gen = jComboBox1.getSelectedItem().toString();
        String em = jTextField3.getText();
        String basicSal = jTextField7.getText();

                                   
            
            String sql ="Update itp.employee SET F_Name ='"+fn+"',L_Name ='"+ln+"',Address ='"+addr+"',MobileNo ='"+MobileNo+"',DOB ='"+DOB+"',JoinDate='"+jdate+"',subject='"+subje+"',JobType='"+JType+"',Gender='"+gen+"',Email='"+em+"',BasicSalary='"+ basicSal+"' where SID = '"+si+"'";
            
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
              tableload();
            } catch (SQLException ex) {
                System.out.println(ex);
                  JOptionPane.showConfirmDialog(null,"update ");
//Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void staffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseClicked

    }//GEN-LAST:event_staffMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
int x=JOptionPane.showConfirmDialog(null,"Do you really want to Delete ");
        
        if(x==0)
        {
            String s =sid.getText();
          
            String sql = "DELETE FROM `employee` WHERE SID ='"+s+"'";
            
            try {
                pst= con.prepareStatement(sql);
                 pst.execute();
                 tableload();
                 
            } catch (SQLException ex) {
                System.out.println(ex);//Logger.getLogger(DeleteMember.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
               String qw= jTextField10.getText().toString();
       String q1="Select * From itp.employee  where SID LIKE '%"+qw+"%'";
       try
      {
           pst = con.prepareStatement(q1);
          rs = pst.executeQuery();
          staff.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(Exception e)
       {
           System.out.println(e.getMessage());
     //  }
    }        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                  sid.setText("");
                  f_name.setText("");
                  l_name.setText("");
                  address.setText("");
                  M_no.setText("");
                  jTextField7.setText("");
                   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                   Date date = new Date();
                  
                 // jDateChooser1.setDate("");
                   //jDateChooser2.setDate("");
                  
                  jComboBox1.getSelectedItem().toString().equals("select");
                  jTextField3.setText("");
                  subject.getSelectedItem().toString().equals("select");
                  jbType.getSelectedItem().toString().equals("select");
                  // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void leaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveMouseClicked
        
        //tableload1();
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
       StaffRegister.setVisible(false);
       Request_Leave.setVisible(false);
       Accept_Reject.setVisible(false);
       AssignClass.setVisible(false);  
       Home.setVisible(true);
       Attendance.setVisible(false);
    }//GEN-LAST:event_jButton3MouseClicked

    private void S_ApprovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_ApprovalActionPerformed
            if((sID.getText().equals(""))&&jTextArea1.getText().equals("") )
           
            {
                        // System.out.println("hello");
                                           JOptionPane.showMessageDialog(null, "Please fillup all fields", "Alert", JOptionPane.INFORMATION_MESSAGE);

            }
            else
            {
                
            }
       
       String s = sID.getText();
       
       String r = jTextArea1.getText();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
         String from = dateFormat.format(jDateChooser1.getDate());
       
       
       String c = dateFormat.format(jDateChooser2.getDate()); 
            

         try{ 
       String q = "INSERT INTO lea(SID,Reason,fm,T) values('"+s+"','"+r+"','"+from+"','"+c+"')";
         pst = con.prepareStatement(q);
         pst.execute();
        // LeaveAccOrregTableLoad1();
         
          System.out.println("success");
    }
         
            
         
    catch(Exception e)
    {
        System.out.println(e);
         JOptionPane.showMessageDialog(null, "Not exist");
    }
      LeaveAccOrregTableLoad1();
       
//            }
//            else
//            {
//                
//            }
    }//GEN-LAST:event_S_ApprovalActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
  String sql ="Update lea SET Opt='Decline' Where SID= '"+a.getText()+"'and fm='"+jTextField1.getText()+"'and T='"+jTextField6.getText()+"' and Reason='"+jTextArea2.getText()+"'";
            
            try {
                pst=con.prepareStatement(sql);
                pst.execute();
                
            } catch (SQLException ex) {
                System.out.println("hihi");
               
            }        
        
        
        LeaveAccOrregTableLoad();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void abcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abcMouseClicked

    }//GEN-LAST:event_abcMouseClicked

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String sid = jTextField4.getText();
        String name = jTextField5.getText();
        String sub = jTextField2.getText();
        String grade = jComboBox3.getSelectedItem().toString();
        
        
        if(jTextField4.getText().equals("")|jTextField5.getText().equals("")|jTextField2.getText().equals("")) 
{
    JOptionPane.showMessageDialog(null,"Please fill");
}//assign_class();
          //String q = "INSERT INTO class(SID,F_Name,class) values('"+jTextField4+"','"+jTextField5+"','"+jTextField2.getText()+")";
           String q = "INSERT INTO itp.class(SID,F_Name,subject,GID) values('"+sid+"','"+name+"','"+sub+"','"+grade+"')"; 
            try {
//                String q = "INSERT INTO itp.Cla(SID,F_Name,class) values('"+jTextField4.getText()+"','"+jTextField5.getText()+"','"+jTextField2.getText()+")";
                pst=con.prepareStatement(q);
                pst.execute();
               // tableload();
               //LeaveAccOrregTableLoad();
            } 
            catch (SQLException ex) {
                System.out.println(ex);
                
                JOptionPane.showMessageDialog(null, "already assigned");
                //Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
            }
       
             
     
            
            
            
               // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTypeActionPerformed
       if(jbType.getSelectedItem().toString().equals("Teacher"))

        {
            subject.show();   
        }
        else if(jbType.getSelectedItem().toString().equals("NonAccademic"))
        {
            subject.hide();
        }
        else
        {
           
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jbTypeActionPerformed

    private void asActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asActionPerformed

        System.out.println(as.getSelectedItem().toString());
        tableloadS a = new tableloadS();
            a.tableload16(as.getSelectedItem().toString());
        System.out.println("Hihi");
        System.out.println(as.getSelectedItem().toString());
        
        if(as.getSelectedItem().toString().equals(as.getSelectedItem().toString()))
         {
             
           
            System.out.println(as.getSelectedItem().toString());
             
         }
       
     
    }//GEN-LAST:event_asActionPerformed

    private void leaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveMousePressed
        // TODO add your handling code here:
        int r = leave.getSelectedRow();
    //TableModel model =leave.getModel();
    DefaultTableModel model1= (DefaultTableModel)leave.getModel();
        String s =  model1.getValueAt(r,1).toString();
        //String ab = model1.getValueAt(r,2).toString();
        String re = model1.getValueAt(r,2).toString();
        String fr = model1.getValueAt(r,3).toString();
        String to = model1.getValueAt(r,4).toString();
        
        System.out.println(s);

        a.setText(s);
      // jbType2.setSelectedItem(ab);
        jTextArea2.setText(re);
        jTextField1.setText(fr);
        jTextField6.setText(to);
        
    }//GEN-LAST:event_leaveMousePressed

    private void ApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveActionPerformed
 String sql ="Update lea SET Opt='Accept' Where SID= '"+a.getText()+"'and fm='"+jTextField1.getText()+"'";
            
            try {
                pst=con.prepareStatement(sql);
                pst.execute();
                LeaveAccOrregTableLoad();
            } catch (SQLException ex) {
                //Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
            }         // TODO add your handling code here:
        
        
        
        
        
        LeaveAccOrregTableLoad();
    }//GEN-LAST:event_ApproveActionPerformed

    private void subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_subjectActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 
        Calendar cal=new GregorianCalendar();
        int day=40;
        //int days = Days.daysBetween(date1, date2).getDays();
        
        //int day;
        //int month=cal.get(Calendar.MONTH);
        //String q1 = "INSERT INTO lea(pdays) values ('"+jLabel7.getText()+"')";
        
        try {
           
              
             String y="select  sum(T-fm) as 'PD' FROM lea WHERE SID=? and Opt='Accept'"; 
            pst=con.prepareStatement(y);
            String s =sID.getText();
            pst.setString(1, s);
            rs2=pst.executeQuery();
            if(rs2.next()){
                String q= rs2.getString("PD");
                int num = Integer.parseInt(q);
                int days=day-num;
               
                jLabel7.setText(Integer.toString(day-num));
                if( days>0 && days<40){
                   
         
          System.out.println("success");
                 JOptionPane.showMessageDialog(null, days+" Days More");
                }
                else
                    JOptionPane.showMessageDialog(null, "You have  "+(day-num)+" days!!");
             //String q= rs2.getString("day");
             //  int y = Integer.parseInt(q);
             //int days=day-y;
               String q1 = "INSERT INTO lea(pdays) values ('"+jLabel7.getText()+"')";
            }
        }
        
        catch(Exception e){
        System.out.print(e.getMessage());}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void abcMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abcMousePressed
         int r = abc.getSelectedRow();
        TableModel model =abc.getModel();
       
       String sid =  (String) abc.getValueAt(r,0).toString();
       String name = (String) abc.getValueAt(r,1).toString();
       String sub = (String) abc.getValueAt(r,2).toString();
       
                 jTextField4.setText(sid);
                  jTextField5.setText(name);
                  jTextField2.setText(sub);
                   // TODO add your handling code here:
    }//GEN-LAST:event_abcMousePressed

    private void leaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveMouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   String s = jTextField11.getText();
       String q1="Select * From lea where SID like '%"+s+"%'";
       try
      {
           pst = con.prepareStatement(q1);
          rs = pst.executeQuery();
          lea.setModel(DbUtils.resultSetToTableModel(rs));
            
       }catch(Exception e)
       {
           System.out.println(e.getMessage());
     //  }
    }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       StaffRegister.setVisible(false);
        Request_Leave.setVisible(false);
        Accept_Reject.setVisible(false);
        AssignClass.setVisible(false);
        Home.setVisible(false);
        Attendance.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void M_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_M_noActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
//String aq = jTextField8.getText();
if(jTextField8.getText().equals("")&&jTextField9.equals(""))
{
     JOptionPane.showMessageDialog(null,"Please fill ");
}
            
        currentdate();
        String aq = jTextField8.getText().toString();
        String ad = jTextField9.getText().toString();
        String sql1 = "insert into itp.attendances_s(SID,dat,attendance)"+ "values('"+aq+"','"+ad+"','IN')";
     
     try {
                pst=con.prepareStatement(sql1);
                pst.execute();
               
            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "already assigned");
                //Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
            } 
     attendTableLoad();
     
    }//GEN-LAST:event_jButton9ActionPerformed

    private void staffMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_staffMouseReleased

    
    
    
    private void staffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMousePressed
    int r = staff.getSelectedRow();
    
    DefaultTableModel model1= (DefaultTableModel)staff.getModel();
       
       
       String s =  (String) model1.getValueAt(r,0).toString();
       
       String fname =  (String) model1.getValueAt(r,1).toString();
     
       String lname = (String) model1.getValueAt(r,2).toString();
       
       String ad =   (String)model1.getValueAt(r,3).toString();
       
       String MN= (String)model1.getValueAt(r,4).toString();
       
       String subj = (String) model1.getValueAt(r,7).toString();
       
       String JType =  (String)model1.getValueAt(r,8).toString();
      
       String gender = (String) model1.getValueAt(r,9).toString();
       
       String email =(String)  model1.getValueAt(r,10).toString();
       
       String balance = (String) model1.getValueAt(r, 11).toString();
       
                sid.setText(s);
                  f_name.setText(fname);
                  l_name.setText(lname);
                  address.setText(ad);
                  M_no.setText(MN);
                  jbType.setSelectedItem(JType);
                  subject.setSelectedItem(subj);
                  jComboBox1.setSelectedItem(gender);
                  jTextField3.setText(email);
                  jTextField7.setText(balance);
           try
                 {
                      Date formatter = new SimpleDateFormat("YYYY-MM-dd").parse((String)staff.getValueAt(r, 5).toString());
                             jDateChooser3.setDate(formatter);
                           
                     Date forma= new SimpleDateFormat("YYYY-MM-dd").parse((String)staff.getValueAt(r, 6).toString());
                             jDateChooser4.setDate(forma);
                      
                 } 
           catch (ParseException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                  
     }      // TODO add your handling code here:
    }//GEN-LAST:event_staffMousePressed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void leaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaMousePressed
  int r = lea.getSelectedRow();
    
    DefaultTableModel model1= (DefaultTableModel)lea.getModel();
       
       
       String s =  (String) model1.getValueAt(r,1).toString();
       
       String reason =  (String) model1.getValueAt(r,2).toString();
     
                     
       sID.setText(s);
       jTextArea1.setText(reason);
       
      
       
            
           try
                 {
                      Date formatter = new SimpleDateFormat("YYYY-MM-dd").parse((String)lea.getValueAt(r, 3).toString());
                             jDateChooser1.setDate(formatter);
                           
                     Date forma= new SimpleDateFormat("YYYY-MM-dd").parse((String)lea.getValueAt(r, 4).toString());
                             jDateChooser2.setDate(forma);
                      
                 } 
           catch (ParseException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                  
     }         // TODO add your handling code here:
    }//GEN-LAST:event_leaMousePressed

    private void leaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_leaMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//    try{
//            String report = "C:\\Users\\DELL\\Desktop\\Project\\Admin";
//            JasperReport jr = JasperCompileManager.compileReport(report);
//            JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
//            JasperViewer.viewReport(jp);
//        }
//        catch(Exception e){}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void attendMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendMousePressed
     int r = attend.getSelectedRow();
    //TableModel model =leave.getModel();
    DefaultTableModel model1= (DefaultTableModel)attend.getModel();
        String s =  model1.getValueAt(r,0).toString();
        //String ab = model1.getValueAt(r,2).toString();
        String date = model1.getValueAt(r,1).toString();
       
     
     
       
               jTextField8.setText(s);
               jTextField9.setText(date);
              // TODO add your handling code here:
    }//GEN-LAST:event_attendMousePressed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
String text;
text = jTextField12.getText();
if(text.equals("123"))
{
    leave.setEnabled(true);
    jLabel8.setEnabled(true);
    a.setEditable(true);
    jLabel10.setEnabled(true);
    jTextArea2.setEnabled(true);
    jTextField1.setEnabled(true);
    jTextField6.setEnabled(true);
    jLabel11.setEnabled(true);
    jLabel23.setEnabled(true);
    jButton10.setEnabled(true);
    Approve.setEnabled(true);
}
else
{
        leave.setEnabled(false);
    jLabel8.setEnabled(false);
    a.setEnabled(false);
    jLabel10.setEnabled(false);
    jTextArea2.setEnabled(false);
    jTextField1.setEnabled(false);
    jTextField6.setEnabled(false);
    jLabel11.setEnabled(false);
    jLabel23.setEnabled(false);
     jButton10.setEnabled(false);
    Approve.setEnabled(false);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACPT;
    private javax.swing.JPanel Accept_Reject;
    private javax.swing.JLabel Address;
    private javax.swing.JButton Approve;
    private javax.swing.JPanel AssignClass;
    private javax.swing.JButton Assign_Class;
    private javax.swing.JPanel Attendance;
    private javax.swing.JLabel DOB;
    private javax.swing.JLabel First_Name;
    private javax.swing.JPanel Home;
    private javax.swing.JLabel Last_Name;
    private javax.swing.JButton Leave_Request;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JTextField M_no;
    private javax.swing.JPanel MiddlePannel;
    private javax.swing.JPanel Request_Leave;
    private javax.swing.JLabel SID;
    private javax.swing.JButton S_Approval;
    private javax.swing.JPanel StaffRegister;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JTextField a;
    private javax.swing.JTable abc;
    private javax.swing.JButton add;
    private javax.swing.JTextField address;
    private javax.swing.JComboBox<String> as;
    private javax.swing.JTable attend;
    private javax.swing.JButton delete;
    private javax.swing.JTextField f_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<String> jbType;
    private javax.swing.JTextField l_name;
    private javax.swing.JTable lea;
    private javax.swing.JTable leave;
    private javax.swing.JTextField sID;
    private javax.swing.JButton search;
    private javax.swing.JTextField sid;
    private javax.swing.JTable staff;
    private javax.swing.JButton staffRegister;
    private javax.swing.JComboBox<String> subject;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    

   
}
