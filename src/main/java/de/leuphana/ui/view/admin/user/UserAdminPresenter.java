package de.leuphana.ui.view.admin.user;

import java.io.Serializable;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.service.UserService;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class UserAdminPresenter extends AbstractCrudPresenter<Account, UserService, UserAdminView>
		implements Serializable {

	@Autowired
	public UserAdminPresenter(UserAdminDataProvider userAdminDataProvider, NavigationManager navigationManager,
			UserService service, BeanFactory beanFactory) {
		super(navigationManager, service, Account.class, userAdminDataProvider, beanFactory);
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