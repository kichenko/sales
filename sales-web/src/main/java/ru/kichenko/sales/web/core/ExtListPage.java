/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.core;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * Результат, возвращаемый контроллером, в виде страницы списка.
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Getter
@Setter
@NoArgsConstructor
public class ExtListPage<T> extends ExtList<T> {

    /**
     * Общее число элементов списка.
     */
    private long total;

    public ExtListPage(Page<T> page) {
        super(true, page.getContent(), null);
        this.total = page.getTotalElements();
    }

}
