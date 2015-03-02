/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.data.dao;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kichenko.sales.data.config.PersistenceConfig;
import ru.kichenko.sales.model.Product;
import ru.kichenko.sales.model.Sale;

/**
 * Класс для тестирования Sale DAO
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@ContextConfiguration(classes = PersistenceConfig.class)
public class SaleDaoTest extends AbstractDaoTest<SaleDao> {

    private Long productId;
    private Long saleId;

    @BeforeMethod
    public void setUp() {

        Product p = new Product("product-1", 75L, new ArrayList<Sale>(0));
        Sale s = new Sale(p, new LocalDateTime(), 50, 100L);
        em.persist(p);
        em.persist(s);

        productId = p.getId();
        saleId = s.getId();

        p = new Product("product-2", 85L, new ArrayList<Sale>(0));
        s = new Sale(p, new LocalDateTime(), 40, 134L);
        em.persist(p);
        em.persist(s);

        p = new Product("product-3", 89L, new ArrayList<Sale>(0));
        s = new Sale(p, new LocalDateTime(), 78, 34L);
        em.persist(p);
        em.persist(s);
    }

    @Test
    public void testFindByProductId() {

        List<Sale> list = dao.findByProductId(productId);

        assertNotNull(list);
        assertEquals(list.size(), 1);

        assertNotNull(list.get(0));
        assertEquals(list.get(0).getId(), saleId);
        assertEquals(list.get(0).getProduct().getId(), productId);
    }
}
