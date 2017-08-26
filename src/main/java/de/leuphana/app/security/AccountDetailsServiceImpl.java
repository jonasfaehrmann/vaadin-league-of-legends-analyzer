package de.leuphana.app.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.service.AccountRoleService;
import de.leuphana.backend.service.AccountService;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

	private final AccountService accountService;
	
	@Autowired
	public AccountDetailsServiceImpl(AccountService userService) {
		this.accountService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String accountname) throws UsernameNotFoundException {
		Account account = accountService.findByEmail(accountname);
		if (null == account) {
			throw new UsernameNotFoundException("No user present with accountname: " + accountname);
		} else {
			return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getRoleName())));
		}
	}
}