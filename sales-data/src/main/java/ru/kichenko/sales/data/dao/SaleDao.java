/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.data.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kichenko.sales.model.Sale;

/**
 * Интерфейс dao для работы с продажами
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public interface SaleDao extends JpaRepository<Sale, Long> {

    @Query("select s from Sale s where s.product.id = :productId")
    List<Sale> findByProductId(@Param("productId") Long productId);
}
