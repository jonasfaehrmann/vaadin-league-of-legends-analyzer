package de.leuphana.ui.view.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.data.account.AccountRole;
import de.leuphana.backend.service.account.AccountRoleService;

@SpringComponent
public class RoleComboBoxDataProvider extends PageableDataProvider<AccountRole, String>{
	
	private final AccountRoleService accountRoleService;

	@Autowired
	public RoleComboBoxDataProvider(AccountRoleService accountRoleService) {
		this.accountRoleService = accountRoleService;
	}

	protected String[] fetchFromBackEnd() {
		return accountRoleService.findAllAsStringArray();
	}

	@Override
	protected Page<AccountRole> fetchFromBackEnd(Query<AccountRole, String> query, Pageable pageable) {
		return accountRoleService.findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<AccountRole, String> query) {
		return (int) accountRoleService.countAnyMatching(query.getFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}
}
