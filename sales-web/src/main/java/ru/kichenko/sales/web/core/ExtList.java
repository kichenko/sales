/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.core;

import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Результат, возвращаемый контроллером, в виде списка.
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@NoArgsConstructor
public class ExtList<T> extends Ext<List<T>> {

    public ExtList(List<T> data) {
        super(true, data, null);
    }

    public ExtList(boolean success, List<T> data) {
        super(success, data, null);
    }

    public ExtList(boolean success, List<T> data, String message) {
        super(success, data, message);
    }
}
