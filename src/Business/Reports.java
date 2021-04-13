/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Madbr
 */
public class Reports {

    //properties
     private String typeOrMonth;
     private String country;
     private int total;
     
     private DBConnection dbCon = new DBConnection();
     
     private ObservableList<Reports> reportReq = FXCollections.observableArrayList();
     
     private ObservableList<Reports> reportCustom = FXCollections.observableArrayList();
     
    /**********************************************
     * @param typeOrMonth type or month value
     * @param country the name of the country.
     * @param total of the report selected.
    ************************************************/
     public Reports(String typeOrMonth,String country, int total) {
        this.typeOrMonth = typeOrMonth;
        this.country = country;
        this.total = total;
    }
     
    /** 
     * Class constructor.
     */
     public Reports() {
        this.typeOrMonth = "";
        this.country = "";
        this.total = 0;
    }
     
     
    /**
     * @return the typeOrMonth
     */
    public String getTypeOrMonth() {
        return typeOrMonth;
    }

    /**
     * @param typeOrMonth the typeOrMonth to set
     */
    public void setTypeOrMonth(String typeOrMonth) {
        this.typeOrMonth = typeOrMonth;
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
     * @return the total
     */
    public int getTotal() {
        return total;
    }

     /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }
    
    /**********************************************
     * @return the required report list.
    ************************************************/
    public ObservableList<Reports> getReportReq(){
        return reportReq;
    }
    
    /**********************************************
     * @return the custom report list.
    ************************************************/
    public ObservableList<Reports> getReportCustom(){
        return reportCustom;
    }
   
    /**********************************************
      * connects to the database to retrieve reports
      * @param tORm the kind of report to retrieve
    ************************************************/
    public void getTypeOrMonth(String tORm){
         try{
            Statement stmt = dbCon.startCon();
            
             //executing statement
             String sql = "";
             if (tORm.equals("type")){
                sql = "SELECT type, Count(Appointment_ID) FROM appointments GROUP BY type";
             }
             else if (tORm.equals("month")){
                sql = "SELECT MONTHNAME(start) AS Month, Count(Appointment_ID) FROM appointments GROUP BY Month;";
             }
          
            reportReq.clear();
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
          
            //process through the result set
            while(rs.next()){
                Reports r = new Reports();
                r.setTypeOrMonth(rs.getString(1));
                r.setTotal(rs.getInt(2));
                reportReq.add(r);
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
      * connects to the database to retrieve new customers for report
    ************************************************/   
    public void getNewCustomers(){
         try{
            Statement stmt = dbCon.startCon();
            
             //executing statement
             String sql = "SELECT WJ07tET.countries.Country, Count(Customer_ID) " +
                          "FROM WJ07tET.customers " +
                          "INNER JOIN WJ07tET.first_level_divisions ON WJ07tET.customers.Division_ID = WJ07tET.first_level_divisions.Division_ID " +
                          "INNER JOIN WJ07tET.countries ON WJ07tET.first_level_divisions.COUNTRY_ID = WJ07tET.countries.Country_ID " +
                          "WHERE YEAR(customers.Create_Date) = YEAR(current_date()) and " +
                          "MONTH(customers.Create_Date) = MONTH(current_date()) " +
                          "GROUP BY Country";
             
            reportCustom.clear();
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
          
            //process through the result set
            while(rs.next()){
                Reports r = new Reports();
                r.setCountry(rs.getString(1));
                r.setTotal(rs.getInt(2));
                reportCustom.add(r);
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
