package de.leuphana.app.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import de.leuphana.backend.data.entity.neww.Account;
import de.leuphana.backend.service.AccountService;

/**
 * SecurityUtils takes care of all such static operations that have to do with
 * security and querying rights from different beans of the UI.
 *
 */
public class SecurityUtils {

	private SecurityUtils() {
		// Util methods only
	}

	/**
	 * Gets the account name of the currently signed in account.
	 *
	 * @return the account name of the current account or <code>null</code> if the
	 *         account has not signed in
	 */
	public static String getAccountname() {
		SecurityContext context = SecurityContextHolder.getContext();
		UserDetails accountDetails = (UserDetails) context.getAuthentication().getPrincipal();
		return accountDetails.getUsername();
	}

	/**
	 * Check if currently signed-in account is in the role with the given role
	 * name.
	 *
	 * @param role
	 *            the role to check for
	 * @return <code>true</code> if account is in the role, <code>false</code>
	 *         otherwise
	 */
	public static boolean isCurrentAccountInRole(String role) {
		return getAccountRoles().stream().filter(roleName -> roleName.equals(Objects.requireNonNull(role))).findAny()
				.isPresent();
	}

	/**
	 * Gets the roles the currently signed-in account belongs to.
	 *
	 * @return a set of all roles the currently signed-in account belongs to.
	 */
	public static Set<String> getAccountRoles() {
		SecurityContext context = SecurityContextHolder.getContext();
		return context.getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());
	}

	/**
	 * Gets the account object for the current account.
	 *
	 * @return the account object
	 */
	public static Account getCurrentAccount(AccountService accountService) {
		return accountService.findByEmail(SecurityUtils.getAccountname());
	}
}
