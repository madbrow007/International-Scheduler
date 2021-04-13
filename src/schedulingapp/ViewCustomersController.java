/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import Business.Appointments;
import Business.Contacts;
import Business.Countries;
import Business.Customers;
import Business.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Madbr
 */
public class ViewCustomersController implements Initializable {

    @FXML
    private TableView<Customers> customer_Table;
    @FXML
    private TableColumn<Customers, Integer> customer_ID_Col;
    @FXML
    private TableColumn<Customers, String> name_Col;
    @FXML
    private TableColumn<Customers, String> address_Col;
    @FXML
    private TableColumn<Customers, String> postal_Code_Col;
    @FXML
    private TableColumn<Customers, String> phone_Col;
    @FXML
    private TableColumn<Customers, Integer> division_Col;
    @FXML
    private TableColumn<Customers, String> country_Col;
    @FXML
    private Button update_Customer_Btn;
    @FXML
    private Button delete_Customer_Btn;
    @FXML
    private Button add_Customer_Btn;
    @FXML
    private Button exit_Btn;

    private Customers customer = new Customers();
    
    private Appointments apps = new Appointments(); 
    
    private FirstLevelDivisions fld = new FirstLevelDivisions();
    
    private ObservableList<FirstLevelDivisions> fldList;
    
    private Countries country = new Countries();
    
    private ObservableList<Countries> countryList;
   
    private ObservableList<Customers> customersForView = customer.getCustomersForView();
    
    
    
    @FXML
     public void handleButtonAction(ActionEvent ev) throws IOException
    {
        //button/radio button actions
           //cancel Button
           if(ev.getSource() == exit_Btn){
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                 
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
           //takes user to add Customer page
           if( ev.getSource() == add_Customer_Btn){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                    
                    //setting selected customer to next screen to update
                    AddCustomerController AddCustomersScreen = fxmlLoader.getController();
                    AddCustomersScreen.setApp(fldList, countryList);
                    
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
           //deletes selected customer record and displays message
           if( ev.getSource() == delete_Customer_Btn){
               int custID = customer_Table.getSelectionModel().getSelectedItem().getCustomer_ID();
               String custName = customer_Table.getSelectionModel().getSelectedItem().getCustomer_Name();
               int appIndex = customer_Table.getSelectionModel().getSelectedIndex();
               
               customersForView.remove(appIndex);
               customer.deleteDB(custID);
               apps.deleteDBCust(custID);
               
               String alertMsg = "Customer: " + custName + " has been deleted from records";
    
               Alert delAlert = new Alert(Alert.AlertType.INFORMATION, alertMsg);
              
               delAlert.showAndWait();
               
               customer_Table.setItems(customersForView);
            }
           
           if( ev.getSource() == update_Customer_Btn){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCustomers.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                    
                    //setting selected customer to next screen to update
                    EditCustomersController EditCustomersScreen = fxmlLoader.getController();
                    EditCustomersScreen.setApp(customer_Table.getSelectionModel().getSelectedItem().getCustomer_ID(), fldList, countryList);
                 
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customer.getAllCustomers();
        fldList = fld.getAllDivisions();
        countryList = country.getAllCountries();
       
   
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        name_Col.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        address_Col.setCellValueFactory(new PropertyValueFactory<>("address"));
        postal_Code_Col.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
        phone_Col.setCellValueFactory(new PropertyValueFactory<>("phone"));
        division_Col.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("division_ID"));
        division_Col.setCellFactory(divisionCellFactory);
        country_Col.setCellValueFactory(new PropertyValueFactory<Customers, String>("division_ID"));
        country_Col.setCellFactory(countryCellFactory);

        customer_Table.setItems(customer.getCustomersForView());
     
    }    
    
     Callback<TableColumn<Customers,Integer>, TableCell<Customers,Integer>> divisionCellFactory = new Callback<TableColumn<Customers,Integer>, TableCell<Customers,Integer>>() {

            @Override
            public TableCell call(TableColumn param) 
            {
                return new TableCell<Customers, Integer>() 
                {
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);

                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {    
                          //lambda used to efficiently make use of the forEach method
                            fldList.forEach((i) -> {
                                if (item == i.getDivision_ID()){
                                    setText(i.getDivision());
                                }
                            });
                        }
                    }
                };
            }
     };
       
     Callback<TableColumn<Customers,String>, TableCell<Customers,String>> countryCellFactory = new Callback<TableColumn<Customers,String>, TableCell<Customers,String>>() {

            @Override
            public TableCell call(TableColumn param) 
            {
                return new TableCell<Customers, Integer>() 
                {
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);

                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {
                          int cID = 0;
                          
                            for (FirstLevelDivisions div :fldList){
                                if (item == div.getDivision_ID()){
                                    cID = div.getCountry_ID();
                                }
                            }
                           for (Countries c :countryList){
                                if (cID == c.getCountry_ID()){
                                     setText(c.getCountry());
                                }
                            }
                        }
                    }
                };
            }
     };
}
