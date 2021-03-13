/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Madbr
 */
public class Appointments {
    
    private int appointment_ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime create_Date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_Updated_By;
    private int customer_ID;
    private int user_ID;
    private int contact_ID; 
    private DBConnection dbCon = new DBConnection();
    
    public Appointments(){
        this.appointment_ID = 0;
        this.title = "";
        this.description = "";
        this.location = "";
        this.type = "";
        this.start = null;
        this.end = null;
        this.create_Date = null;
        this.created_by = null;
        this.last_update = null;
        this.last_Updated_By = null;
        this.customer_ID = 0;
        this.user_ID = 0;
        this.contact_ID = 0;
    }

    public Appointments(int appointment_ID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID) {
        this.appointment_ID = appointment_ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
        this.customer_ID = customer_ID;
        this.user_ID = user_ID;
        this.contact_ID = contact_ID;
    }
    
    /**
     * @return the appointment_ID
     */
    public int getAppointment_ID() {
        return appointment_ID;
    }

    /**
     * @param appointment_ID the appointment_ID to set
     */
    public void setAppointment_ID(int appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
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
     * @return the user_ID
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * @param user_ID the user_ID to set
     */
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
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
    
    //display test method
    public void display(){
        System.out.println("appointment_ID: " + appointment_ID);
        System.out.println("title " + title);
        System.out.println("description: " + description);
        System.out.println("location: " + location);
        System.out.println("type: " + type);
        System.out.println("start: " + start);
        System.out.println("end " + end);
        System.out.println("create_Date: " + create_Date);
        System.out.println("created_by: " + created_by);
        System.out.println("last_update: " + last_update);
        System.out.println("last_Updated_By: " + last_Updated_By);
        System.out.println("customer_ID: " + customer_ID);
        System.out.println("user_ID: " + user_ID);
        System.out.println("contact_ID: " + contact_ID);
    }
   //sql methods
    /**********************************************
     * selects record from database and sets class
     *    properties.
     * @param id string representing the appointment
     *    id.
    ************************************************/
    public void selectDB(int id){
         try{
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "select * from appointments WHERE appointment_ID = 1";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            //process through the result set
            while(rs.next()){
            setAppointment_ID(rs.getInt(1));
            setTitle(rs.getString(2));
            setDescription(rs.getString(3));
            setLocation(rs.getString(4));
            setType(rs.getString(5));
            setStart(rs.getTimestamp(6).toLocalDateTime());
            setEnd(rs.getTimestamp(7).toLocalDateTime());
            setCreate_Date(rs.getTimestamp(8).toLocalDateTime());
            setCreated_by(rs.getString(9));
            setLast_update(rs.getTimestamp(10).toLocalDateTime());
            setLast_Updated_By(rs.getString(11));
            setCustomer_ID(rs.getInt(12));
            setUser_ID(rs.getInt(13));
            setContact_ID(rs.getInt(14));
            }
           
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
            sql = "UPDATE appointments set title='" + title + "'," +
                    "description='" + description + "'," +
                    "location='" + location + "'," +
                    "type='" + type + "'," +
                    "start='" + Timestamp.valueOf(start) + "'," +
                    "end='" + Timestamp.valueOf(end) + "'," +
                    "Create_Date='" + Timestamp.valueOf(create_Date) + "'," +
                    "Created_by='" + created_by + "'," +
                    "Last_update='" + Timestamp.valueOf(last_update) + "'," +
                    "Last_Updated_By='" + last_Updated_By + "'," +
                    "customer_ID=" + customer_ID + "," +
                    "user_ID=" + user_ID + "," +
                    "contact_ID=" + contact_ID + " " +
                    "WHERE appointment_ID=" + appointment_ID;
      
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
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location of the appointment.
     * @param type the type of the type.
     * @param start the start of the appointment.
     * @param end the end of the appointment.
     * @param create_Date the day the appointment record was created.
     * @param created_by the person who created the appointment record.
     * @param last_update the latest date the appointment record was updated.
     * @param last_Updated_By the person who last updated the appointment record.
     * @param customer_ID the customer_ID associated with the appointment.
     * @param user_ID the user_ID associated with the appointment.
     * @param contact_ID the contact_ID associated with the appointment.
    ************************************************/
    public void insertDB(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID){
         try{
           //create connection
            Statement stmt = dbCon.startCon();
           
            //executing statement
            String sql;
            sql = "insert into appointments (title, description, location, type, start, end, create_Date, created_by, last_update, last_Updated_By, customer_ID, user_ID, contact_ID)" 
                    + " values ('" + title + "','" +
                    description + "','" +
                    location + "','" +
                    type + "','" +
                    Timestamp.valueOf(start) + "','" +
                    Timestamp.valueOf(end) + "','" +
                    Timestamp.valueOf(create_Date) + "','" +
                    created_by + "','" +
                    Timestamp.valueOf(last_update) + "','" +
                    last_Updated_By + "'," +
                    customer_ID + "," + 
                    user_ID + "," + 
                    contact_ID + "" + 
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
    
     
    
     public static void main(String args[]){
       //Tester Code
        Appointments a4 = new Appointments();
         a4.selectDB(1);
         a4.setType("doop");
         a4.setLocation("poop");
        // a4.updateDB();
         a4.insertDB(a4.getTitle(), a4.getDescription(), a4.getLocation(), a4.getType(), a4.getStart(), a4.getStart(), a4.getCreate_Date(), a4.getCreated_by(), a4.getLast_update(), a4.getLast_Updated_By(), a4.getCustomer_ID(), a4.getUser_ID(), a4.getContact_ID());
         a4.display();
       // c4.deleteDB(4);
       //its Frank
       //c4.setFirstName("FrankK");
       //c4.updateDB();
    }
}
