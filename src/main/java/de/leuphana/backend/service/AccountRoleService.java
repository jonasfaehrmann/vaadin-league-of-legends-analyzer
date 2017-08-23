package de.leuphana.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.backend.AccountRoleRepository;
import de.leuphana.backend.data.entity.Account_Role;

@Service
public class AccountRoleService {

	private final AccountRoleRepository accountRoleRepository;

	@Autowired
	public AccountRoleService(AccountRoleRepository accountRoleRepository) {
		this.accountRoleRepository = accountRoleRepository;
	}

	public Account_Role findAccountRoleById(Long id) {
		return accountRoleRepository.findOne(id);
	}

	public List<Account_Role> findAll() {
		return accountRoleRepository.findAll();
	}

	public String[] findAllAsStringArray() {
		List<Account_Role> accountRoleList = accountRoleRepository.findAll();

		String[] accountRolesStringArray = new String[accountRoleList.size()];
		int index = 0;
		for (Account_Role accountRole : accountRoleList) {
			accountRolesStringArray[index] = accountRole.getName();
			index++;
		}
		
		return accountRolesStringArray;
	}

}
