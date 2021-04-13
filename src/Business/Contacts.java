/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Madbr
 */
public class Contacts {

    //properties
    private int contact_ID;
    private String contact_Name;
    private String email;
    
    private DBConnection dbCon = new DBConnection();
    private ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
    
    /**********************************************
     * @param contact_ID  the id associated with the contact.
     * @param contact_Name the name of the contact.
     * @param email the email of the contact.
    ************************************************/
    public Contacts(int contact_ID, String contact_Name, String email) {
        this.contact_ID = contact_ID;
        this.contact_Name = contact_Name;
        this.email = email;
    }
    
    /** 
     * Class constructor.
     */
     public Contacts() {
        this.contact_ID = 0;
        this.contact_Name = "";
        this.email = "";
    }

 
    /**
     * @return the contact_ID
     */
    public int getContact_ID() {
        return contact_ID;
    }

    /**
     * @param contact_ID the contact_ID to set
     */
    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    /**
     * @return the contact_Name
     */
    public String getContact_Name() {
        return contact_Name;
    }

    /**
     * @param contact_Name the contact_Name to set
     */
    public void setContact_Name(String contact_Name) {
        this.contact_Name = contact_Name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
     //display test method
    public void display(){
        System.out.println("Cust_ID: " + contact_ID);
        System.out.println("Cust_Name " + contact_Name);
        System.out.println("Address: " + email);
 
    }
    
    //sql methods
    /**********************************************
     * selects record from database and sets class
     *    properties.
     * @param id string representing the contact’s
     *    id.
    ************************************************/
    public void selectDB(int id){
         try{
            
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "select * from contacts WHERE Contact_ID = " + id + "";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            //process through the result set
            while(rs.next()){
            setContact_ID(rs.getInt(1));
            setContact_Name(rs.getString(2));
            setEmail(rs.getString(3));
            }
            //getAppointments();
           
            //Close Connection
            dbCon.closeCon(dbCon.getCon());
         }
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e)
      {
          System.out.println(e);
      }
    }  
    
     /**********************************************
     * retrieves all contact records from the database
     * @return the list of contacts
     ************************************************/
     public ObservableList<Contacts> getAllContacts(){
         
         try{
            Statement stmt = dbCon.startCon();
            String sql = sql = "select * from contacts";
            
            contactsList.clear();
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            //process through the result set
            while(rs.next()){
            Contacts contact = new Contacts();
            contact.setContact_ID(rs.getInt(1));
            contact.setContact_Name(rs.getString(2));
            contact.setEmail(rs.getString(3));
           
            contactsList.add(contact);
            }
            //Close Connection
            dbCon.closeCon(dbCon.getCon());
         }
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.println(e);
         }
          return contactsList; 
     }
}
