/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import Business.Appointments;
import Business.Contacts;
import Business.Reports;
import Business.Users;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ViewReportsController implements Initializable {

    @FXML
    private TableView<Appointments> schedule_Table;
    @FXML
    private TableColumn<Appointments, Integer> appointment_ID_Col;
    @FXML
    private TableColumn<Appointments, String> title_Col;
    @FXML
    private TableColumn<Appointments, String> desc_Col;
    @FXML
    private TableColumn<Appointments, String> type_Col;
    @FXML
    private TableColumn<Appointments, LocalDateTime> start_Col;
    @FXML
    private TableColumn<Appointments, LocalDateTime> end_Col;
    @FXML
    private TableColumn<Appointments, Integer> customer_ID_Col;
    @FXML
    private TableView<Reports> type_Total_Table;
    @FXML
    private TableColumn<?, ?> total_Col;
    @FXML
    private TableView<Reports> country_Total_Table;
    @FXML
    private TableColumn<?, ?> country_Col;
    @FXML
    private TableColumn<?, ?> total2_Col;
    @FXML
    private RadioButton type_RB;
    @FXML
    private RadioButton month_RB;
    @FXML
    private Button cancel_Btn;
    @FXML
    private ComboBox<Contacts> contact_Combo;
    @FXML
    private TableColumn<Appointments, String> typeS_Col;
    @FXML
    private Button submit_Btn;
     
    
    private Appointments apps = new Appointments(); 
    
    private FilteredList<Appointments> filteredData;
  
    private DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
    
    private ObservableList<Appointments> getAppsForView = apps.getAppsForView();
    
    private Contacts contact = new Contacts();
    
    private ObservableList<Contacts> contactsList;
    
    private Reports reportReq = new Reports(); 
    
    private ObservableList<Reports> reqReportList =  FXCollections.observableArrayList();
    
    private Reports reportCust = new Reports(); 
    
    private ObservableList<Reports> reqCustList =  FXCollections.observableArrayList();
    
    
            
   
   

    
    
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
           
           //Contact combo box change
           if(ev.getSource() == submit_Btn){
                contact.selectDB(contact_Combo.selectionModelProperty().getValue().getSelectedItem().getContact_ID());
                 
               //workaround for buttoncell display issue not displaying contact name
                int index = 0;
                for (Contacts c : contact.getAllContacts()){
                    if (c.getContact_ID() == contact.getContact_ID()){
                        break;
                    }
                   index++;
                }
                contact_Combo.getSelectionModel().select(index);
                
                setFilteredApps(contact_Combo.selectionModelProperty().getValue().getSelectedItem().getContact_ID());
  
                schedule_Table.setItems(filteredData);
           }
           
           if(ev.getSource() == type_RB){
                reportReq.getTypeOrMonth("type");
                
                reqReportList = reportReq.getReportReq();
                
                month_RB.setSelected(false);
                type_RB.setSelected(true);
                type_Col.setCellValueFactory(new PropertyValueFactory<>("typeOrMonth"));
                total_Col.setCellValueFactory(new PropertyValueFactory<>("total"));
        
                type_Total_Table.setItems(reqReportList);
            }
           
           if(ev.getSource() == month_RB){
                reportReq.getTypeOrMonth("month");
                
                reqReportList = reportReq.getReportReq();
                
                month_RB.setSelected(true);
                type_RB.setSelected(false);
                type_Col.setCellValueFactory(new PropertyValueFactory<>("typeOrMonth"));
                total_Col.setCellValueFactory(new PropertyValueFactory<>("total"));
        
                type_Total_Table.setItems(reqReportList);
            }
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  //For Contact schedules
        apps.getAllAppointments();
        contactsList = contact.getAllContacts();
        filteredData = new FilteredList<>(getAppsForView);
        
        appointment_ID_Col.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_Col.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeS_Col.setCellValueFactory(new PropertyValueFactory<>("type"));
        start_Col.setCellValueFactory(new PropertyValueFactory<>("start"));
        start_Col.setCellFactory(timeCellFactory);
        end_Col.setCellValueFactory(new PropertyValueFactory<>("end"));
        end_Col.setCellFactory(timeCellFactory);
        customer_ID_Col.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        
        schedule_Table.setItems(filteredData);
        
        //getting list of contacts
        contact_Combo.setItems(contact.getAllContacts());
 
        contact_Combo.setCellFactory(cellFactory);
        contact_Combo.setButtonCell(cellFactory.call(null));
        
      //For setting up Month and type reports
        reportReq.getTypeOrMonth("type");
        reqReportList = reportReq.getReportReq();
        
        type_RB.setSelected(true);
        type_Col.setCellValueFactory(new PropertyValueFactory<>("typeOrMonth"));
        total_Col.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        type_Total_Table.setItems(reqReportList);
        
        //For setting up custom report
        reportCust.getNewCustomers();
        reqCustList = reportCust.getReportCustom();
   
        country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
        total2_Col.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        country_Total_Table.setItems(reqCustList);
    }    
    
    public void setFilteredApps(int cID){
         
     //lambda used to efficiently set predicates for the filtered appointment list    
        filteredData.setPredicate((app) -> {
            return app.getContact_ID() == cID;
        });
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
                             //lambda used for simplicity 
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
    
    
}
