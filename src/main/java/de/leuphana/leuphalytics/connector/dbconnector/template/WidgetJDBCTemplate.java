package de.leuphana.leuphalytics.connector.dbconnector.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import de.leuphana.leuphalytics.connector.dbconnector.DAO.WidgetDAO;
import de.leuphana.leuphalytics.connector.dbconnector.mapper.WidgetMapper;
import de.leuphana.leuphalytics.model.widget.Widget;

public class WidgetJDBCTemplate implements WidgetDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Integer id, String name) {
		String SQL = "insert into Widget (id, name) values (?, ?)";
		jdbcTemplateObject.update(SQL, id, name);
		System.out.println("Created record ID = " + id + " NAME = " + name);
		return;
	}

	@Override
	public Widget getWidget(Integer id) {
		String SQL = "select * from Widget where id = ?";
		Widget widget = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new WidgetMapper());

		return widget;
	}

	@Override
	public List<Widget> listWidgets() {
		String SQL = "select * from Widget";
		List<Widget> widgets = jdbcTemplateObject.query(SQL, new WidgetMapper());
		return widgets;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from Widget where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted record with ID = " + id);
		return;
	}

	@Override
	public void update(Integer id, String name) {
		String SQL = "update widget set name = ? where id = ?";
		jdbcTemplateObject.update(SQL, name, id);
		System.out.println("Updated record with ID = " + id);
		return;
	}

}
