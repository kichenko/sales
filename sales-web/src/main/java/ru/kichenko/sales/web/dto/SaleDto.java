/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDateTime;

/**
 * Dto продаж
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto extends AbstractDto {

    protected Long productId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDateTime date;
    protected String productName;
    protected Integer quantity;
    protected Long cost;

    public SaleDto(Long id, Long productId, String productName, LocalDateTime date, Integer quantity, Long cost) {
        super(id);
        this.productId = productId;
        this.productName = productName;
        this.date = date;
        this.quantity = quantity;
        this.cost = cost;
    }
}
