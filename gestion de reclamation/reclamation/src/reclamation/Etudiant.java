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
public class Etudiant {
    
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String groupe;
    private String specialite;
    private String matricule;
    private String creation_date;
    private String modification_date;
    private String statu;

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public void setModification_date(String modification_date) {
        this.modification_date = modification_date;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupe() {
        return groupe;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getModification_date() {
        return modification_date;
    }

    public String getStatu() {
        return statu;
    }

    public Etudiant(String id, String nom, String prenom, String email, String groupe, String specialite, String matricule, String creation_date, String modification_date, String statu) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.groupe = groupe;
        this.specialite = specialite;
        this.matricule = matricule;
        this.creation_date = creation_date;
        this.modification_date = modification_date;
        this.statu = statu;
    }
    
}
