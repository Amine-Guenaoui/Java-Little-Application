/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation;



/**
 *
 * @author Administrator
 */
public class Reclamations {
        
        private String id;
        private String reclamation;
        private String date;

    public String getId() {
        return id;
    }

    public String getReclamation() {
        return reclamation;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public void setDate(String date) {
        this.date = date;
    }
  
    public Reclamations(String id, String reclamation, String date) {
        this.id = id;
        this.reclamation = reclamation;
        this.date = date;
    }
        
        
}
