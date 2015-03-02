/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.management.RuntimeErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kichenko.sales.web.core.Ext;

/**
 * Базовый класс контроллеров
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Slf4j
public abstract class AbstractController {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Ext<String> handleException(Exception ex) {

        log.error("Необработанная ошибка в контроллере", ex);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(new StringWriter());
        ex.printStackTrace(pw);

        return new Ext<>(false, sw.toString(), ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeErrorException.class)
    public Ext<String> handleRuntimeException(Exception ex) {

        log.error("Необработанная runtime-ошибка в контроллере", ex);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(new StringWriter());
        ex.printStackTrace(pw);

        return new Ext<>(false, sw.toString(), ex.getMessage());
    }
}
