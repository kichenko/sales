/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.controller;

import com.google.common.collect.Lists;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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
import ru.kichenko.sales.web.dto.ProductDto;
import ru.kichenko.sales.web.dto.ProductShortDto;
import ru.kichenko.sales.web.service.ProductService;

/**
 * Тесты контроллера продукции
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/sale-mvc-dispatcher-servlet.xml", "classpath:context-test.xml"})
public class ProductControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ProductService service;

    private final ProductDto productDto = new ProductDto(1L, "productDto", 50L);
    private final ProductShortDto productShortDto = new ProductShortDto(1L, "productDto");

    @BeforeMethod
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testListAll() throws Exception {

        when(service.shortList()).thenReturn(Lists.newArrayList(productShortDto));

        mockMvc.perform(get("/products/list/all")).
          andExpect(status().isOk()).
          andExpect(content().contentType(CONTENT_TYPE_APPLICATION_JSON_UTF8)).
          andExpect(jsonPath("$.success", is(true))).
          andExpect(jsonPath("$.message", nullValue())).
          andExpect(jsonPath("$.data", hasSize(1))).
          andExpect(jsonPath("$.data[0].id", is(productShortDto.getId().intValue()))).
          andExpect(jsonPath("$.data[0].name", is(productShortDto.getName())));

        verify(service, times(1)).shortList();
    }

    @Test
    public void testInsert() throws Exception {

        mockMvc.perform(post("/products/insert").
          contentType(CONTENT_TYPE_APPLICATION_JSON_UTF8).
          content(convert2Json(productDto))).
          andExpect(status().isOk()).
          andExpect(content().contentType(CONTENT_TYPE_APPLICATION_JSON_UTF8)).
          andExpect(jsonPath("$.success", is(true))).
          andExpect(jsonPath("$.message", nullValue())).
          andExpect(jsonPath("$.data", nullValue()));

        verify(service, times(1)).insert(org.mockito.Mockito.any(ProductDto.class));
    }

}
