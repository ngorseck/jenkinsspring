package sn.isi.online.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String libelle;
    @ManyToOne
    private Professeur professeur;
    @OneToMany(mappedBy = "cours")
    private List<EtudiantCours> etudiantCours = new ArrayList<>();



    public Cours() {
    }

    public Cours(int id, String libelle, Professeur professeur) {
        this.id = id;
        this.libelle = libelle;
        this.professeur = professeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public List<EtudiantCours> getEtudiantCours() {
        return etudiantCours;
    }

    public void setEtudiantCours(List<EtudiantCours> etudiantCours) {
        this.etudiantCours = etudiantCours;
    }
}
