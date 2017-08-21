package de.leuphana.ui.view.admin.user;

import java.io.Serializable;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import de.leuphana.backend.data.entity.User;
import de.leuphana.backend.data.entity.neww.Account;
import de.leuphana.backend.service.AccountService;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class AccountAdminPresenter extends AbstractCrudPresenter<Account, AccountService, AccountAdminView>
		implements Serializable {

	@Autowired
	public AccountAdminPresenter(AccountAdminDataProvider accountAdminDataProvider, NavigationManager navigationManager,
			AccountService service, BeanFactory beanFactory) {
		super(navigationManager, service, Account.class, accountAdminDataProvider, beanFactory);
	}

	public String encodePassword(String value) {
		return getService().encodePassword(value);
	}

	@Override
	protected void editItem(Account item) {
		super.editItem(item);
		getView().setPasswordRequired(item.isNew());
	}
}