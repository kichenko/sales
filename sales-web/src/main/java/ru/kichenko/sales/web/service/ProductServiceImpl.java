/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kichenko.sales.data.dao.ProductDao;
import ru.kichenko.sales.model.Product;
import ru.kichenko.sales.model.Sale;
import ru.kichenko.sales.web.dto.ProductDto;
import ru.kichenko.sales.web.dto.ProductShortDto;
import ru.kichenko.sales.web.service.core.ServiceException;

/**
 * Сервис продукции
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * Короткий список продукции
     *
     * @return короткий список продукции
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductShortDto> shortList() {
        return Lists.transform(productDao.findAll(), new Function<Product, ProductShortDto>() {
            @Override
            public ProductShortDto apply(Product p) {
                return new ProductShortDto(p.getId(), p.getName());
            }
        });
    }

    /**
     * Cписок продукции
     *
     * @return список продукции
     */
    @Override
    public List<ProductDto> list() {
        throw new UnsupportedOperationException();
    }

    /**
     * Постраничный список продукции
     *
     * @param pageable страница
     * @return список продукции
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> list(Pageable pageable) {
        Page<Product> page = productDao.findAll(pageable);
        return new PageImpl<>(Lists.transform(page.getContent(), new Function<Product, ProductDto>() {
            @Override
            public ProductDto apply(Product p) {
                return new ProductDto(p.getId(), p.getName(), p.getPrice());
            }
        }), pageable, page.getTotalElements());

    }

    /**
     * Получение продукта по #id
     *
     * @param id #id продукта
     * @throws ServiceException
     * @return продукт
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(Long id) throws ServiceException {
        Product p = productDao.findOne(id);
        if (p != null) {
            return new ProductDto(p.getName(), p.getPrice());
        } else {
            throw new ServiceException("Не найден продукт в БД");
        }
    }

    /**
     * Сохранение продукта
     *
     * @param dto продукт dto
     * @return dto продукт dto c #id
     * @throws ServiceException
     */
    @Override
    @Transactional
    public ProductDto insert(ProductDto dto) throws ServiceException {
        Product p = new Product(dto.getName(), dto.getPrice(), new ArrayList<Sale>(0));
        productDao.save(p);
        dto.setId(p.getId());

        return dto;
    }

    /**
     * Редактирование продукта
     *
     * @param dto продукт dto
     * @throws ServiceException
     */
    @Override
    @Transactional
    public void update(ProductDto dto) throws ServiceException {
        ProductDto prod = (ProductDto) dto;
        Product p = productDao.findOne(dto.getId());
        if (p != null) {
            p.setName(prod.getName());
            p.setPrice(prod.getPrice());
            productDao.save(p);
        } else {
            throw new ServiceException("Не найден продукт в БД");
        }
    }
}
