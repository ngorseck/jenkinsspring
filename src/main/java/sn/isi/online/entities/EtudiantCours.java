package sn.isi.online.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EtudiantCours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Cours cours = new Cours();
    @ManyToOne
    private Etudiant etudiant = new Etudiant();



    public EtudiantCours() {
    }

    public EtudiantCours(int id, Cours cours, Etudiant etudiant) {
        this.id = id;
        this.cours = cours;
        this.etudiant = etudiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    //Etudiant 1 -------*EtudiantCours*-------- 1Cours
}
