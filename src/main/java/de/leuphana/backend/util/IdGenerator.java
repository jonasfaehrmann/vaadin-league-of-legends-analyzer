package de.leuphana.backend.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import de.leuphana.backend.data.entity.AbstractEntity;

/**
 * 
 * @author Jonas Fährmann
 *
 */
public class IdGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		
		Connection connection = session.connection();
		
		try {

	        PreparedStatement preparedStatement = connection
	                .prepareStatement("SELECT MAX(id) as id from " + object.getClass().getAnnotation(Table.class).name());

	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            Long id = resultSet.getLong("id");
	            System.out.println("Generated custom id for " + object.getClass().getAnnotation(Table.class).name() + " : " + (id+1l));
	            return id+1l;
	        }

	    } catch (SQLException exception) {       
	        exception.printStackTrace();
	    }
		return null;
	}

}
