/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 *
 * @author Madbr
 */
public class Users {
    
    private int user_ID;
    private String user_Name = "test";
    private static String password = "test";
    private LocalDateTime create_Date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_Updated_By;
    
    private DBConnection dbCon = new DBConnection();

     /**********************************************
     * @param user_ID the user id.
     * @param user_Name the name of the user.
     * @param create_Date the day the user record was created.
     * @param created_by the user who created the user record.
     * @param last_update the last day and time the user record was updated.
     * @param last_Updated_By the user who last updated the user record.
    ************************************************/
    public Users(int user_ID, String user_Name, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
    }
    
      
    /** 
     * Class constructor.
     */
     public Users() {
        this.user_ID = 0;
        this.user_Name = "";
        this.create_Date = null;
        this.created_by = "";
        this.last_update = null;
        this.last_Updated_By = "";
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
     * @return the user_Name
     */
    public String getUser_Name() {
        return user_Name;
    }

    /**
     * @param user_Name the user_Name to set
     */
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
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
    
    //SQL method
    /**********************************************
     * selects record from database and sets class
     *    properties.
     * @param id string representing the user
     *    id.
    ************************************************/
    public void selectDB(int id){
         try{
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "select * from users WHERE user_ID = " + id + "";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            //process through the result set
            while(rs.next()){
            setUser_ID(rs.getInt(1));
            setUser_Name(rs.getString(2));
            setCreate_Date(rs.getTimestamp(4).toLocalDateTime());
            setCreated_by(rs.getString(5));
            setLast_update(rs.getTimestamp(6).toLocalDateTime());
            setLast_Updated_By(rs.getString(7));
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
       
}
