package de.leuphana.ui.view.user.widget;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.board.Row;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import de.leuphana.app.security.SecurityUtils;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.data.entity.Widget;
import de.leuphana.backend.service.AccountService;
import de.leuphana.backend.service.WidgetService;
import de.leuphana.ui.navigation.NavigationManager;

@SpringView
public class UserWidgetView extends UserWidgetViewDesign implements View {

	private final NavigationManager navigationManager;
	private final AccountService accountService;
	private final WidgetService widgetService;

	private Account account;

	private Row row;

	@Autowired
	public UserWidgetView(NavigationManager navigationManager, AccountService accountService,
			WidgetService widgetService) {
		this.navigationManager = navigationManager;
		this.accountService = accountService;
		this.widgetService = widgetService;
		this.account = SecurityUtils.getCurrentUser(accountService);
	}

	@PostConstruct
	public void init() {

		initWidgetList();
	}

	private void initWidgetList() {
		Set<Widget> accountWidgets = account.getWidgets();

		for (Widget widget : widgetService.findAll()) {
			if (accountWidgets.stream().anyMatch(accountWidget -> accountWidget.getId() == widget.getId())) {
				row = board.addRow(new Label(widget.getName()), initButton("remove", widget.getId()));
				row.addStyleName("board-row-group");
			} else {
				row = board.addRow(new Label(widget.getName()), initButton("add", widget.getId()));
				row.addStyleName("board-row-group");
			}
		}
	}

	private Button initButton(String string, Long id) {
		Button button = null;
		String idAsString = String.valueOf(id);

		switch (string) {
		case "add":
			button = new Button("Add");
			button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			break;
		case "remove":
			button = new Button("Remove");
			button.setStyleName(ValoTheme.BUTTON_DANGER);
			break;
		default:
			break;
		}

		button.setId(idAsString);
		
		addButtonClickListener(button);

		return button;
	}
	
	private void addButtonClickListener(Button button){
		
//		Update or remove Widget on click
		button.addClickListener(event -> {
			Set<Widget> accountWidgets = account.getWidgets();
			Widget widget = widgetService.findWidgetByName(Long.valueOf(event.getButton().getId()));

			switch (event.getButton().getCaption()) {
			case "Add":
				accountWidgets.add(widget);
				break;
			case "Remove":
				for (Iterator<Widget> iterator = accountWidgets.iterator(); iterator.hasNext();) {
					Widget w = iterator.next();
					if (widget.getId() == w.getId()) {
						iterator.remove();
					}
				}
				break;
			default:
				break;
			}
			accountService.changeWidgets(account, accountWidgets);
			
//			Switch appearance of button on click
			if(button.getCaption().equals("Add")){
				button.setCaption("Remove");
				button.setStyleName(ValoTheme.BUTTON_DANGER);
			}else if(button.getCaption().equals("Remove")){
				button.setCaption("Add");
				button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			}
			
//			refresh account after update
			account = SecurityUtils.getCurrentUser(accountService);
		});
	}
}
