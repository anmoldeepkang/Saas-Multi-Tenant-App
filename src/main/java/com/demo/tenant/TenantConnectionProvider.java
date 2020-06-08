package com.demo.tenant;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TenantConnectionProvider implements
		org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider {

	private static final long serialVersionUID = 1348353870772468815L;
	private static Logger logger = LoggerFactory.getLogger(TenantConnectionProvider.class);
	private String DEFAULT_TENANT = FlywayConfig.DEFAULT_SCHEMA;
	
	private DataSource datasource;
	
	ExpiringMap<String, DataSource> map=new ExpiringMap<>();
	
    
	
	public TenantConnectionProvider(DataSource dataSource) {
		this.datasource = dataSource;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {

		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier)
			throws SQLException {
		logger.debug("Get connection for tenant {}", tenantIdentifier);
		Connection connection;
		if(tenantIdentifier.equals(FlywayConfig.DEFAULT_SCHEMA)) {
			connection=getAnyConnection();
			
			connection.setSchema(FlywayConfig.DEFAULT_SCHEMA);
			connection.setCatalog(FlywayConfig.DEFAULT_SCHEMA);
			  } else { connection = getAnyConnection();
			 
			connection.setSchema(tenantIdentifier);
			connection.setCatalog(tenantIdentifier);
		}
		
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection)
			throws SQLException {
		logger.debug("Release connection for tenant {}", tenantIdentifier);
		connection.setSchema(DEFAULT_TENANT);
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;

	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

}
