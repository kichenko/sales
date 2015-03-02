/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import ru.kichenko.sales.data.config.TestDataSourceConfig;

/**
 * Абстрактный класс для тестирования DAO
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@ContextConfiguration(classes = TestDataSourceConfig.class)
public abstract class AbstractDaoTest<T> extends AbstractTransactionalTestNGSpringContextTests {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected T dao;

    @BeforeMethod
    protected void clearDatabase() {
        this.deleteFromTables("product", "sale");
    }
}
