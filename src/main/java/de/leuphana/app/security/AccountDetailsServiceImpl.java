package de.leuphana.app.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.leuphana.backend.data.Role;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.service.UserService;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public AccountDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = userService.findByEmail(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			String userRole = "";
		
			switch (user.getRole_Id()) {
			case 1:
				userRole = Role.ADMIN; 
				break;
			case 2:
				userRole = Role.BAKER;
			default:
				break;
			}
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(userRole)));
		}
	}
}