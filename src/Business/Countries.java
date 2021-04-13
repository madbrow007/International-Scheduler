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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Madbr
 */
public class Countries {

//properties
    private int country_ID;
    private String country;
    private LocalDateTime create_Date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_Updated_By;
    
    private DBConnection dbCon = new DBConnection();
    private ObservableList<Countries> countryList = FXCollections.observableArrayList();  
    
     /**********************************************
     * @param country_ID the country id.
     * @param country the name of the country.
     * @param create_Date the day the country record was created.
     * @param created_by the user who created the country record.
     * @param last_update the last day and time the country record was updated.
     * @param last_Updated_By the user who last updated the country record.
    ************************************************/
    public Countries(int country_ID, String country, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By) {
        this.country_ID = country_ID;
        this.country = country;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
    }
    
     /** 
     * Class constructor.
     */
    public Countries() {
        this.country_ID = 0;
        this.country = "";
        this.create_Date = null;
        this.created_by = null;
        this.last_update = null;
        this.last_Updated_By = null;
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

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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
     * @param cList the Country List to set without going to database
     */
    public void setCountryList (ObservableList<Countries> cList)
    {
        countryList = cList;
    }
    
     //sql methods
    /**********************************************
     * selects record from database and sets class
     *    properties.
     * @param id integer representing the country
     *    id.
    ************************************************/
    public void selectCountry(int id){
         try{
            Statement stmt = dbCon.startCon();
            
            //executing statement
            String sql;
            sql = "SELECT * FROM countries WHERE Country_ID = " + id + "";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
       
            //process through the result set
            while(rs.next()){
            setCountry_ID(rs.getInt(1));
            setCountry(rs.getString(2));
            setCreate_Date(rs.getTimestamp(3).toLocalDateTime());
            setCreated_by(rs.getString(4));
            setLast_update(rs.getTimestamp(5).toLocalDateTime());
            setLast_Updated_By(rs.getString(6));
        
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
      * connects to the database to retrieve countries
      * @return the country list.
    ************************************************/
    public ObservableList<Countries> getAllCountries(){
         
         try{
            Statement stmt = dbCon.startCon();
            String sql = sql = "select * from countries";
            
            countryList.clear();
            
            //executing statement
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
         
            //process through the result set
            while(rs.next()){
            Countries c = new Countries();
            c.setCountry_ID(rs.getInt(1));
            c.setCountry(rs.getString(2));
            c.setCreate_Date(rs.getTimestamp(3).toLocalDateTime());
            c.setCreated_by(rs.getString(4));
            c.setLast_update(rs.getTimestamp(5).toLocalDateTime());
            c.setLast_Updated_By(rs.getString(6));
        
            countryList.add(c);
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
          return countryList; 
     }
    
    /**********************************************
      * get country list
      * @return the country list.
    ************************************************/
    public ObservableList<Countries> getCountriesForView(){
        return countryList;
    }

}
