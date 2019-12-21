/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Arrays.equals;
import static java.util.Arrays.equals;
import static java.util.Arrays.equals;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

/**
 *
 * @author amine
 */
public class FXMLDocumentController implements Initializable {

    Connection conx=null;
    PreparedStatement prepared=null;
    ResultSet resultat=null;
    
    
    
    @FXML
    private ChoiceBox ch;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorLabel;
    
    @FXML
        public void Creat(ActionEvent event) throws IOException
    {
        Parent win4 = FXMLLoader.load(getClass().getResource("nouveau_compte.fxml"));
        Scene S4 = new Scene(win4);
        
        Stage window4 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        window4.setScene(S4);
        window4.show();
    
    }
    
            
            
        
        
        
        
        
        
    @FXML
               public void Connexion(ActionEvent event) throws IOException, SQLException
    {       // connecxion a la base
            ConnectionClass CC=new ConnectionClass();
            conx= CC.getConnection();
        
            String userName= username.getText();
            String pass= password.getText();
            
            if (userName == "" )
                errorLabel.setText("entrez votre nom");
            else if (pass == "")
                errorLabel.setText("entrez votre mot de passe");
            else { // si l'utilisateur a saisi ses infos
            String sql="SELECT nom, pass FROM ";
            String xmlFile = "";
            
            int selected = 0 ; // on dirait une variable booleen pour confirmer que la selection d'utilisateur a été bien selectionné 
            // l'utilisateur doit selectionner son type 
            if ("Adminstrateur".equals(ch.getValue().toString())) {

                xmlFile = "Adminstrateur.fxml";// le fichier xml d'un admin sera selectionné 
                sql +="admins"; // la table d'admins 
                selected = 1 ;// indique que le type d'utilisateur a été bien séléctionné 
                
            } else if ("Etudiant".equals(ch.getValue().toString())) {
                
                xmlFile = "etudiant.fxml" ;// le fichier xml d'un etudiant sera selectionné
                sql +="etudiant";// la table d'etudiant 
                selected = 1 ; // indique que le type d'utilisateur a été bien séléctionné 
            }else if ("Enseignant".equals(ch.getValue().toString())) {
                
                xmlFile = "Enseignant.fxml" ;// le fichier xml d'un ensiegnant sera selectionné
                sql +="enseignant"; // la table des enseignants 
                selected = 1 ;// indique que le type d'utilisateur a été bien séléctionné 
                
            }else {
            selected = 0 ;    
            errorLabel.setText("vous devez specifier qui etez vous avant de connecter"); // affichage d'un erreur si aucun né selectionné
            }
        
        
        
        
       
         
        
     
        if (selected == 1 ) { // si l'utilisateur a été selectionné 
        
            try {
                // lancement de la requete sql
                prepared = conx.prepareStatement(sql);  
                resultat=prepared.executeQuery();
                int i=0;
                while(resultat.next())
                {
                     String userName1= resultat.getString("nom");
                     String pass1= resultat.getString("pass");
                     i++;
                     System.out.println(i);
                     if(userName1.equals(userName)  &&  pass1.equals(pass))

                         {
                             Parent win = FXMLLoader.load(getClass().getResource(xmlFile)); // prendre le fichier xml qui concerne l'utilisateur séléctionné 
                             Scene S = new Scene(win);

                             Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();

                             window.setScene(S);
                             window.show();
                         }

           }
           
           

            } catch (SQLException ex){
                ex.printStackTrace();

            }
       
        }
    }
       
       
    }
    
    
        public void change(ActionEvent event) throws IOException
    {   
        String userselected;
        userselected= ch.getValue().toString();
        
        
        if(userselected.equals("Etudiant") )
        {
        Parent win = FXMLLoader.load(getClass().getResource("etudiant.fxml"));
        Scene S = new Scene(win);
        
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(S);
        window.show();
        }
        
        if(userselected.equals("Adminstrateur") )
        {
        Parent win2 = FXMLLoader.load(getClass().getResource("Adminstrateur.fxml"));
        Scene S2 = new Scene(win2);
        
        Stage window2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        window2.setScene(S2);
        window2.show();
        }
        
        if(userselected.equals("Enseignant") )
        {
        Parent win3 = FXMLLoader.load(getClass().getResource("Enseignant.fxml"));
        Scene S3 = new Scene(win3);
        
        Stage window3 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
        window3.setScene(S3);
        window3.show();
        }
    
    }

    
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
          
        ch.getItems().add("Etudiant");
        ch.getItems().add("Adminstrateur");
        ch.getItems().add("Enseignant");
    }    
    
    
}
