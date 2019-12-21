/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class EtudiantController implements Initializable {

    @FXML
    private Button djaghloul;
    @FXML
 public void change(ActionEvent event) throws IOException
    {
        Parent win6 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene S6 = new Scene(win6);
        
        Stage window6 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        window6.setScene(S6);
        window6.show();
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
    }    

    @FXML
    private void khebach(ActionEvent event) {
        djaghloul.setText("khi 7mar");
        
        
    }
    
}
