/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kichenko.sales.data.dao.ProductDao;
import ru.kichenko.sales.data.dao.SaleDao;
import ru.kichenko.sales.model.Product;
import ru.kichenko.sales.model.Sale;
import ru.kichenko.sales.web.dto.SaleDto;
import ru.kichenko.sales.web.dto.SaleShortDto;
import ru.kichenko.sales.web.service.core.ServiceException;

/**
 * Сервис продаж
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private ProductDao productDao;

    /**
     * Короткий список продаж
     *
     * @return короткий список продаж
     */
    @Override
    public List<SaleShortDto> shortList() {
        throw new UnsupportedOperationException();
    }

    /**
     * Cписок продаж
     *
     * @return список продаж
     */
    @Override
    public List<SaleDto> list() {
        throw new UnsupportedOperationException();
    }

    /**
     * Получение продажи по #id
     *
     * @param id #id продажи
     * @throws ServiceException
     * @return продажа
     */
    @Override
    public SaleDto getById(Long id) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    /**
     * Редактирование продажи
     *
     * @param dto продажа dto
     * @throws ServiceException
     */
    @Override
    public void update(SaleDto dto) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    /**
     * Постраничный список продаж
     *
     * @param pageable страница
     * @return список продаж
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SaleDto> list(Pageable pageable) {
        Page<Sale> page = saleDao.findAll(pageable);
        return new PageImpl<>(Lists.transform(page.getContent(), new Function<Sale, SaleDto>() {
            @Override
            public SaleDto apply(Sale s) {
                return new SaleDto(s.getId(), s.getProduct().getId(), s.getProduct().getName(), s.getDate(), s.getQuantity(), s.getCost());
            }
        }), pageable, page.getTotalElements());
    }

    /**
     * Добавление продажи
     *
     * @param dto продажа dto
     * @return dto продажа dto с #id
     * @throws ServiceException
     */
    @Override
    @Transactional
    public SaleDto insert(SaleDto dto) throws ServiceException {
        Product p = productDao.findOne(dto.getProductId());
        if (p != null) {
            Long cost = p.getPrice() * dto.getQuantity();

            Sale s = new Sale(p, dto.getDate(), dto.getQuantity(), cost);
            saleDao.save(s);

            dto.setId(s.getId());
            dto.setCost(cost);
            dto.setProductName(p.getName());
        } else {
            throw new ServiceException("Не найден продукт в БД");
        }

        return dto;
    }

    /**
     * Поиск продажи по #id продукта
     *
     * @param productId #id продукта
     * @return список продаж
     */
    @Override
    @Transactional(readOnly = true)
    public List<SaleDto> findByProductId(Long productId) {
        return Lists.transform(saleDao.findByProductId(productId), new Function<Sale, SaleDto>() {
            @Override
            public SaleDto apply(Sale s) {
                return new SaleDto(s.getId(), s.getProduct().getId(), s.getProduct().getName(), s.getDate(), s.getQuantity(), s.getCost());
            }
        });
    }
}
