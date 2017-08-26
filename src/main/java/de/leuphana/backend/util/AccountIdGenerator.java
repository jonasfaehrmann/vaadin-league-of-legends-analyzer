package de.leuphana.backend.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AccountIdGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		
		Connection connection = session.connection();
		
		try {

	        PreparedStatement ps = connection
	                .prepareStatement("SELECT MAX(id) as id from account");

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            Long id = rs.getLong("id");
	            return id+1;
	        }

	    } catch (SQLException e) {       
	        e.printStackTrace();
	    }
		return null;
	}

}
