package de.leuphana.ui.view.widgetedit;

import java.io.Serializable;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.data.entity.Widget;
import de.leuphana.backend.service.AccountService;
import de.leuphana.backend.service.WidgetService;
import de.leuphana.ui.navigation.NavigationManager;
import de.leuphana.ui.view.admin.AbstractCrudPresenter;
import de.leuphana.ui.view.admin.account.AccountAdminDataProvider;
import de.leuphana.ui.view.admin.account.AccountAdminView;

public class WidgetEditPresenter extends AbstractCrudPresenter<Widget, WidgetService, WidgetEditView>
implements Serializable {

@Autowired
public WidgetEditPresenter(WidgetEditDataProvider widgetEditDataProvider, NavigationManager navigationManager,
	WidgetService service, BeanFactory beanFactory) {
	super(navigationManager, service, Widget.class, widgetEditDataProvider, beanFactory);
}

@Override
protected void editItem(Widget item) {
super.editItem(item);
}
}