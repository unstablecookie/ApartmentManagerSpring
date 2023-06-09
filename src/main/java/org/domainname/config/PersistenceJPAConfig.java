package org.domainname.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import java.util.Properties;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.domainname.entity.User;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.domainname.repository"})
public class PersistenceJPAConfig {
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(User.class.getPackage().getName());
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	      return em;
	   }

		@Bean
		public DataSource dataSource() {
		    DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName("org.postgresql.Driver");
		    dataSource.setUsername("postgres");
		    dataSource.setPassword("P@ssw0rd");
		    dataSource.setUrl("jdbc:postgresql://localhost:5432/jdncdemobd"); 
		    return dataSource;
		}
		
		@Bean
		public PlatformTransactionManager transactionManager() {
		    JpaTransactionManager transactionManager = new JpaTransactionManager();
		    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		    return transactionManager;
		}

		@Bean
		public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		    return new PersistenceExceptionTranslationPostProcessor();
		}

		Properties additionalProperties() {
		    Properties properties = new Properties();
		    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		       
		    return properties;
		}

}

