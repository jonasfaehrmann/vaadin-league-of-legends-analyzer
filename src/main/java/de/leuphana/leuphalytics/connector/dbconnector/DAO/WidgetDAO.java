package de.leuphana.leuphalytics.connector.dbconnector.DAO;

import java.util.List;

import javax.sql.DataSource;

import de.leuphana.leuphalytics.model.widget.Widget;

public interface WidgetDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Student table.
	 */
	public void create(String name, Integer age);

	/**
	 * This is the method to be used to list down a record from the Student
	 * table corresponding to a passed student id.
	 */
	public Widget getWidget(Integer id);

	/**
	 * This is the method to be used to list down all the records from the
	 * Student table.
	 */
	public List<Widget> listWidgets();

	/**
	 * This is the method to be used to delete a record from the Student table
	 * corresponding to a passed student id.
	 */
	public void delete(Integer id);

	/**
	 * This is the method to be used to update a record into the Student table.
	 */
	public void update(Integer id, Integer age);
}
