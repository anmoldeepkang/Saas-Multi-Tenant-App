package com.demo.tenant;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.demo.SpringBootWebApplication;




@EntityScan("com.demo.model")
@Configuration

public class HibernateConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name="entityManagerFactorySecondary")
    @PersistenceContext(unitName = "entityManagerFactorySecondary")
    LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(
            DataSource dataSource,
            MultiTenantConnectionProvider multiTenantConnectionProvider,
            CurrentTenantIdentifierResolver tenantIdentifierResolver
    ) {

    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    	em.setPersistenceUnitName("entityManagerFactorySecondary");
        em.setDataSource(dataSource);
        em.setPackagesToScan(SpringBootWebApplication.class.getPackage().getName());
        em.setJpaVendorAdapter(this.jpaVendorAdapter());
        
//        Map<String, Object> jpaPropertiesMap = new HashMap<>( jpaProperties.getProperties());
//        jpaPropertiesMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
//
//        em.setJpaPropertyMap(jpaPropertiesMap);

        return em;
    }
    
    
    @Bean
    @Primary
    @PersistenceContext(unitName = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            MultiTenantConnectionProvider multiTenantConnectionProvider,
            CurrentTenantIdentifierResolver tenantIdentifierResolver
    ) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(SpringBootWebApplication.class.getPackage().getName());
        em.setJpaVendorAdapter(this.jpaVendorAdapter());

        Map<String, Object> jpaPropertiesMap = new HashMap<>( jpaProperties.getProperties());
        jpaPropertiesMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
        em.setJpaPropertyMap(jpaPropertiesMap);

        return em;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager(DataSource ds,@Qualifier("entityManagerFactorySecondary") EntityManagerFactory entityManagerFactory) {
      JpaTransactionManager tm = new JpaTransactionManager();
      tm.setEntityManagerFactory(entityManagerFactory);
      tm.setDataSource(ds);
      return tm;
    }
    
   
    
}
