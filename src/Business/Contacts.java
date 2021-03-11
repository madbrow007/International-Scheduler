/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Madbr
 */
public class Contacts {

    //properties
    private int contact_ID;
    private String contact_Name;
    private String email;
    
    //contructors
    public Contacts(int contact_ID, String contact_Name, String email) {
        this.contact_ID = contact_ID;
        this.contact_Name = contact_Name;
        this.email = email;
    }

    //getters and setters
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
}
