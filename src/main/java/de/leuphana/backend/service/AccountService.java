package de.leuphana.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.backend.AccountRepository;
import de.leuphana.backend.data.entity.neww.Account;

@Service
public class AccountService extends CrudService<Account> {

		private static final String MODIFY_LOCKED_USER_NOT_PERMITTED = "User has been locked and cannot be modified or deleted";
		private final PasswordEncoder passwordEncoder;
		private final AccountRepository accountRepository;
		
		@Autowired
		public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
			this.accountRepository = accountRepository;
			this.passwordEncoder = passwordEncoder;
		}

		public Account findByEmail(String email) {
			return getRepository().findByEmail(email);
		}

		@Override
		public Page<Account> findAnyMatching(Optional<String> filter, Pageable pageable) {
			if (filter.isPresent()) {
				String repositoryFilter = "%" + filter.get() + "%";
				return getRepository().findByEmailLikeIgnoreCaseOrNameLikeIgnoreCase(repositoryFilter,
						repositoryFilter, pageable);
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
			return accountRepository;
		}

		public String encodePassword(String value) {
			return passwordEncoder.encode(value);
		}

		@Override
		@Transactional
		public Account save(Account entity) {
			throwIfAccountLocked(entity.getId());
			return super.save(entity);
		}

		@Override
		@Transactional
		public void delete(Integer accountId) {
			throwIfAccountLocked(accountId);
			super.delete(accountId);
		}

		private void throwIfAccountLocked(Integer accountId) {
			if (accountId == null) {
				return;
			}

			Account account = getRepository().findOne(accountId);
			if (account.isLocked()) {
				throw new UserFriendlyDataException(MODIFY_LOCKED_USER_NOT_PERMITTED);
			}
		}

}
