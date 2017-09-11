package de.leuphana.ui.view.admin.account;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.spring.annotation.SpringView;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.ui.util.WidgetSetToCommaSeparatedStringConverter;
import de.leuphana.ui.view.admin.AbstractCrudView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

@SpringView
public class AccountAdminView extends AbstractCrudView<Account> {

	private final AccountAdminPresenter presenter;

	private final AccountAdminViewDesign accountAdminViewDesign;
	
	private final WidgetSetToCommaSeparatedStringConverter widgetsToCommaSeparatedStringConverter;
	
	private boolean passwordRequired;
	
	@Autowired
	public AccountAdminView(AccountAdminPresenter presenter, WidgetSetToCommaSeparatedStringConverter setToCommaSeparatedStringConverter) {
		this.presenter = presenter;
		this.widgetsToCommaSeparatedStringConverter = setToCommaSeparatedStringConverter;
		accountAdminViewDesign = new AccountAdminViewDesign();
	} 
	
	/**
	 * Custom validator to be able to decide dynamically whether the password
	 * field is required or not (empty value when updating the account is
	 * interpreted as 'do not change the password').
	 */
	private Validator<String> passwordValidator = new Validator<String>() {

 		BeanValidator passwordBeanValidator = new BeanValidator(Account.class, "password");

		@Override
		public ValidationResult apply(String value, ValueContext context) {
			if (!passwordRequired && value.isEmpty()) {
				// No password required and field is empty
				// OK regardless of other restrictions as the empty value will
				// not be used
				return ValidationResult.ok();
			} else {
				return passwordBeanValidator.apply(value, context);
			}
		}
	};

	@PostConstruct
	private void init() {
		presenter.init(this);
		getGrid().setColumns("email", "name", "role.roleName", "summonerName");
		getGrid().addColumn(account -> widgetsToCommaSeparatedStringConverter.convertToPresentation(account.getWidgets())).setCaption("Widgets");
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Account> binder) {
		binder.forField(getViewComponent().password).withValidator(passwordValidator).bind(bean -> "",
				(bean, value) -> {
					if (value.isEmpty()) {
						// If nothing is entered in the password field, do
						// nothing
					} else {
						bean.setPassword(presenter.encodePassword(value));
					}
				});
		binder.bindInstanceFields(getViewComponent());
	}

	public void setPasswordRequired(boolean passwordRequired) {
		this.passwordRequired = passwordRequired;
		getViewComponent().password.setRequiredIndicatorVisible(passwordRequired);
	}

	@Override
	public AccountAdminViewDesign getViewComponent() {
		return accountAdminViewDesign;
	}

	@Override
	protected AccountAdminPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Account> getGrid() {
		return getViewComponent().accountGrid;
	}

	@Override
	protected void setGrid(Grid<Account> grid) {
		getViewComponent().accountGrid = grid;
	}

	@Override
	protected Component getForm() {
		return getViewComponent().form;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}

	@Override
	protected Button getDelete() {
		return getViewComponent().delete;
	}

	@Override
	protected Button getUpdate() {
		return getViewComponent().update;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().email;
	}

}
