/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.time.LocalDateTime;

/**
 *
 * @author Madbr
 */
public class Countries {

//properties
    private int country_ID;
    private String country;
    //DATETIME
    private LocalDateTime create_Date;
    private String created_by;
    //TIMESTAMP
    private LocalDateTime last_update;
    private String last_Updated_By;
    
    
    public Countries(int country_ID, String country, LocalDateTime create_Date, String created_by, LocalDateTime last_update, String last_Updated_By) {
        this.country_ID = country_ID;
        this.country = country;
        this.create_Date = create_Date;
        this.created_by = created_by;
        this.last_update = last_update;
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
    
    
    public static void main(String args[]){
        LocalDateTime rightnow = LocalDateTime.now();
        Countries c1 = new Countries(13,"america",rightnow,"madison",rightnow,"madison");
        
        System.out.println(c1.getCountry_ID() + " " + c1.getCountry() + " " + c1.getCreate_Date().toString() + " " + c1.getCreated_by() + " " + c1.getLast_update().toString() + " " + c1.getLast_Updated_By());
    }
    
}
