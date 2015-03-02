/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.core;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Типовой результат, который возвращает контроллер.
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ext<T> {

    private boolean success;

    @JsonSerialize
    private T data;

    @JsonSerialize
    private String message;

    public Ext(T data) {
        this(true, data, null);
    }

    public Ext(T data, boolean success) {
        this.data = data;
        success = true;
    }
}
