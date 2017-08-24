package de.leuphana.ui.view.orderedit;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import de.leuphana.ui.view.orderedit.OrderHistory;
import de.leuphana.ui.view.orderedit.OrderStateSelect;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class OrderEditViewDesign extends VerticalLayout {
	protected HorizontalLayout reportHeader;
	protected Label orderId;
	protected Label stateLabel;
	protected OrderStateSelect state;
	protected DateField dueDate;
	protected ComboBox<java.time.LocalTime> dueTime;
	@PropertyId("customer.fullName")
	protected TextField fullName;
	@PropertyId("customer.phoneNumber")
	protected TextField phone;
	@PropertyId("customer.details")
	protected TextField details;
	protected CssLayout productInfoContainer;
	protected Button addItems;
	protected Label total;
	protected OrderHistory history;
	protected Button cancel;
	protected Button ok;

	public OrderEditViewDesign() {
		Design.read(this);
	}
}
