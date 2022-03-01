package sn.isi.online.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professeur implements Serializable {
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "professeurs_roles",
            joinColumns = { @JoinColumn(name = "email", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "nom", nullable = false, updatable = false)})
    private List<Roles> roles = new ArrayList<>();
    @OneToMany(mappedBy = "professeur")
    private List<Cours> cours = new ArrayList<>();


    public Professeur() {
    }

    public Professeur(String nom, String prenom, String telephone, String email, String password, int etat) {
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
