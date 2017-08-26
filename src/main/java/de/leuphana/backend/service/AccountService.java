package de.leuphana.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.backend.AccountRepository;
import de.leuphana.backend.data.entity.Account;

@Service
public class AccountService extends CrudService<Account> {

	private static final String MODIFY_LOCKED_USER_NOT_PERMITTED = "User has been locked and cannot be modified or deleted";
	private final PasswordEncoder passwordEncoder;
	private final AccountRepository userRepository;

	@Autowired
	public AccountService(AccountRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Account findByEmail(String email) {
		return getRepository().findByEmail(email);
	}

	@Override
	public Page<Account> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByEmailLikeIgnoreCaseOrNameLikeIgnoreCaseOrAccountRoleLikeIgnoreCase(repositoryFilter,
					repositoryFilter, repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByEmailLikeIgnoreCaseOrNameLikeIgnoreCase(repositoryFilter, repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	@Override
	protected AccountRepository getRepository() {
		return userRepository;
	}

	public String encodePassword(String value) {
		return passwordEncoder.encode(value);
	}

	@Override
	@Transactional
	public Account save(Account entity) {
		System.out.println("**************************");
		System.out.println("Account id" + entity.getId());
		System.out.println("**************************");
		throwIfUserLocked(entity.getId());
		return super.save(entity);
	}

	@Override
	@Transactional
	public void delete(long userId) {
		throwIfUserLocked(userId);
		super.delete(userId);
	}

	private void throwIfUserLocked(Long userId) {
		if (userId == null) {
			return;
		}

		Account dbUser = getRepository().findOne(userId);
		if (dbUser.isLocked()) {
			throw new AccountFriendlyDataException(MODIFY_LOCKED_USER_NOT_PERMITTED);
		}
	}

}
