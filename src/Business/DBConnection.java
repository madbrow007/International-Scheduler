 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Madbr
 */
public class DBConnection {
 
    
    private Statement stmt = null;
    private Connection con = null;
    
    //connection getter and setter
    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

   //start connection 
    public Statement startCon(){
        try{
            //connection name
            Class.forName("com.mysql.jdbc.Driver");
            Connection con
                    = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/WJ07tET", "U07tET", "53689128112");

            //create statement
             stmt = con.createStatement();

        } catch (SQLException b)  {System.out.print(b);}
          catch (Exception e) {System.out.println(e);}
        return stmt; 
    }
    
    //close connection
    public void closeCon(Connection con){
        try{
        con.close();
        }catch (SQLException e) {System.out.println(e);}
    }
}
