package de.leuphana.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.leuphana.backend.WidgetRepository;
import de.leuphana.backend.data.entity.AccountRole;
import de.leuphana.backend.data.entity.Widget;

/**
 * 
 * @author Jonas Fährmann
 *
 */
@Service
public class WidgetService extends CrudService<Widget>{

	private final WidgetRepository widgetRepository;

	@Autowired
	public WidgetService(WidgetRepository widgetRepository) {
		this.widgetRepository = widgetRepository;
	}

	
	public Widget findWidgetByName(Long id) {
		return widgetRepository.findOne(id);
	}

	public List<Widget> findAll() {
		return widgetRepository.findAll();
	}

	public Page<Widget> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}
	
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	protected WidgetRepository getRepository() {
		return widgetRepository;
	}


	public String[] findAllAsStringArray() {
		List<Widget> widgetList = widgetRepository.findAll();

		String[] widgetStringArray = new String[widgetList.size()];
		int index = 0;
		for (Widget widget : widgetList) {
			widgetStringArray[index] = widget.getName();
			index++;
		}
		
		return widgetStringArray;
	}

}
