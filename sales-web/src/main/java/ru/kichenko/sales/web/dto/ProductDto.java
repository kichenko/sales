/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * Dto продукта
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends AbstractDto {

    protected String name;
    protected Long price;

    public ProductDto(Long id, String name, Long price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
