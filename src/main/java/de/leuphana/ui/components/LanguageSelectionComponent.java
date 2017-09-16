package de.leuphana.ui.components;

import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.MenuBar;

import de.leuphana.backend.service.RiotService;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;

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
					RiotService.setLocale(Locale.DE_DE, text);
					break;
				case language2:
					RiotService.setLocale(Locale.EN_US, text);
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
