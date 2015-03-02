/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.model;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Продукт
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractBaseEntity {

    protected String name;
    protected Long price;

    protected Collection<Sale> sales = new ArrayList<>(0);

    public Product(Long id, String name, Long price, Collection<Sale> sales) {
        super(id);
        this.name = name;
        this.price = price;
        this.sales = sales;

    }
}
