/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.service;

import ru.kichenko.sales.web.dto.ProductDto;
import ru.kichenko.sales.web.dto.ProductShortDto;

/**
 * Интерфейс продукции
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public interface ProductService extends CrudProductSaleService<ProductDto, ProductShortDto> {
}
