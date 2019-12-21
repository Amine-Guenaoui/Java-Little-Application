/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AdminstrateurController implements Initializable {
    
    // variables speciales pour la base de donnes 
    Connection conx=null;
    PreparedStatement prepared=null;
    ResultSet resultat=null;

    @FXML
    private Button reclamation;
    @FXML
    private Button etudiants;
    @FXML
    private Button ensiegnants;
    @FXML
    private Button historique;
    @FXML
    private Button configuration;
    @FXML
    private Button deconexion;
    @FXML
    private ImageView Close;
    @FXML
    private Pane dashboardPane;
    @FXML
    private Pane reclamationPane;
    @FXML
    private Pane etudiantsPane;
    @FXML
    private Pane enseignantsPane;
    @FXML
    private Pane configurationPane;
    @FXML
    private Pane historiquePane;
    @FXML
    private TableView<Enseignant> enseignantsTable;
    @FXML
    private TableView<Reclamations> reclamationTable;
    @FXML
    private TableView<Historique> historiqueTable;
    @FXML
    private TableView<Etudiant> etudiantsTable;
    @FXML
    private TextField confNom;
    @FXML
    private Text confPrenom;
    @FXML
    private TextField confEmail;
    @FXML
    private PasswordField confPass1;
    @FXML
    private PasswordField confPass2;
    @FXML
    private Button confConfirm;
    @FXML
    private Button confClear;
    @FXML
    private Text headerTime;
    @FXML
    private Text errorConfMsg;
    @FXML
    private Text headerNom;
    @FXML
    private Text headerPrenom;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            dashboardPane.setVisible(false);
            reclamationPane.setVisible(false);
            etudiantsPane.setVisible(false);
            enseignantsPane.setVisible(false);
            historiquePane.setVisible(false);
            configurationPane.setVisible(false);
            
        // inistialisation des etudiants
        etudiantsTable.setEditable(true);
        TableColumn<Etudiant,String> idCol = new TableColumn("id");
        TableColumn<Etudiant,String> nomCol = new TableColumn("nom");
        TableColumn<Etudiant,String> prenomCol = new TableColumn("prenom");
        TableColumn<Etudiant,String> emailCol = new TableColumn("email");
        TableColumn<Etudiant,String> groupeCol = new TableColumn("groupe");
        TableColumn<Etudiant,String> specialiteCol = new TableColumn("specialite");
        TableColumn<Etudiant,String> matriculeCol = new TableColumn("matricule");
        TableColumn<Etudiant,String> creationDateCol = new TableColumn("creation_date");        
        TableColumn<Etudiant,String> modificationDateCol = new TableColumn("modification_date");
        TableColumn<Etudiant,String> statuCol = new TableColumn("statu"); 
        etudiantsTable.getColumns().addAll(idCol,nomCol,prenomCol,emailCol,groupeCol,specialiteCol,matriculeCol,creationDateCol,modificationDateCol,statuCol);
        
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        groupeCol.setCellValueFactory(new PropertyValueFactory("groupe"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory("specialite"));
        matriculeCol.setCellValueFactory(new PropertyValueFactory("matricule"));
        creationDateCol.setCellValueFactory(new PropertyValueFactory("creation_date"));
        modificationDateCol.setCellValueFactory(new PropertyValueFactory("modification_date"));
        statuCol.setCellValueFactory(new PropertyValueFactory("statu"));
        
         // initisalization de la table des enseignants 
        enseignantsTable.setEditable(true);
        TableColumn<Enseignant,String> idCol1 = new TableColumn("id");
        TableColumn<Enseignant,String> nomCol1 = new TableColumn("nom");
        TableColumn<Enseignant,String> prenomCol1 = new TableColumn("prenom");
        TableColumn<Enseignant,String> emailCol1 = new TableColumn("email");
        TableColumn<Enseignant,String> gradeCol = new TableColumn("grade");
        TableColumn<Enseignant,String> moduleCol = new TableColumn("module");
        TableColumn<Enseignant,String> creationDateCol1 = new TableColumn("creation date");        
        TableColumn<Enseignant,String> modificationDateCol1 = new TableColumn("modification date");
        
        enseignantsTable.getColumns().addAll(idCol1,nomCol1,prenomCol1,emailCol1,gradeCol,moduleCol,creationDateCol1,modificationDateCol1);
        
        
        idCol1.setCellValueFactory(new PropertyValueFactory("id"));
        nomCol1.setCellValueFactory(new PropertyValueFactory("nom"));
        prenomCol1.setCellValueFactory(new PropertyValueFactory("prenom"));
        emailCol1.setCellValueFactory(new PropertyValueFactory("email"));
        gradeCol.setCellValueFactory(new PropertyValueFactory("grade"));
        moduleCol.setCellValueFactory(new PropertyValueFactory("module"));
        creationDateCol1.setCellValueFactory(new PropertyValueFactory("creation_date"));
        modificationDateCol1.setCellValueFactory(new PropertyValueFactory("modification_date"));
        
        //initializing the header for the admin and saving his name and some few data 
        String sql = "SELECT * FROM admin_online";  
        String user = "error",prenom = "error";
      /*  try {  
              PreparedStatement prepared1 = conx.prepareStatement(sql);
             ResultSet resultat1=prepared1.executeQuery();

                while(resultat1.next())
                {
                     user= resultat.getString("nom");
                     prenom= resultat.getString("prenom");
                     System.out.println("chargement des donnes de l'utilisateur encours ... \n");

                }
        headerNom.setText(user);
        headerPrenom.setText(prenom);
        } catch (SQLException ex) {
            Logger.getLogger(AdminstrateurController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("chargement des donnes de lutilisateur na pas marcher ! \n");
        }
        */       
        
       
    }    

    @FXML
    private void showDashboard(ActionEvent event) {
            dashboardPane.setVisible(true);
            reclamationPane.setVisible(false);
            etudiantsPane.setVisible(false);
            enseignantsPane.setVisible(false);
            configurationPane.setVisible(false);
            
    }

    @FXML
    private void showReclamation(ActionEvent event) {
        
            dashboardPane.setVisible(false);
            reclamationPane.setVisible(true);
            etudiantsPane.setVisible(false);
            enseignantsPane.setVisible(false);
            historiquePane.setVisible(false);
            configurationPane.setVisible(false);
            
    }

    @FXML
    private void showEtudiants(ActionEvent event) {
            
            dashboardPane.setVisible(false);
            reclamationPane.setVisible(false);
            etudiantsPane.setVisible(true);
            enseignantsPane.setVisible(false);
            historiquePane.setVisible(false);
            configurationPane.setVisible(false);
               // chargement des donnes des enseignants 
                    // conexion a la base 
        ConnectionClass CC=new ConnectionClass();
        String sql_etudiant="SELECT * FROM etudiant";
        // preparation du tableView 
         // initisalization de la table 
        
        
        
        
        // declaration de la liste d'ou nous allons garder les donnes
        
        ObservableList<Etudiant> data;
        data = FXCollections.observableArrayList();
      
        
        
       
        // prenonons la liste des etudiants 
        try {
             System.out.println("chargement des donnes des etudiants begin .. \n");
             conx= CC.getConnection();
        
             prepared = conx.prepareStatement(sql_etudiant);
             resultat= prepared.executeQuery();// lancement de la requete sql
             System.out.println("requete est lance .. \n" );
             int i=1;
               // prendre les donnés pour la liste des etudiants 
             while (resultat.next()) {
                    String id= resultat.getString("id");// attribut 1
                    String nom= resultat.getString("nom");// attribut 2
                    String prenom= resultat.getString("prenom"); // attribut 3 
                    String email = resultat.getString("email");
                    String groupe = resultat.getString("groupe");// ... 
                    String specialite = resultat.getString("specialite");
                    String matricule = resultat.getString("matricule");
                    String statu = resultat.getString("statu");
                    
                    Date creation_date = new Date (resultat.getDate("creation_date").getTime());
                    Date modification_date = new Date (resultat.getDate("modification_date").getTime());
                    
                    System.out.println(" " + id + " " + nom + " " + prenom + " " +groupe + " " + specialite + " " + matricule + " " + creation_date.toString() + " " + modification_date.toString() );
                    
                    Etudiant d = new Etudiant(id , nom , prenom ,email , groupe , specialite ,matricule, creation_date.toString() , modification_date.toString() , statu );
                    data.add(d);
                   
                  //  list.add(new Enseignant(id,nom , prenom ,email , grade , module , "2018" , "2019" ));    
                  //  System.out.println(" " + id + " " + nom + " " + prenom + " " +grade + " " + module + ";\n"  ); 
                    
                   
                   System.out.println("chargement des donnes des etudiants.. \n" + i++ );
                
                
         }
          
        } catch (SQLException ex){
                ex.printStackTrace();

            } 
        System.out.println("chargement des donnes des etudiants 100% \n");
          // ajoutons les donnes au tableau 
        
        
        etudiantsTable.setItems(data);
        System.out.println("chargement des donnes des etudiants ..\n");
    }

    @FXML
    private void showEnseignants(ActionEvent event) {
        
            dashboardPane.setVisible(false);
            reclamationPane.setVisible(false);
            etudiantsPane.setVisible(false);
            enseignantsPane.setVisible(true);
            historiquePane.setVisible(false);
            configurationPane.setVisible(false);
               // chargement des donnes des enseignants 
                    // conexion a la base 
        ConnectionClass CC=new ConnectionClass();
        String sql_etudiant="SELECT * FROM enseignant";
        // preparation du tableView 
        
        
        
        // declaration de la liste d'ou nous allons garder les donnes
        
        ObservableList<Enseignant> data;
        data = FXCollections.observableArrayList();
      
        
        
       
        // prenonons la liste des enseignants 
        try {
             System.out.println("chargement des donnes des enseignants begin .. \n");
             conx= CC.getConnection();
        
             prepared = conx.prepareStatement(sql_etudiant);
             resultat= prepared.executeQuery();// lancement de la requete sql
             System.out.println("requete est lance .. \n" );
             int i=1;
               // prendre les donnés pour la liste des etudiants 
             while (resultat.next()) {
                    String id= resultat.getString("id");// attribut 1
                    String nom= resultat.getString("nom");// attribut 2
                    String prenom= resultat.getString("prenom"); // attribut 3 
                    String email = resultat.getString("email");
                    String grade = resultat.getString("grade");// ... 
                    String module = resultat.getString("module");
                    
                    Date creation_date = new Date (resultat.getDate("creation_date").getTime());
                    Date modification_date = new Date (resultat.getDate("modification_date").getTime());
                    
                    System.out.println(" " + id + " " + nom + " " + prenom + " " +grade + " " + module + " " + creation_date.toString() + " " + modification_date.toString() );
                    
                    Enseignant d = new Enseignant(id , nom , prenom ,email , grade , module , creation_date.toString() , modification_date.toString() );
                    data.add(d);
                   
                  //  list.add(new Enseignant(id,nom , prenom ,email , grade , module , "2018" , "2019" ));    
                  //  System.out.println(" " + id + " " + nom + " " + prenom + " " +grade + " " + module + ";\n"  ); 
                    
                   
                   System.out.println("chargement des donnes des enseignants.. \n" + i++ );
                
                
         }
          
        } catch (SQLException ex){
                ex.printStackTrace();

            } 
        System.out.println("chargement des donnes des etudiants 100% \n");
          // ajoutons les donnes au tableau 
      
        enseignantsTable.setItems(data);
        System.out.println("chargement des donnes des enseignants ..\n");
            
         
        
                
        
            
    }

    @FXML
    private void showHistory(ActionEvent event) {
            
            dashboardPane.setVisible(false);
            reclamationPane.setVisible(false);
            etudiantsPane.setVisible(false);
            enseignantsPane.setVisible(false);
            historiquePane.setVisible(true);
            configurationPane.setVisible(false);
            
                // chargement des donnes des historiaue
                    // conexion a la base 
        ConnectionClass CC=new ConnectionClass();
        String sql_historique="SELECT * FROM historique";
        // preparation du tableView 
         // initisalization de la table 
        historiqueTable.setEditable(true);
        TableColumn<Historique,String> idCol = new TableColumn("Id");
        TableColumn<Historique,String> actionCol = new TableColumn("Action");
        TableColumn<Historique,String> faitParCol = new TableColumn("Date de creation");
        TableColumn<Historique,String> dateCol = new TableColumn("Fait Par");
        historiqueTable.getColumns().addAll(idCol,actionCol,faitParCol,dateCol);
        
        
        
        
        // declaration de la liste d'ou nous allons garder les donnes 
        
        ObservableList<Historique> data;
        data = FXCollections.observableArrayList();
        
        // prenonons l'historique
        try {
             System.out.println("chargement des donnes d'historique begin .. \n");
             conx= CC.getConnection();
        
             prepared = conx.prepareStatement(sql_historique);
             resultat= prepared.executeQuery();// lancement de la requete sql
             System.out.println("requete est lance .. \n" );
             int i=1;
               // prendre les donnés pour la liste de l'historique
             while (resultat.next()) {
                    String id= resultat.getString("id");// attribut 1
                    String action= resultat.getString("action");// attribut 2
                    String fait_par= resultat.getString("fait_par"); // attribut 3 
                    Date date = new Date (resultat.getDate("date").getTime());
                    
                    System.out.println(" " + id + " " + action + " " + fait_par + " " + date.toString() );
                    
                    Historique d = new Historique(id ,action ,fait_par ,date.toString() );
                    data.add(d);
                   
                  //  list.add(new Enseignant(id,nom , prenom ,email , grade , module , "2018" , "2019" ));    
                  //  System.out.println(" " + id + " " + nom + " " + prenom + " " +grade + " " + module + ";\n"  ); 
                    
                   
                   System.out.println("chargement des donnes des enseigants .. \n" + i++ );
                
                
        }
          
        } catch (SQLException ex){
                ex.printStackTrace();

            } 
        System.out.println("chargement des donnes d'historique 100% \n");
          // ajoutons les donnes au tableau 
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        actionCol.setCellValueFactory(new PropertyValueFactory("action"));
        faitParCol.setCellValueFactory(new PropertyValueFactory("fait_par"));
        
        historiqueTable.setItems(data);
        System.out.println("chargement des donnes de l'historique termine  \n");
        
                
        
            
    
    }

    @FXML
    private void showConfiguration(ActionEvent event) {
            dashboardPane.setVisible(false);
            reclamationPane.setVisible(false);
            etudiantsPane.setVisible(false);
            enseignantsPane.setVisible(false);
            historiquePane.setVisible(false);
            configurationPane.setVisible(true);
    }

    @FXML
    private void Disconnect(ActionEvent event) throws IOException, SQLException {
        // suppression de la session 
        ConnectionClass CC=new ConnectionClass();
        conx= CC.getConnection();
        Statement statement= conx.createStatement();
        String sql_supression = "DELETE FROM `reclamation`.`admins` WHERE  `nom`='"+headerNom.getText()+"' AND `prenom`='"+headerPrenom.getText()+"' " ;
        statement.executeUpdate(sql_supression);
        
        // changement de la fenetre 
         Parent win6 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene S6 = new Scene(win6);
        
        Stage window6 =(Stage)((Node)event.getSource()).getScene().getWindow();
        
       window6.setScene(S6);
        window6.show();
     
    }

    @FXML
    private void confirmationDialogue(ActionEvent event) throws IOException, SQLException {
        // ouvrir un nouveau dialogue 
        //  WindowsDialogueOpener confirmation = new WindowsDialogueOpener("dialogueconfConfirmationFXML.fxml");
        if (confNom.equals("")) {
            errorConfMsg.setText("entrez votre nouveau nom");
        }else if (confPrenom.equals("")) {
            errorConfMsg.setText("entrez votre nouveau prenom");
        }else if (confEmail.equals("")) {
            errorConfMsg.setText("entrez votre nouveau Email");
        }else if (confPass1.equals("")) {
            errorConfMsg.setText("entrez votre ancien mot de passe");
        }else if (confPass2.equals("")) {
            errorConfMsg.setText("entrez votre nouveau mot de passe");
        }else {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation d'action");
        alert.setHeaderText("Voulez vous changer vos infos du profile ?");
        //alert.setContentText("Etez vous .");
        ButtonType buttonTypeOne = new ButtonType("Oui");
        ButtonType buttonTypeCancel = new ButtonType("Non", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        // prendre le resultat de la question 
        Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == buttonTypeOne){
        System.out.println("information en train d'etre changer .... \n");
        
        // appelons la base pour enregistrer les infos 
        // connecxion a la base et modifiant les donnes 
            ConnectionClass CC=new ConnectionClass();
            conx= CC.getConnection();
            String sql=" UPDATE `reclamation`.`admins` SET `nom`='"+confNom.getText()+"', `prenom`='"+confPrenom.getText()+"', `email`='"+confEmail.getText()+"', `pass`='"+confPass2.getText()+"' WHERE  `nom`='"+headerNom.getText()+ "' AND `prenom`='"+headerPrenom.getText()+"' ;";
            Statement statement= conx.createStatement();
            statement.executeUpdate(sql);// modification dans la base 
            sql = "UPDATE `reclamation`.`admin_online` SET `nom`='"+confNom.getText()+"', `prenom`='"+confPrenom.getText()+"' WHERE `nom`='"+headerNom.getText()+ "' AND `prenom`='"+headerPrenom.getText()+"' ;";
            statement.executeUpdate(sql);
            headerNom.setText(confNom.getText()); // pour changer l'entete ( dynamique )
            headerPrenom.setText(confPrenom.getText());
            System.out.println("changement des donnes termine");
    }
    }
    }

    @FXML
    private void annulerConfButton(ActionEvent event) {
    }
    
}
