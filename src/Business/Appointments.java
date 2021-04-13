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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author Madbr
 */
public class Appointments {
    
    //Properties
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
    
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm a");
    
    private DBConnection dbCon = new DBConnection();
    
    private ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
    
    public final LocalDateTime currentTime = LocalDateTime.now();
    
    /** 
     * Class constructor.
     */
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

    /**********************************************
     * @param appointment_ID the appointment id.
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location for the appointment.
     * @param type the type of appointment.
     * @param start the start day and time of the appointment.
     * @param end the end day and time of the appointment.
     * @param create_Date the day the appointment was created.
     * @param created_by the user who created the appointment.
     * @param last_update the last day and time the appointment was updated.
     * @param last_Updated_By the user who last updated the appointment.
     * @param customer_ID the customer id associated with this appointment.
     * @param user_ID the user id associated with this appointment.
     * @param contact_ID  he contact id associated with this appointment.
    ************************************************/
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
    
    /**
     * @param appointmentsList the appointmentsList to set without going to database
     */
    public void setAppointmentsList(ObservableList<Appointments> appointmentsList) {
        this.appointmentsList = appointmentsList;
    }

     /**
     * @param utcDB the LocalDateTime object from the database set in UTC
     * @return LocalDateTime object converted to the user's local time zone
     */
    public LocalDateTime convertToLocal (LocalDateTime utcDB){
        
        ZonedDateTime zonedUTC = utcDB.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedUTCToLocal = zonedUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime after = zonedUTCToLocal.toLocalDateTime();
        
       return after;
    }
    
     /**
     * @param before the LocalTime object that's set in UTC
     * @return LocalTime object converted to the user's local time zone
     */
    public LocalTime convertToLocal (LocalTime before){
        
        LocalDateTime utcDB = before.atDate(LocalDate.now());
        ZonedDateTime zonedUTC = utcDB.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedUTCToLocal = zonedUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime zonedUTCToLocalDT = zonedUTCToLocal.toLocalDateTime();
        LocalTime after = zonedUTCToLocalDT.toLocalTime();
        
       return after;
    }
    
     /**
     * @param before the LocalTime object that's set in the users local time zone
     * @return LocalTime object converted to the company's EST time zone
     */
    public LocalTime compareToEst (LocalTime before){
        
        LocalDateTime utcDB = before.atDate(LocalDate.now());
        ZonedDateTime zonedUTC = utcDB.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedUTCToEst = zonedUTC.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime zonedEstToLocalDT = zonedUTCToEst.toLocalDateTime();
        LocalTime after = zonedEstToLocalDT.toLocalTime();
    
       return after;
    }
   
     /**
     * @param local the LocalDateTime object that's set in the users local time zone
     * @return LocalDateTime object converted to UTC time
     */
    public LocalDateTime convertToUTC (LocalDateTime local){
       
        ZonedDateTime zonedLocal = local.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime zonedLocalToUTC = zonedLocal.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime after = zonedLocalToUTC.toLocalDateTime();
        
       return after;
    }
    
     /**
     * @param newStart the start time of the appointment being added or updated
     * @param newEnd the end time of the appointment being added or updated
     * @param existingApps the list of appointments that already exist
     * @throws ScheduleOverlapException if schedule overlap or incorrect time set exception occurs 
     */
    public void checkForOverlaps (LocalTime newStart, LocalTime newEnd, FilteredList<Appointments> existingApps) throws ScheduleOverlapException{ 
     
        for (Appointments app : existingApps){
              
           
            LocalTime appStart = convertToLocal(app.getStart().toLocalTime());

            LocalTime appEnd = convertToLocal(app.getEnd().toLocalTime());

            System.out.println(newStart.toString());
            System.out.println(newEnd.toString());
            
            if (appStart.isBefore(newEnd) && newStart.isBefore(appEnd)){
               throw new ScheduleOverlapException("Scheduling error, Please Try Again! Date Must not be overlapped with another appointment!");
            }
            if (newStart.isAfter(newEnd) || newStart.equals(newEnd)){
                throw new ScheduleOverlapException("Scheduling error, End of appointment MUST be at least 1 hour after the start!");
            }
        }
    }   
    

   //sql methods
    /**********************************************
     * selects record from database and sets class
     *    properties.
     * @param id integer representing the appointment
     *    id.
    ************************************************/
    public void selectDB(int id){
         try{
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "select * from appointments WHERE appointment_ID = " + id + "";
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
     * @param id of the appointment
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
     * @param created_by the person who created the appointment record.
     * @param last_Updated_By the person who last updated the appointment record.
     * @param customer_ID the customer_ID associated with the appointment.
     * @param user_ID the user_ID associated with the appointment.
     * @param contact_ID the contact_ID associated with the appointment.
    ************************************************/
    public void insertDB(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String created_by, String last_Updated_By, int customer_ID, int user_ID, int contact_ID){
         try{
           //create connection
            Statement stmt = dbCon.startCon();
           
            //executing statement
            String sql;
            sql = "insert into appointments (title, description, location, type, start, end, created_by, last_Updated_By, customer_ID, user_ID, contact_ID)" 
                    + " values ('" + title + "','" +
                    description + "','" +
                    location + "','" +
                    type + "','" +
                    Timestamp.valueOf(start) + "','" +
                    Timestamp.valueOf(end) + "','" +
                    created_by + "','" +
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
    
    /**********************************************
     * deletes 1 record from database.
     * @param appointment_id the id of the appointment.
    ************************************************/
    public void deleteDB(int appointment_id){
        try{
            
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "DELETE FROM appointments WHERE appointment_id = " + "" + appointment_id + ";";
            System.out.println(sql);
           
            stmt.execute(sql);
           
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
     * deletes all records associated with customer_ID from database.
     * @param customer_ID the id of the customer.
    ************************************************/
    public void deleteDBCust(int customer_ID){
        try{
            
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "DELETE FROM appointments WHERE customer_ID = " + "" + customer_ID + ";";
            System.out.println(sql);
            stmt.execute(sql);  
           
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
     * @return the appointment list.
    ************************************************/
    public ObservableList<Appointments> getAppsForView(){
        return appointmentsList;
    }
    
    /**********************************************
      * connects to the database to retrieve appointments
      * @return the appointment list.
    ************************************************/
    public ObservableList<Appointments> getAllAppointments(){
         
         try{
            Statement stmt = dbCon.startCon();
            String sql = sql = "select * from appointments";
            
            appointmentsList.clear();
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            //process through the result set
            while(rs.next()){
            Appointments app = new Appointments();
            app.setAppointment_ID(rs.getInt(1));
            app.setTitle(rs.getString(2));
            app.setDescription(rs.getString(3));
            app.setLocation(rs.getString(4));
            app.setType(rs.getString(5));
            app.setStart(rs.getTimestamp(6).toLocalDateTime());
            app.setEnd(rs.getTimestamp(7).toLocalDateTime());
            app.setCreate_Date(rs.getTimestamp(8).toLocalDateTime());
            app.setCreated_by(rs.getString(9));
            app.setLast_update(rs.getTimestamp(10).toLocalDateTime());
            app.setLast_Updated_By(rs.getString(11));
            app.setCustomer_ID(rs.getInt(12));
            app.setUser_ID(rs.getInt(13));
            app.setContact_ID(rs.getInt(14));
           
            appointmentsList.add(app);
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
          return appointmentsList; 
     }
 
}


