package de.leuphana.leuphalytics.connector.dbconnector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.leuphana.leuphalytics.model.widget.Widget;

@Component
public class WidgetService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Widget> findAll() {
		return jdbcTemplate.query("SELECT id, name FROM widgets",
				(rs, rowNum) -> new Widget(rs.getInt("id"), rs.getString("name")));
	}

	public void update(Widget widget) {
		jdbcTemplate.update("UPDATE widget SET name=? WHERE id=?", widget.getWidgetName(), widget.getWidgetId());
	}

}
