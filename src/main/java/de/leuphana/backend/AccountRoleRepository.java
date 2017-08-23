package de.leuphana.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.backend.data.entity.Account_Role;

public interface AccountRoleRepository extends JpaRepository<Account_Role, Long> {

}
