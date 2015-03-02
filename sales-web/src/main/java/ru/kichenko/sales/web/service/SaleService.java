/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.service;

import java.util.List;
import ru.kichenko.sales.web.dto.SaleDto;
import ru.kichenko.sales.web.dto.SaleShortDto;

/**
 * Интерфейс продаж
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public interface SaleService extends CrudProductSaleService<SaleDto, SaleShortDto> {

    List<SaleDto> findByProductId(Long productId);
}
