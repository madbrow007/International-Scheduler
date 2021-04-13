/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Madbr
 */
public class FirstLevelDivisions {

    //properties
    private int division_ID;
    private String division;
    private LocalDateTime create_Date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_Updated_By;
    private int country_ID;
    
    private DBConnection dbCon = new DBConnection();
    private ObservableList<FirstLevelDivisions> fldList = FXCollections.observableArrayList();

    /** 
     * Class constructor.
     */
    public FirstLevelDivisions(){
        this.division_ID = 0;
        this.division = "";
        this.create_Date = null;
        this.created_by = "";
        this.last_update = null;
        this.last_Updated_By = "";
        this.country_ID = 0;
    }
      
    /**********************************************
     * @param division_ID the division ID.
     * @param division the name of the division.
     * @param create_Date the day the division record was created.
     * @param created_by the user who created the division record.
     * @param last_update the last day and time the division record was updated.
     * @param last_Updated_By the user who last updated the division record.
     * @param country_ID the country id associated with the division of the customer.
    ************************************************/
    public FirstLevelDivisions(int division_ID, String division, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By, int country_ID) {
        this.division_ID = division_ID;
        this.division = division;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
        this.country_ID = country_ID;
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

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
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
     * @return the country_ID
     */
    public int getCountry_ID() {
        return country_ID;
    }

    /**
     * @param country_ID the country_ID to set
     */
    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    //sql methods
    /**********************************************
     * selects records from database and sets class
     *    properties.
     * @param id the division's country id
    ************************************************/
    public void selectDivisionList(int id){
         try{
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + id + "";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
       
            //process through the result set
            FirstLevelDivisions f;
            while(rs.next()){
            f = new FirstLevelDivisions();
            f.setDivision_ID(rs.getInt(1));
            f.setDivision(rs.getString(2));
            f.setCreate_Date(rs.getTimestamp(3).toLocalDateTime());
            f.setCreated_by(rs.getString(4));
            f.setLast_update(rs.getTimestamp(5).toLocalDateTime());
            f.setLast_Updated_By(rs.getString(6));
            f.setCountry_ID(rs.getInt(7));
        
            fldList.add(f);
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
      * connects to the database to retrieve divisions
      * @return the division list.
      ************************************************/
     public ObservableList<FirstLevelDivisions> getAllDivisions(){
         
         try{
            Statement stmt = dbCon.startCon();
            String sql = sql = "select * from first_level_divisions";
            
            fldList.clear();
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
         
            //process through the result set
            while(rs.next()){
            FirstLevelDivisions fld = new FirstLevelDivisions();
            fld.setDivision_ID(rs.getInt(1));
            fld.setDivision(rs.getString(2));
            fld.setCreate_Date(rs.getTimestamp(3).toLocalDateTime());
            fld.setCreated_by(rs.getString(4));
            fld.setLast_update(rs.getTimestamp(5).toLocalDateTime());
            fld.setLast_Updated_By(rs.getString(6));
            fld.setCountry_ID(rs.getInt(7));
          
            fldList.add(fld);
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
          return fldList; 
     }
    
     /**********************************************
     * @return the division list.
     ************************************************/
    public ObservableList<FirstLevelDivisions> getDivisionsForView(){
        return fldList;
    }

}