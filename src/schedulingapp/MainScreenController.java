/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import Business.Appointments;
import Business.Contacts;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Madbr
 */

    
public class MainScreenController implements Initializable {
    
    //properties
    private Stage oldWindow;
    @FXML
    private TableView<Appointments> appointment_Table;
    @FXML
    private TableColumn<Appointments, Integer> appointment_ID_Col;
    @FXML
    private TableColumn<Appointments, String> title_Col;
    @FXML
    private TableColumn<Appointments, String> desc_Col;
    @FXML
    private TableColumn<Appointments, String> location_Col;
    @FXML
    private TableColumn<Appointments, Integer> contact_Col;
    @FXML
    private TableColumn<Appointments, String> type_Col;
    @FXML
    private TableColumn<Appointments, LocalDateTime> start_Col;
    @FXML
    private TableColumn<Appointments, LocalDateTime> end_Col;
    @FXML
    private TableColumn<Appointments, Integer> customer_ID_Col;
    @FXML
    private Button update_App_Btn;
    @FXML
    private Button delete_App_Btn;
    @FXML
    private Button edit_Cust_Btn;
    @FXML
    private Button add_App_Btn;
    @FXML
    private RadioButton week_RB;
    @FXML
    private RadioButton month_RB;
    @FXML
    private Button reports_Btn;
    @FXML
    private RadioButton all_RB;
    @FXML
    private Button exit_Btn;
    
    private Appointments apps = new Appointments(); 
    
    Contacts contact = new Contacts();
    
    private ObservableList<Contacts> contactsList;
            
    private FilteredList<Appointments> filteredData;
  
    private DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
    
