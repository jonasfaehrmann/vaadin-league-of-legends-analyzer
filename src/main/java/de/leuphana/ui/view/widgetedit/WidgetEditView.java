package de.leuphana.ui.view.widgetedit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import de.leuphana.backend.data.entity.Widget;
import de.leuphana.ui.view.admin.AbstractCrudPresenter;
import de.leuphana.ui.view.admin.AbstractCrudView;

public class WidgetEditView extends AbstractCrudView<Widget>{

	@Override
	protected AbstractCrudPresenter<Widget, ?, ? extends AbstractCrudView<Widget>> getPresenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Grid<Widget> getGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setGrid(Grid<Widget> grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Component getForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Button getAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Button getCancel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Button getDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Button getUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TextField getSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Focusable getFirstFormField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Widget> binder) {
		// TODO Auto-generated method stub
		
	}
	

}
