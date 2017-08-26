package de.leuphana.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.leuphana.backend.AccountRoleRepository;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.data.entity.AccountRole;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@Service
public class AccountRoleService extends CrudService<AccountRole> {

	private final AccountRoleRepository accountRoleRepository;

	@Autowired
	public AccountRoleService(AccountRoleRepository accountRoleRepository) {
		this.accountRoleRepository = accountRoleRepository;
	}

	public AccountRole findAccountRoleByName(Long id) {
		return accountRoleRepository.findOne(id);
	}

	public List<AccountRole> findAll() {
		return accountRoleRepository.findAll();
	}

	public String[] findAllAsStringArray() {
		List<AccountRole> accountRoleList = accountRoleRepository.findAll();

		String[] accountRolesStringArray = new String[accountRoleList.size()];
		int index = 0;
		for (AccountRole accountRole : accountRoleList) {
			accountRolesStringArray[index] = accountRole.getRoleName();
			index++;
		}
		
		return accountRolesStringArray;
	}

	public Page<AccountRole> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}
	
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	protected AccountRoleRepository getRepository() {
		return accountRoleRepository;
	}

}
