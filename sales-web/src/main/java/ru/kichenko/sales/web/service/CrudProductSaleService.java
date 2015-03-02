/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kichenko.sales.web.dto.AbstractDto;
import ru.kichenko.sales.web.dto.AbstractShortDto;
import ru.kichenko.sales.web.service.core.ServiceException;

/**
 * CRUD интерфейс сервиса для продаж и продукции
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public interface CrudProductSaleService<F extends AbstractDto, S extends AbstractShortDto> {

    F insert(F dto) throws ServiceException;

    void update(F dto) throws ServiceException;

    F getById(Long id) throws ServiceException;

    Page<F> list(Pageable pageable);

    List<F> list();

    List<S> shortList();

}
