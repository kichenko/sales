/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Базовый класс тестов контроллеров
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
public abstract class AbstractControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ObjectMapper objectMapper;

    public final static MediaType CONTENT_TYPE_APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public byte[] convert2Json(Object object) throws IOException {
        return objectMapper.writeValueAsBytes(object);
    }
}
