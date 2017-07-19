package de.leuphana.leuphalytics.connector.dbconnector.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.leuphana.leuphalytics.model.widget.Widget;

public class WidgetMapper implements RowMapper<Widget> {

	public Widget mapRow(ResultSet rs, int rowNum) throws SQLException {
		Widget widget = new Widget();
		widget.setWidgetId(rs.getInt("id"));
		widget.setWidgetName(rs.getString("name"));

		return widget;
	}
}
