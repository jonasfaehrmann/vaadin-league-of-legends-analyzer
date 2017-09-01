package de.leuphana.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.backend.data.entity.AccountRole;
import de.leuphana.backend.data.entity.Widget;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public interface WidgetRepository extends JpaRepository<Widget, Long> {

	Page<Widget> findByNameLikeIgnoreCase(String name, Pageable page);

	long countByNameLikeIgnoreCase(String name);

}
