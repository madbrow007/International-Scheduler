/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapp;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    /**
     * Initializes the controller class.
     */
    
    public void handleButtonAction(ActionEvent ev) throws IOException
    {
        //Exit Button
         //  if( ev.getSource() == eBtn){
         //     System.exit(0);
          //  }
          
         //submit button  
         //  if( ev.getSource() == proModBtn){

          //     changeScene("ModifyProductScreen", ev);
          //  }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResourceBundle rbLang = ResourceBundle.getBundle("schedulingapp/Lang",Locale.getDefault());
        
        if (rbLang.getLocale().getLanguage().equals("fr")){
           
            System.out.println(rbLang.getString("submit"));
        }
        
        else System.out.println("submit");
       
    }    
    
}
