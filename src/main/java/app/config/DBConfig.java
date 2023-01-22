package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("app")
public class DBConfig {
   private final Environment env;

   @Autowired
   public DBConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, Environment env) {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(dataSource);
      entityManagerFactoryBean.setPackagesToScan("app.model");
      entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

      Properties properties = new Properties();

      properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
      properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
      properties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getRequiredProperty("hibernate.temp.use_jdbc_metadata_defaults"));
      properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
      properties.put("hibernate.use_sql_comments", env.getRequiredProperty("hibernate.use_sql_comments"));
      entityManagerFactoryBean.setJpaProperties(properties);
      return entityManagerFactoryBean;
   }

   @Bean
   JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory);
      return transactionManager;
   }
}
