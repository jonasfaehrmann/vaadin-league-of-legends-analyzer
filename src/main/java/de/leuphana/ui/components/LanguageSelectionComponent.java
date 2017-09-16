package de.leuphana.ui.components;

import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@PrototypeScope
public class LanguageSelectionComponent extends MenuBar {

	public LanguageSelectionComponent() {
		setSizeFull();

		final String language1 = "Deutsch";
		final String language2 = "English";

		Command command = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				String text = selectedItem.getText();

				switch (text) {
				case language1:
					System.out.println(language1);
					break;
				case language2:
					System.out.println(language2);
					break;
				default:
					break;
				}
			}

		};

		MenuItem de = addItem(language1, command);
		MenuItem en = addItem(language2, command);

		System.out.println("added menu");
	}

}
