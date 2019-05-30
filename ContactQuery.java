
package contact_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;


public class ContactQuery {
    
    public void insertContact(contact cont)
    {
           String dbname = "contacts";
        String DB_CONNECTION = "jdbc:mysql://localhost/";
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_CONNECTION+dbname, "root", "");
           String query=("INSERT INTO mycontact(fname, lname,groupc, phone, email,address,pic,userid) VALUES (?,?,?,?,?,?,?,?)");
            PreparedStatement ps=connection.prepareStatement(query);
         
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getCid());
            
             if(ps.executeUpdate()!=0)
            {
                JOptionPane.showMessageDialog(null, "New Contact Added");
               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Something Wrong");
               
            } 
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
           //Update Contact

    public void UpdateContact(contact cont,boolean withImage)
    {
         String dbname = "contacts";
        String DB_CONNECTION = "jdbc:mysql://localhost/";
       // String query="";
        if(withImage==true)//if the user want to update the contant profile picture to
        {
          String  query="UPDATE `mycontact` SET `fname`=?,`lname`=?,`groupc`=?,`phone`=?,`email`=?,`address`=?,`pic`=? WHERE `id` =?";
            // query=("UPDATE student_info SET fname=?,lname=?,groupc=?,phone-?,email=?,address=?,pic=? where id=+id");
            try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_CONNECTION+dbname, "root", "");
          
            PreparedStatement ps=connection.prepareStatement(query);
         
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getCid());
            
             if(ps.executeUpdate()!=0)
            {
                JOptionPane.showMessageDialog(null, "Contact Data Edited");
               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Something Wrong");
               
            } 
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        else//the user want to keep the same image[remove the image from the update]
         {    
        String query="UPDATE `mycontact` SET `fname`=?,`lname`=?,`groupc`=?,`phone`=?,`email`=?,`address`=? WHERE `id` =?";
        //query=("UPDATE student_info SET fname=?,lname=?,groupc=?,phone-?,email=?,address=? where id=+id");
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_CONNECTION+dbname, "root", "");
          
            PreparedStatement ps=connection.prepareStatement(query);
         
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            //ps.setBytes(7, cont.getPic());
            ps.setInt(7, cont.getCid());
            
             if(ps.executeUpdate()!=0)
            {
                JOptionPane.showMessageDialog(null, "Contact Data Edited");
               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Something Wrong");
               
            } 
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
       
    }     
        //Delete Content 
        public void DeleteContent()
        {
            
            String dbname = "contacts";
        String DB_CONNECTION = "jdbc:mysql://localhost/";
            try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_CONNECTION+dbname, "root", "");
            String query=("DELETE FROM `mycontact` WHERE `id` =?");
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,cid);
             if(ps.executeUpdate()!=0)
            {
                JOptionPane.showMessageDialog(null, "Contact Deleted");
               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Something Wrong");
               
            } 
            } catch (Exception e) {
            }
        }
    
    
    //Create a list of a Contact
    
    public ArrayList<contact> contactList(int userId)
    {
         ArrayList<contact> clist=new ArrayList<contact>();
        String dbname = "contacts";
       String DB_CONNECTION = "jdbc:mysql://localhost/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             Connection connection = DriverManager.getConnection(DB_CONNECTION+dbname, "root", "");
             String query=("SELECT id, fname, lname, groupc, phone, email, address, pic FROM mycontact where userid="+userId);
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query);
             contact ct;
             while(rs.next()){
                 ct=new contact(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("groupc"), rs.getString("phone"), rs.getString("email"), rs.getString("address"), rs.getBytes("pic"), userId);
                 clist.add(ct);
             }
        } catch (Exception e) {
            Logger.getLogger(ContactQuery.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        return clist;
    }
    
}
