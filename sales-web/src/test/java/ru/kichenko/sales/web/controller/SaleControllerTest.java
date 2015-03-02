/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.controller;

import com.google.common.collect.Lists;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.joda.time.LocalDateTime;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static ru.kichenko.sales.web.controller.AbstractControllerTest.CONTENT_TYPE_APPLICATION_JSON_UTF8;
import ru.kichenko.sales.web.dto.SaleDto;
import ru.kichenko.sales.web.service.SaleService;

/**
 * Тесты контроллера продаж
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/sale-mvc-dispatcher-servlet.xml", "classpath:context-test.xml"})

public class SaleControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SaleService service;

    private final SaleDto saleDto = new SaleDto(1L, 15L, "Продукт", new LocalDateTime(), 10, 15L);

    @BeforeMethod
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testFindByProductId() throws Exception {

        when(service.findByProductId(anyLong())).thenReturn(Lists.newArrayList(saleDto));

        mockMvc.perform(get("/sales/list/{productId}", 1L)).
          andExpect(status().isOk()).
          andExpect(content().contentType(CONTENT_TYPE_APPLICATION_JSON_UTF8)).
          andExpect(jsonPath("$.success", is(true))).
          andExpect(jsonPath("$.message", nullValue())).
          andExpect(jsonPath("$.data", hasSize(1))).
          andExpect(jsonPath("$.data[0].id", is(saleDto.getId().intValue()))).
          andExpect(jsonPath("$.data[0].productId", is(saleDto.getProductId().intValue()))).
          andExpect(jsonPath("$.data[0].productName", is(saleDto.getProductName()))).
          andExpect(jsonPath("$.data[0].quantity", is(saleDto.getQuantity().intValue()))).
          andExpect(jsonPath("$.data[0].cost", is(saleDto.getCost().intValue())));

        verify(service, times(1)).findByProductId(anyLong());
    }

    @Test
    public void testInsert() throws Exception {

        mockMvc.perform(post("/sales/insert").
          contentType(CONTENT_TYPE_APPLICATION_JSON_UTF8).
          content(convert2Json(saleDto))).
          andExpect(status().isOk()).
          andExpect(content().contentType(CONTENT_TYPE_APPLICATION_JSON_UTF8)).
          andExpect(jsonPath("$.success", is(true))).
          andExpect(jsonPath("$.message", nullValue())).
          andExpect(jsonPath("$.data", nullValue()));

        verify(service, times(1)).insert(org.mockito.Mockito.any(SaleDto.class));
    }
}
