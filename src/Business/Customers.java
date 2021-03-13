/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Madbr
 */
public class Customers {
 
    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private LocalDateTime create_Date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_Updated_By;
    private int division_ID;
    private DBConnection dbCon = new DBConnection();
    
     public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By, int division_ID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
        this.division_ID = division_ID;
    }
     
    
    public Customers() {
        this.customer_ID = 0;
        this.customer_Name = "";
        this.address = "";
        this.postal_Code = "";
        this.phone = "";
        this.create_Date = null;
        this.created_by = "";
        this.last_update = null;
        this.last_Updated_By = "";
        this.division_ID = 0;
    }
   
    /**
     * @return the customer_ID
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * @param customer_ID the customer_ID to set
     */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /**
     * @return the customer_Name
     */
    public String getCustomer_Name() {
        return customer_Name;
    }

    /**
     * @param customer_Name the customer_Name to set
     */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postal_Code
     */
    public String getPostal_Code() {
        return postal_Code;
    }

    /**
     * @param postal_Code the postal_Code to set
     */
    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the create_Date
     */
    public LocalDateTime getCreate_Date() {
        return create_Date;
    }

    /**
     * @param create_Date the create_Date to set
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        this.create_Date = create_Date;
    }

    /**
     * @return the created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the last_update
     */
    public LocalDateTime getLast_update() {
        return last_update;
    }

    /**
     * @param last_update the last_update to set
     */
    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    /**
     * @return the last_Updated_By
     */
    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    /**
     * @param last_Updated_By the last_Updated_By to set
     */
    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    /**
     * @return the division_ID
     */
    public int getDivision_ID() {
        return division_ID;
    }

    /**
     * @param division_ID the division_ID to set
     */
    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }
    
    //display test method
    public void display(){
        System.out.println("Cust_ID: " + customer_ID);
        System.out.println("Cust_Name " + customer_Name);
        System.out.println("Address: " + address);
        System.out.println("Postal_Code: " + postal_Code);
        System.out.println("Phone: " + phone);
        System.out.println("Create_Date: " + create_Date);
        System.out.println("Created_By " + created_by);
        System.out.println("Last_Update: " + last_update);
        System.out.println("Last_Updated_By: " + last_Updated_By);
        System.out.println("Division_ID: " + division_ID);
    }
    
    //sql methods
    /**********************************************
     * selects record from database and sets class
     *    properties.
     * @param id string representing the customers’s
     *    id.
    ************************************************/
    public void selectDB(int id){
         try{
            
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "select * from customers WHERE Customer_ID = 1";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            //process through the result set
            while(rs.next()){
            setCustomer_ID(rs.getInt(1));
            setCustomer_Name(rs.getString(2));
            setAddress(rs.getString(3));
            setPostal_Code(rs.getString(4));
            setPhone(rs.getString(5));
            setCreate_Date(rs.getTimestamp(6).toLocalDateTime());
            setCreated_by(rs.getString(7));
            setLast_update(rs.getTimestamp(8).toLocalDateTime());
            setLast_Updated_By(rs.getString(9));
            setDivision_ID(rs.getInt(10));
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
     * update database record according to current class
     *    property values.
    ************************************************/
    public void updateDB(){
         try{
            //create connection
            Statement stmt = dbCon.startCon();
          
            //executing statement
            String sql;
            sql = "UPDATE customers set Customer_Name='" + customer_Name + "'," +
                    "Address='" + address + "'," +
                    "Postal_Code='" + postal_Code + "'," +
                    "Phone='" + phone + "'," +
                    "Create_Date='" + Timestamp.valueOf(create_Date) + "'," +
                    "Created_by='" + created_by + "'," +
                    "Last_update='" + Timestamp.valueOf(last_update) + "'," +
                    "Last_Updated_By='" + last_Updated_By + "'" +
                    "WHERE Customer_ID=" + customer_ID + " AND Division_ID="  + division_ID;
       
           System.out.println("hello");
            
           stmt.executeUpdate(sql);
           
           //closing connection 
           dbCon.closeCon(dbCon.getCon());
         }
         
         catch(SQLException e){
             System.out.print(e);
         }
         catch(Exception e){
             System.out.print(e);
         }
    } 
     
     /**********************************************
     * adds new record to database.
     * @param customer_ID the id of the customer.
     * @param customer_Name the name of the customer.
     * @param address the address of the customer.
     * @param postal_Code the the postal code of the customer.
     * @param create_Date the day the customer record was created.
     * @param created_by the person who created the customer record.
     * @param last_update the latest date the customer record was updated.
     * @param last_Updated_By the person who last updated the customer record.
     * @param division_ID the division id associated with the customer.
    ************************************************/
    public void insertDB(String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By, int division_ID){
         try{
           //create connection
            Statement stmt = dbCon.startCon();
           
            //executing statement
            String sql;
            sql = "insert into customers (customer_Name, address, postal_Code, phone, create_Date, created_by, last_update, last_Updated_By, division_ID)" 
                    + " values ('" + customer_Name + "','" +
                    address + "','" +
                    postal_Code + "','" +
                    phone + "','" +
                    Timestamp.valueOf(create_Date) + "','" +
                    created_by + "','" +
                    Timestamp.valueOf(last_update) + "','" +
                    last_Updated_By + "'," +
                    division_ID + "" + 
                     ")";
                    
           System.out.println(sql);
           stmt.executeUpdate(sql);
         
           //closing connection 
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
    
    public void deleteDB(int customer_ID){
        try{
            
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "DELETE FROM customers WHERE Customer_ID = " + "" + customer_ID + ";";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
           
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
    
    
    public static void main(String args[]){
       //Tester Code
        Customers c4 = new Customers();
        //c4.selectDB(2);
        //c4.setCreated_by("Madison B");
        //c4.setAddress("333 fart way");
        //c4.insertDB(c4.getCustomer_Name(), c4.getAddress(), c4.getPostal_Code(), c4.getPhone(), c4.getCreate_Date(), c4.getCreated_by(), c4.getLast_update(), c4.getLast_Updated_By(), c4.getDivision_ID());
        //c4.display();
        c4.deleteDB(4);
       //its Frank
       //c4.setFirstName("FrankK");
       //c4.updateDB();
    }
    
}
