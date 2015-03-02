/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

/**
 * Глобальная настройка jakson'а
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Description("Конфигурация JSON-маппера Jackson")
@Configuration
public class JacksonConfig {

    @Bean
    @Description("Маппер объектов Jackson с глобальными настройками")
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        return mapper;
    }
}
