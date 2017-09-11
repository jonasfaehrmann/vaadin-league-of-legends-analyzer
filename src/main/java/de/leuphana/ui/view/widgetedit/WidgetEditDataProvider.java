package de.leuphana.ui.view.widgetedit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.data.entity.Widget;
import de.leuphana.backend.service.AccountService;
import de.leuphana.backend.service.WidgetService;

@SpringComponent
@PrototypeScope
public class WidgetEditDataProvider extends FilterablePageableDataProvider<Widget, Object> {

	private final WidgetService widgetService;

	@Autowired
	public WidgetEditDataProvider(WidgetService widgetService) {
		this.widgetService = widgetService;
	}
	
	
	@Override
	protected Page<Widget> fetchFromBackEnd(Query<Widget, Object> query, Pageable pageable) {
		return widgetService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

	@Override
	protected int sizeInBackEnd(Query<Widget, Object> query) {
		return (int) widgetService.countAnyMatching(getOptionalFilter());
	}

}
