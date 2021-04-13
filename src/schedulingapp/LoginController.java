/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madbr
 */
public class LoginController implements Initializable {

    @FXML
    private Text loginLbl;
    @FXML
    private Text userLbl;
    @FXML
    private Text passLbl;
    @FXML
    private TextField userTF;
    @FXML
    private TextField passTF;
    @FXML
    private Button submitBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Label zoneLbl;
    
    private String alertMsg = "Wrong Username/Password Combination";
    
    private Alert passAlert = new Alert(Alert.AlertType.ERROR, alertMsg);
    
    private ResourceBundle rbLang;
    
    private ZoneId zod;
    @FXML
    private Label zoneNameLbl;
    

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void handleButtonAction(ActionEvent ev) throws IOException
    {
        //Clear Button
           if( ev.getSource() == clearBtn){
                userTF.clear();
                passTF.clear();
            }
          
         //submit button  
        //change to switch screen not add screen
           if (ev.getSource() == submitBtn) {
            try {
               File file = new File("login_activity.txt");
               FileWriter logFile = new FileWriter(file, true);
               BufferedWriter logFileBW = new BufferedWriter(logFile); 
               
      
                   if (userTF.getText().equals("test") & passTF.getText().equals("test")) {

                       logFileBW.newLine();
                       logFileBW.write("Login Attempt: " + LocalDateTime.now().toString() + " Was Succesful");

                       logFileBW.close();

                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                       Parent parentName = fxmlLoader.load();
                       Scene sceneName = new Scene(parentName);

                       MainScreenController MainScreen = fxmlLoader.getController();
                       MainScreen.checkForAppointments(LocalDateTime.now());

                       Stage window = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                       window.setScene(sceneName);
                       window.show();
                   }
                   else {
                       logFileBW.newLine();
                       logFileBW.write("Login Attempt: " + LocalDateTime.now().toString() + " Failed");

                       logFileBW.close();
                      passAlert.showAndWait();
                    }
               
              } catch (IOException e) {
                    System.out.println(e);
                }
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //creates language translation
        try{
        
        //default system zoneId and language setting
        zod = ZoneId.systemDefault();
        zoneNameLbl.setText(zod.getId());
        rbLang = ResourceBundle.getBundle("schedulingapp/Lang", Locale.getDefault());
   
        if (rbLang.getLocale().getLanguage().equals("fr")){
            loginLbl.setText(rbLang.getString("login"));
            userLbl.setText(rbLang.getString("username"));
            passLbl.setText(rbLang.getString("password"));
            submitBtn.setText(rbLang.getString("submit"));
            clearBtn.setText(rbLang.getString("clear"));
            zoneLbl.setText(rbLang.getString("location"));
            zoneNameLbl.setText(rbLang.getString(zod.getId()));
            passAlert.setTitle(rbLang.getString("errorTitle"));
            passAlert.setHeaderText(rbLang.getString("passwordError"));
            passAlert.setContentText(rbLang.getString("passwordError"));
          }
        }
        
        //create error messaage
        catch(Exception e){
            if (!((Locale.getDefault().getLanguage()).equals("en")))
                    System.out.println("Lanaguage not supported");;
        }
    }       
}
