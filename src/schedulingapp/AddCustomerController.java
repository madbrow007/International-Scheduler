/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import Business.Countries;
import Business.Customers;
import Business.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Madbr
 */
public class AddCustomerController implements Initializable {

    @FXML
    private TextField customer_ID_TB;
    @FXML
    private TextField postal_Code_TB;
    @FXML
    private TextField name_TB;
    @FXML
    private TextField address_TB;
    @FXML
    private TextField phone_TB;
    @FXML
    private ComboBox<Countries> country_Combo;
    @FXML
    private Button add_Btn;
    @FXML
    private Button cancel_Btn;
    @FXML
    private ComboBox<FirstLevelDivisions> state_Combo;
    
    private FilteredList<FirstLevelDivisions> filteredDivsions;
    
    private Customers customer = new Customers();
    
    private FirstLevelDivisions fld = new FirstLevelDivisions();
    
    private ObservableList<FirstLevelDivisions> fldList;
    
    private Countries country = new Countries();


    
    
    
    @FXML
     public void handleButtonAction(ActionEvent ev) throws IOException
    {
        //button/radio button actions
           //cancel Button
           if(ev.getSource() == cancel_Btn){
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewCustomers.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                 
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
            if (ev.getSource() == country_Combo) {
            setFilteredDivisions(country_Combo.selectionModelProperty().getValue().getSelectedItem().getCountry_ID());
  
            state_Combo.setItems(filteredDivsions);
            state_Combo.setCellFactory(divisionCellFactory);
            state_Combo.setButtonCell(divisionCellFactory.call(null));
            }
            
            if(ev.getSource() == add_Btn){
    
                 customer.setCustomer_Name(name_TB.getText());
                 customer.setAddress(address_TB.getText());
                 customer.setPhone(phone_TB.getText());
                 customer.setPostal_Code(postal_Code_TB.getText());
                 customer.setDivision_ID(state_Combo.getValue().getDivision_ID());
                
                
                customer.insertDB(customer.getCustomer_Name(), customer.getAddress(), customer.getPostal_Code(), customer.getPhone(), "user", "user", customer.getDivision_ID());
                
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewCustomers.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                 
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
    }    
    
       //populate textfields from previously selected appointment
    public void setApp(ObservableList<FirstLevelDivisions> divList, ObservableList<Countries> cList){
  
        
        country.setCountryList(cList);
        filteredDivsions = new FilteredList<>(divList);

        //workaround for buttoncell display issue not displaying names
        for (FirstLevelDivisions f : filteredDivsions){
            if (f.getDivision_ID() == customer.getDivision_ID()){
                fld = f;
                break;
            }
        }

        for (Countries c : cList){
            if (c.getCountry_ID() == fld.getCountry_ID()){
                country = c;
                break;
            }
        }
        
        setFilteredDivisions(country.getCountry_ID());
        
        country_Combo.setItems(cList);
        country_Combo.setCellFactory(countryCellFactory);
        country_Combo.setButtonCell(countryCellFactory.call(null));
        
        state_Combo.setItems(filteredDivsions);
        state_Combo.setCellFactory(divisionCellFactory);
        state_Combo.setButtonCell(divisionCellFactory.call(null));
    }
    
    public void setFilteredDivisions(int cID){
        
        //lambda used to efficiently set predicates for the filtered divison list 
        filteredDivsions.setPredicate((div) -> {
           int cIDList = div.getCountry_ID();
            return cIDList == cID;
        });
    
    }    
     
     Callback<ListView<FirstLevelDivisions>, ListCell<FirstLevelDivisions>> divisionCellFactory = new Callback<ListView<FirstLevelDivisions>, ListCell<FirstLevelDivisions>>(){
   
            @Override
            public ListCell<FirstLevelDivisions> call(ListView<FirstLevelDivisions> param) {
                return new ListCell<FirstLevelDivisions>(){
                
                    @Override
                    protected void updateItem(FirstLevelDivisions item, boolean empty){
                       super.updateItem(item, empty);
                       if (item == null || empty) {
                          
                          setGraphic(null);
                       }
                       else{
                           setText(item.getDivision());
                        }
                    }
                };
            }
   };
     
     
     Callback<ListView<Countries>, ListCell<Countries>> countryCellFactory = new Callback<ListView<Countries>, ListCell<Countries>>(){
   
            @Override
            public ListCell<Countries> call(ListView<Countries> param) {
                return new ListCell<Countries>(){
                
                    @Override
                    protected void updateItem(Countries item, boolean empty){
                       super.updateItem(item, empty);
                       if (item == null || empty) {
                          
                          setGraphic(null);
                       }
                       else{
                           setText(item.getCountry());
                        }
                    }
                };
            }
   };
    
}
