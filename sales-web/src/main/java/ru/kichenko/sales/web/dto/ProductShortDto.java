/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto продукта (короткий)
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductShortDto extends AbstractShortDto {

    protected String name;

    public ProductShortDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
