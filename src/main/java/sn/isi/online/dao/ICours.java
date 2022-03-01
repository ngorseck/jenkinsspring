package sn.isi.online.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.online.entities.Cours;

@Repository
public interface ICours extends JpaRepository<Cours, Integer> {
}
