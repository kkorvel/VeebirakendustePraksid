package config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hsqldb.jdbc.JDBCDriver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import service.TransferService;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"service", "dao", "aop"})
@PropertySource("classpath:/application.properties")
public class Config {

    @Resource
    private Environment env;

    @Bean
    @Scope("prototype")
    public TransferService transferService() {
        return new TransferService();
    }


    @Bean
    public JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource());
    }

    private DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(JDBCDriver.class.getName());
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setUrl(env.getProperty("javax.persistence.jdbc.url"));
        return ds;
    }


    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory();
        return new JpaTransactionManager(factory);
    }


    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setPackagesToScan("model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(additionalProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");

        return properties;
    }
}