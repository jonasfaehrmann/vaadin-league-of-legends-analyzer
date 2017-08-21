package de.leuphana.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.leuphana.backend.data.entity.widget.Widget;

@Component
public class WidgetService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Widget> findAll() {
		return jdbcTemplate.query("SELECT ID, NAME FROM WIDGET",
				(rs, rowNum) -> new Widget(rs.getInt("ID"), rs.getString("NAME")));
	}

	public void update(Widget widget) {
		jdbcTemplate.update("UPDATE widget SET name=? WHERE id=?", widget.getWidgetName(), widget.getWidgetId());
	}
}
