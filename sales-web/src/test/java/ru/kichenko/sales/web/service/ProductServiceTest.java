/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.service;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kichenko.sales.data.dao.ProductDao;
import ru.kichenko.sales.model.Product;
import ru.kichenko.sales.model.Sale;
import ru.kichenko.sales.web.dto.ProductDto;
import ru.kichenko.sales.web.dto.ProductShortDto;
import ru.kichenko.sales.web.service.core.ServiceException;

/**
 * Тесты для сервиса продукции
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public class ProductServiceTest {

    @InjectMocks
    private final ProductService service = new ProductServiceImpl();

    @Mock
    private ProductDao productDao;

    private final Product product = new Product("product", 100L, new ArrayList<Sale>(0));

    @BeforeMethod
    public void injectMocks() {

        MockitoAnnotations.initMocks(this);
        when(productDao.findAll()).thenReturn(Lists.newArrayList(product));
        when(productDao.findOne(new Long(1L))).thenReturn(product);
        when(productDao.findOne(new Long(-1L))).thenReturn(null);
    }

    @Test
    public void testListAll() {

        List<ProductShortDto> products = service.shortList();

        assertNotNull(products);
        assertEquals(products.size(), 1);

        assertNotNull(products.get(0));
        assertEquals(products.get(0).getId(), product.getId());
        assertEquals(products.get(0).getName(), product.getName());

        verify(productDao).findAll();
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUpdate() throws Exception {
        service.update(new ProductDto(-1L, "", 100L));
    }

    @Test
    public void testGetById() throws Exception {

        Long productId = 1L;
        ProductDto dto = service.getById(productId);

        assertNotNull(dto);
        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getName(), product.getName());
        assertEquals(dto.getPrice(), product.getPrice());

        verify(productDao).findOne(productId);
    }

}
