package de.leuphana.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import de.leuphana.backend.data.entity.neww.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findByEmail(String email);

	Page<Account> findByEmailLikeIgnoreCaseOrNameLikeIgnoreCase(String emailLike, String nameLike, Pageable pageable);

	Integer countByEmailLikeIgnoreCaseOrNameLikeIgnoreCase(String emailLike, String nameLike);

	Page<Account> findAll(Pageable pageable);

}
