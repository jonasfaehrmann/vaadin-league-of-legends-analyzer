package de.leuphana.backend;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.backend.data.entity.Account;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByEmail(String email);

	Page<Account> findByEmailLikeIgnoreCaseOrNameLikeIgnoreCase(String emailLike, String nameLike, Pageable pageable);

	long countByEmailLikeIgnoreCaseOrNameLikeIgnoreCase(String emailLike, String nameLike);
}
