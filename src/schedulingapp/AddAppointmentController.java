/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import Business.Appointments;
import Business.Contacts;
import Business.ScheduleOverlapException;
import Business.Users;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class AddAppointmentController implements Initializable {

    @FXML
    private TextField appointment_ID_TB;
    @FXML
    private TextField location_TB;
    @FXML
    private TextField title_TB;
    @FXML
    private TextField desc_TB;
    @FXML
    private TextField type_TB;
    @FXML
    private TextField user_ID_TB;
    @FXML
    private TextField cust_ID_TB;
    @FXML
    private ComboBox<Contacts> contact_Combo;
    @FXML
    private Button add_Btn;
    @FXML
    private Button cancel_Btn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<LocalTime> start_Combo;
    @FXML
    private ComboBox<LocalTime> end_Combo;
    
    private ObservableList<LocalTime> timesLT = FXCollections.observableArrayList();
    
    private FilteredList<LocalTime> filteredData;
    
    private Alert scheduleAlert = new Alert(Alert.AlertType.ERROR);
    
    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
    
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm a");
    
    Appointments app = new Appointments();
    
    Contacts contact = new Contacts();
    

    
    
    
    
    @FXML
    public void handleButtonAction(ActionEvent ev) throws IOException
    {
        //button/radio button actions
           //cancel Button
           if(ev.getSource() == cancel_Btn){
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                    Parent parentName = fxmlLoader.load();
                    Scene sceneName = new Scene(parentName);
                 
                    Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    window.setScene(sceneName);
                    window.show();
            }
           
           if(ev.getSource() == add_Btn){
               //convert back iso format and to utc localdatetime
               try{
                LocalDate date = datePicker.getValue();
               String month; 
               String day;
               if (date.getMonthValue() <= 9){
                month = "0" + date.getMonthValue() + "";
               }
               else month = Integer.toString(date.getMonthValue());
               
               if (date.getDayOfMonth() <= 9){
                day = "0" + date.getDayOfMonth() + "";
               }
               
               else day = Integer.toString(date.getDayOfMonth());
             
               
               LocalDateTime startToISO = LocalDateTime.parse(month + "-" + day + 
                                                             "-" + date.getYear() + " " + app.convertToLocal(start_Combo.getValue()).format(tf), dTF);
               LocalDateTime endToISO = LocalDateTime.parse(month + "-" + day + 
                                                             "-" + date.getYear() + " " + app.convertToLocal(end_Combo.getValue()).format(tf), dTF);
               
               //checking for overlapped appointments
             
               //lambda used for simplicity 
               FilteredList<Appointments> filteredApps = new FilteredList<>(app.getAppsForView().filtered((apps) -> {
                   System.out.println(apps.getStart().toString());
                  return apps.convertToLocal(apps.getStart()).toLocalDate().isEqual(startToISO.toLocalDate());
                }));
                   
               app.checkForOverlaps(startToISO.toLocalTime(), endToISO.toLocalTime(), filteredApps);
               

               app.setStart(app.convertToUTC(LocalDateTime.parse(startToISO.format(DateTimeFormatter.ISO_DATE_TIME))));
               app.setEnd(app.convertToUTC(LocalDateTime.parse(endToISO.format(DateTimeFormatter.ISO_DATE_TIME))));
               
         
               app.setTitle(title_TB.getText());
               app.setDescription(desc_TB.getText());
               app.setLocation(location_TB.getText());
               app.setType(type_TB.getText());
               Users user = new Users();
               user.selectDB(app.getUser_ID());
               app.setLast_Updated_By(user.getUser_Name());
               app.setUser_ID(Integer.parseInt(user_ID_TB.getText()));
               app.setCustomer_ID(Integer.parseInt(cust_ID_TB.getText()));
               app.setContact_ID(contact_Combo.selectionModelProperty().get().getSelectedItem().getContact_ID());
               
               }catch (ScheduleOverlapException e){
                   scheduleAlert.setContentText(e.getMessage());
                   scheduleAlert.showAndWait();
               }
            
               app.insertDB(app.getTitle(), app.getDescription(), app.getLocation(), app.getType(), app.getStart(), app.getEnd(), app.getCreated_by(), app.getLast_Updated_By(), app.getCustomer_ID(), app.getUser_ID(), app.getContact_ID());
              
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
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
        for (int hours = 0; hours <= 23; hours++) {
            timesLT.add(LocalTime.of(hours, 0));
        }
        filteredData = new FilteredList<>(timesLT);
    }    
    
    
    
    public void setApp(ObservableList<Appointments> appList) {

        app.setAppointmentsList(appList);
        
        //listing of times
        //lambda used to efficiently set predicates for the filtered times list    
        filteredData.setPredicate((times) -> {
            LocalTime estTime = app.compareToEst(times);
            LocalTime eight = LocalTime.NOON.minusHours(4).minusMinutes(1);
            LocalTime ten = LocalTime.NOON.plusHours(10).plusMinutes(1);

            return estTime.isAfter(eight) && estTime.isBefore(ten);
        });

        start_Combo.setItems(filteredData);
        start_Combo.setCellFactory(cellFactoryTimes);
        start_Combo.setButtonCell(cellFactoryTimes.call(null));

        end_Combo.setItems(filteredData);
        end_Combo.setCellFactory(cellFactoryTimes);
        end_Combo.setButtonCell(cellFactoryTimes.call(null));

        //getting list of contacts
        contact_Combo.setItems(contact.getAllContacts());

        contact_Combo.setCellFactory(cellFactory);
        contact_Combo.setButtonCell(cellFactory.call(null));

        contact.selectDB(app.getContact_ID());
    }
       




   //setting custom display for combobox listcell and buttoncell items
   Callback<ListView<Contacts>, ListCell<Contacts>> cellFactory = new Callback<ListView<Contacts>, ListCell<Contacts>>(){
   
            @Override
            public ListCell<Contacts> call(ListView<Contacts> param) {
                return new ListCell<Contacts>(){
                
                    @Override
                    protected void updateItem(Contacts item, boolean empty){
                       super.updateItem(item, empty);
                       if (item == null || empty) {
                          
                          setGraphic(null);
                       }
                       else{
                           setText(item.getContact_Name());
                        }
                    }
                };
            }
   };

    Callback<ListView<LocalTime>, ListCell<LocalTime>> cellFactoryTimes = new Callback<ListView<LocalTime>, ListCell<LocalTime>>(){
   
            @Override
            public ListCell<LocalTime> call(ListView<LocalTime> param) {
                return new ListCell<LocalTime>(){
                
                    @Override
                    protected void updateItem(LocalTime item, boolean empty){
                       super.updateItem(item, empty);
                       if (item == null || empty) {
                          
                          setGraphic(null);
                       }
                       else{
                           setText(app.convertToLocal(item).format(tf));
                        }
                    }
                };
            }
   };
    
}
