package de.leuphana.ui.view.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.ComboBox;

import de.leuphana.backend.service.AccountRoleService;

public class RoleSelect extends ComboBox<String> {

	@Autowired
	AccountRoleService accountRoleService;

	public RoleSelect() {
		setCaption("Role");
		setEmptySelectionAllowed(false);
		setItems(accountRoleService.findAllAsStringArray());
		setTextInputAllowed(false);
	}
}
