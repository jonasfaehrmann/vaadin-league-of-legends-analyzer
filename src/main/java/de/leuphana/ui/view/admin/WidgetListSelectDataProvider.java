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
import de.leuphana.backend.data.widget.Widget;
import de.leuphana.backend.service.WidgetService;
import de.leuphana.backend.service.account.AccountRoleService;

@SpringComponent
public class WidgetListSelectDataProvider extends PageableDataProvider<Widget, String>{
	
	private final WidgetService widgetService;

	@Autowired
	public WidgetListSelectDataProvider(WidgetService widgetService) {
		this.widgetService = widgetService;
	}

	protected String[] fetchFromBackEnd() {
		return widgetService.findAllAsStringArray();
	}

	@Override
	protected Page<Widget> fetchFromBackEnd(Query<Widget, String> query, Pageable pageable) {
		return widgetService.findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Widget, String> query) {
		return (int) widgetService.countAnyMatching(query.getFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}
}
