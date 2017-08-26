package de.leuphana.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.backend.data.entity.AccountRole;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {

	Page<AccountRole> findByNameLikeIgnoreCase(String name, Pageable page);

	long countByNameLikeIgnoreCase(String name);

}