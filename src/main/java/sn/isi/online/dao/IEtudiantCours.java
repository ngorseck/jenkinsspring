package sn.isi.online.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.online.entities.EtudiantCours;

@Repository
public interface IEtudiantCours extends JpaRepository<EtudiantCours, Integer> {
}
