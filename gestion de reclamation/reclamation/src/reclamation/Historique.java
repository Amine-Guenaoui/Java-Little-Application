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
public class Historique {
     
    private String id;
    private String action;
    private String date;
    private String fait_par;

   

    public String getFait_par() {
        return fait_par;
    }

    public void setFait_par(String fait_par) {
        this.fait_par = fait_par;
    }

    public Historique(String id, String action, String date, String fait_par) {
        this.id = id;
        this.action = action;
        this.date = date;
        this.fait_par = fait_par;
    }

    public Historique(String id, String action, String date) {
        this.id = id;
        this.action = action;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDate(String date) {
        this.date = date;
    }
        
        
     
     
}
