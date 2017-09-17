package de.leuphana.ui.view.admin;

import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;

import de.leuphana.backend.data.account.AccountRole;

@SpringComponent
@PrototypeScope
public class RoleComboBox extends ComboBox<AccountRole> {
	
	private final RoleComboBoxDataProvider dataProvider;
	
	public RoleComboBox(RoleComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setCaption("Role");
		setEmptySelectionAllowed(false);
		setItemCaptionGenerator(AccountRole::getRoleName);
		setDataProvider(dataProvider);
		setTextInputAllowed(false);
	}
	
}