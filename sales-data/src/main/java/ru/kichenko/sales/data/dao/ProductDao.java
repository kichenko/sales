/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kichenko.sales.model.Product;

/**
 * Интерфейс dao для работы с продуктами
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public interface ProductDao extends JpaRepository<Product, Long> {

}
