package sn.isi.online.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sn.isi.online.entities.Etudiant;

@Repository
public interface IEtudiant extends JpaRepository<Etudiant, String> {
}
