package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
