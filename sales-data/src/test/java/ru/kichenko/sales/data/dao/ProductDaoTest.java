/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.data.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import ru.kichenko.sales.data.config.PersistenceConfig;
import ru.kichenko.sales.model.Product;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.BeforeMethod;
import ru.kichenko.sales.model.Sale;

/**
 * Класс для тестирования Product DAO
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@ContextConfiguration(classes = PersistenceConfig.class)
public class ProductDaoTest extends AbstractDaoTest<ProductDao> {

    @BeforeMethod
    public void setUp() {
        em.persist(new Product("product-1", 75L, new ArrayList<Sale>(0)));
    }

    @Test
    public void testFindAll() {
        List<Product> list = dao.findAll();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        assertNotNull(list.get(0));
        assertEquals(list.get(0).getName(), "product-1");
        assertEquals(list.get(0).getPrice(), new Long(75L));
    }
}
