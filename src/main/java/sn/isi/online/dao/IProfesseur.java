package sn.isi.online.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.isi.online.entities.Professeur;

@Repository
public interface IProfesseur extends JpaRepository<Professeur, String> {


    @Query("select p from Professeur p where p.email = :email and p.password = :password")
    public Professeur chercher(@Param("email") String e, @Param("password") String p);


    @Query("select p from Professeur p where p.email = :email")
    public Professeur getProfesseurByEmail(@Param("email") String e);

}
