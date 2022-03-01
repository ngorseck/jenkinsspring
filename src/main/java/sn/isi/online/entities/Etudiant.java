package sn.isi.online.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Etudiant implements Serializable {
    @Id
    @Column(length=200)
    private String email;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String telephone;
    @Column
    private String password;
    @Column
    private int etat;
    @OneToMany(mappedBy = "etudiant")
    private List<EtudiantCours> etudiantCours = new ArrayList<>();



    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String telephone, String email, String password, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.etat = etat;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public List<EtudiantCours> getEtudiantCours() {
        return etudiantCours;
    }

    public void setEtudiantCours(List<EtudiantCours> etudiantCours) {
        this.etudiantCours = etudiantCours;
    }
}
