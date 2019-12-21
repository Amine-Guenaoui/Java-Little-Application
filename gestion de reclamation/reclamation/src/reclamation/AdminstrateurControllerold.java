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
import javafx.scene.chart.BarChart;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AdminstrateurControllerold implements Initializable {

    Connection conx=null;
    PreparedStatement prepared=null;
    ResultSet resultat=null;
    
    
    private TreeView<String> itemsTree;
    private BarChart<?, ?> chartBar;
   
    
    public void change(ActionEvent event) throws IOException
    {
        Parent win6 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene S6 = new Scene(win6);
        
        Stage window6 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
       window6.setScene(S6);
        window6.show();
     chartBar.setVisible(false);
    }
    
        // methode qui prendre la selection de treeview 
   
    public void treeSelected(MouseEvent event) {
    
        TreeItem<String> item = itemsTree.getSelectionModel().getSelectedItem();
        System.out.println(item.getValue() + "is selected from tree view .");
        // les donnes de chaque etudiant qui a été séléctionné sera affiché 
    
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // initialisation de treeview
        TreeItem<String> root = new TreeItem<>("tableaux");
        
        TreeItem<String> etudiants = new TreeItem<>("etudiants");
        TreeItem<String> enseignants = new TreeItem<>("enseignants");
        TreeItem<String> reclamations = new TreeItem<>("reclamations");
        
       
         
// root.getChildren().addAll(etudiants,ensiegnants,reclamations);
        // les requetes a utiliser 
        String sql_etudiant="SELECT nom,prenom FROM etudiant";
        String sql_enseignant="SELECT nom,prenom FROM enseignant";
        String sql_reclamation="SELECT * FROM reclamations";
        // conexion a la base 
        ConnectionClass CC=new ConnectionClass();
            
           
        
       
        // prenonons la liste des etudiants 
        try {
             
             conx= CC.getConnection();
        
             prepared = conx.prepareStatement(sql_etudiant);
             resultat= prepared.executeQuery();// lancement de la requete sql
             int i=1;
               // prendre les donnés pour la liste des etudiants 
             while (resultat.next()) {
                    String nom= resultat.getString("nom");// attribut 1
                    String prenom= resultat.getString("prenom"); // attribut 2 
                     
                    
                   etudiants.getChildren().add(new TreeItem<>(nom +" "+prenom) );
                   System.out.println("chargement des donnes des etudiants .. \n" + i++ );
                
                
         }
          
        } catch (SQLException ex){
                ex.printStackTrace();

            } 
        // prenonons la liste des enseignants 
        try {
             Connection C= CC.getConnection();
             conx= CC.getConnection();
        
             prepared = conx.prepareStatement(sql_enseignant);
             resultat= prepared.executeQuery();// lancement de la requete sql
             int i=1;
               // prendre les donnés pour la liste des etudiants 
             while (resultat.next()) {
                    String nom= resultat.getString("nom");// attribut 1
                    String prenom= resultat.getString("prenom"); // attribut 2 
                     
                    
                   enseignants.getChildren().add(new TreeItem<>(nom +" "+prenom) );
                   System.out.println("chargement des donnez des enseignants.. \n" + i++ );
                
                
         }
          
        } catch (SQLException ex){
                ex.printStackTrace();

            } 
        
        
        
       
        //etudiants.getChildren().add(new TreeItem<> ("hello"));
        
        
        root.getChildren().add(etudiants);
        root.getChildren().add(enseignants);
        root.getChildren().add(reclamations);
        
        itemsTree.setRoot(root);
        
        
        
        
        
        
    }    
    
}
