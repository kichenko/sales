/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.data.config;

import com.googlecode.flyway.core.Flyway;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Конфигурация для доступа к БД, транзакций, ORM-маппинга.
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.kichenko.sales.data.dao")
public class PersistenceConfig {

    /**
     * Источник данных определяется в другой конфигурации.
     */
    @Autowired
    private DataSource dataSource;

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPersistenceUnitName("sales-data");
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setDataSource(dataSource);

        factoryBean.setMappingResources("Product.hbm.xml", "Sale.hbm.xml");

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(AvailableSettings.DIALECT, PostgreSQL82Dialect.class.getName());
        jpaProperties.setProperty(AvailableSettings.HBM2DDL_AUTO, "validate");
        jpaProperties.setProperty(AvailableSettings.RELEASE_CONNECTIONS, "after_transaction");
        jpaProperties.setProperty(AvailableSettings.SHOW_SQL, "true");
        jpaProperties.setProperty(AvailableSettings.FORMAT_SQL, "true");
        jpaProperties.setProperty(AvailableSettings.USE_SQL_COMMENTS, "true");
        jpaProperties.setProperty(AvailableSettings.STATEMENT_FETCH_SIZE, "50");
        jpaProperties.setProperty(org.hibernate.jpa.AvailableSettings.NAMING_STRATEGY, ImprovedNamingStrategy.class.getName());
        jpaProperties.setProperty("jadira.usertype.autoRegisterUserTypes", "true");

        factoryBean.setJpaProperties(jpaProperties);

        /**
         * Выполняем миграции при старте приложения
         */
        Flyway flyway = new Flyway();

        flyway.setDataSource(dataSource);
        flyway.migrate();

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
