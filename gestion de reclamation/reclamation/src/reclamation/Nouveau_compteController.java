/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Nouveau_compteController implements Initializable {

   
   
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField specialite;
    @FXML
    private PasswordField num;
    @FXML
    private PasswordField mot_p;
    @FXML
    private Label error;
    @FXML
    public void Retour(ActionEvent event) throws IOException
    {
        Parent win5 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene S5 = new Scene(win5);
        
        Stage window5 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        window5.setScene(S5);
        window5.show();
    
    }
    

    
    @FXML
     public void Creation_nv(ActionEvent event) throws  SQLException
     {
        String errors= "";
         if ("".equals(nom.getText()))
             errors +="entrez votre nom";
         else if ("".equals(prenom.getText()))
                  errors +="entrez votre prenom";
         else if ("".equals(specialite.getText()))
                  errors +="entrez votre specialite";
         else if ("".equals(num.getText()) ) 
                  errors +="entrez votre numero";
          else   if ("".equals(mot_p.getText()))
                  errors +="entrez votre mot de passe ";
         
          else { 
          ConnectionClass CC=new ConnectionClass();
          Connection C= CC.getConnection();
         
         
         String sql ="INSERT INTO `etudiant` (`nom`, `prenom`, `specialite`, `matricule`, `pass`) VALUES ('"+nom.getText()+"', '"+prenom.getText()+"', '"+specialite.getText()+"', '"+num.getText()+"', '"+mot_p.getText()+"')";
         Statement statement= C.createStatement();
         statement.executeUpdate(sql);
          }
        
         error.setText(errors);
         
         
         
         
         
         
         
         
        
     }   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
