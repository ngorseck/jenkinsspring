package sn.isi.online.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.online.entities.Roles;

@Repository
public interface IRoles extends JpaRepository<Roles, String> {
}