    private ObservableList<Appointments> getAppsForView = apps.getAppsForView();
      
    

//Methods/actions
    @FXML
    public void handleButtonAction(ActionEvent ev) throws IOException
    {
        //button actions
           //Exit Button
           if(ev.getSource() == exit_Btn){
              System.exit(0);
            }
          
         //change view of appointments 
         //appointments in the current week
           if( ev.getSource() == week_RB){
               
               month_RB.setSelected(false);
               all_RB.setSelected(false);
               LocalDateTime endWeek = apps.currentTime.plusDays(7);
               
               //lambda used to efficiently set predicates for the filtered appointment list 
               filteredData.setPredicate((record) -> {
                    LocalDateTime recordDate = record.getStart();
                       
                    return recordDate.isAfter(apps.currentTime) && recordDate.isBefore(endWeek);
               });
              appointment_Table.setItems(filteredData);
               }
          
           //appointments in the current month
           if( ev.getSource() == month_RB){
               week_RB.setSelected(false);
               all_RB.setSelected(false);
               
              //lambda used to efficiently set predicates for the filtered appointment list    
               filteredData.setPredicate((record) -> {
                    LocalDateTime recordDate = record.getStart();
                       
                    return recordDate.getMonth() == apps.currentTime.getMonth();
               });
              appointment_Table.setItems(filteredData);
               }
            
           //All appointments
           if( ev.getSource() == all_RB){
               month_RB.setSelected(false);
               week_RB.setSelected(false);
               
               appointment_Table.setItems(apps.getAppsForView());
            }
           
           //takes user to edit appointment page
           if( ev.getSource() == edit_Cust_Btn){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewCustomers.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                 
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
           //takes user to add appointment page
           if( ev.getSource() == add_App_Btn){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAppointment.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                 
                     //setting selected appointment to next screen to update
                    AddAppointmentController AddAppointmentScreen = fxmlLoader.getController();
                    AddAppointmentScreen.setApp(apps.getAppsForView());
                    
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
           //deletes selected appointment record and displays deleted appoointment
           if( ev.getSource() == delete_App_Btn){
               int appID = appointment_Table.getSelectionModel().getSelectedItem().getAppointment_ID();
               String appType = appointment_Table.getSelectionModel().getSelectedItem().getType();
               int appIndex = appointment_Table.getSelectionModel().getSelectedIndex();
               
               getAppsForView.remove(appIndex);
               apps.deleteDB(appID);
               
               String alertMsg = appType + " Appointment with ID: " + appID + " has been cancelled";
    
               Alert delAlert = new Alert(Alert.AlertType.INFORMATION, alertMsg);
              
               delAlert.showAndWait();
               
               appointment_Table.setItems(getAppsForView);
            }
           if( ev.getSource() == update_App_Btn){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateAppointment.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                    
                    //setting selected appointment to next screen to update
                    UpdateAppointmentController UpdateAppointmentScreen = fxmlLoader.getController();
                    UpdateAppointmentScreen.setApp(appointment_Table.getSelectionModel().getSelectedItem().getAppointment_ID(), apps.getAppsForView());
                 
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
           if( ev.getSource() == reports_Btn){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewReports.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                    
                    /*
                    //setting selected appointment to next screen to update
                    UpdateAppointmentController UpdateAppointmentScreen = fxmlLoader.getController();
                    UpdateAppointmentScreen.setApp(appointment_Table.getSelectionModel().getSelectedItem().getAppointment_ID());
                    */
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
  
        apps.getAllAppointments();
        contactsList = contact.getAllContacts();
        filteredData = new FilteredList<>(getAppsForView);
        
        
        
        appointment_ID_Col.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_Col.setCellValueFactory(new PropertyValueFactory<>("description"));
        location_Col.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact_Col.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("contact_ID"));
        contact_Col.setCellFactory(contactCellFactory);
        type_Col.setCellValueFactory(new PropertyValueFactory<>("type"));
        start_Col.setCellValueFactory(new PropertyValueFactory<>("start"));
        start_Col.setCellFactory(timeCellFactory);
        end_Col.setCellValueFactory(new PropertyValueFactory<>("end"));
        end_Col.setCellFactory(timeCellFactory);
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        
        appointment_Table.setItems(apps.getAppsForView());
    }    
    
    //setting custom display for tableColumn items
     Callback<TableColumn<Appointments,LocalDateTime>, TableCell<Appointments,LocalDateTime>> timeCellFactory = new Callback<TableColumn<Appointments,LocalDateTime>, TableCell<Appointments,LocalDateTime>>() {

            @Override
            public TableCell call(TableColumn param) 
            {
                return new TableCell<Appointments, LocalDateTime>() 
                {
                    @Override
                    public void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);

                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {
                            setText(apps.convertToLocal(item).format(dTF));
                            setFont(Font.font (13));
                        }
                    }
                };
            }
     };

     Callback<TableColumn<Appointments,Integer>, TableCell<Appointments,Integer>> contactCellFactory = new Callback<TableColumn<Appointments,Integer>, TableCell<Appointments,Integer>>() {

            @Override
            public TableCell call(TableColumn param) 
            {
                return new TableCell<Appointments, Integer>() 
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
                            contactsList.forEach((i) -> {
                                if (item == i.getContact_ID()){
                                    setText(i.getContact_Name());
                                }
                            });
                        }
                    }
                };
            }
     };
    
    public void getOldWindow(Stage window){
         oldWindow = window;
     } 
    
    public void checkForAppointments(LocalDateTime login){
        LocalDateTime localLoginTime = login;
        boolean isApp = false;
        String incomingApp = "";
   
        
        for (Appointments currentApps : filteredData){
            LocalDateTime currentAppsToLocal = apps.convertToLocal(currentApps.getStart());
            
            if (currentAppsToLocal.isAfter(localLoginTime) && currentAppsToLocal.isBefore(localLoginTime.plusMinutes(15))){
               incomingApp = ("Appointment with ID: " + currentApps.getAppointment_ID() + " is coming up today, " + currentAppsToLocal.format(dTF)); 
               isApp = true;
               break;
            }
        }
        if (isApp == true){
            Alert incomingAppAlert = new Alert(Alert.AlertType.INFORMATION, incomingApp);
            incomingAppAlert.showAndWait(); 
        }
        else { 
            incomingApp = "No upcoming appointments within the next 15 minutes";
            Alert incomingAppAlert = new Alert(Alert.AlertType.INFORMATION, incomingApp);
            incomingAppAlert.showAndWait(); 
        }
    }
}
