/*
 * (c) Сергей Киченко, 2015. Все права защищены.
 */
package ru.kichenko.sales.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kichenko.sales.web.core.Ext;
import ru.kichenko.sales.web.core.ExtList;
import ru.kichenko.sales.web.core.ExtListPage;
import ru.kichenko.sales.web.dto.ProductDto;
import ru.kichenko.sales.web.dto.ProductShortDto;
import ru.kichenko.sales.web.service.ProductService;

/**
 * Контроллер продукции
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Slf4j
@Controller
@RequestMapping(value = "/products")
public class ProductController extends AbstractController {

    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public String product(Model model) {
        return "product";
    }

    @ResponseBody
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public ExtList<ProductShortDto> list() throws Exception {
        return new ExtList<>(service.shortList());
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ExtListPage<ProductDto> list(Pageable pageable) throws Exception {
        return new ExtListPage<>(service.list(pageable));
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Ext<ProductDto> getById(@PathVariable("id") Long id) throws Exception {
        return new Ext<>(service.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Ext<ProductDto> insert(@RequestBody ProductDto dto) throws Exception {
        return new Ext<>(service.insert(dto));
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Ext<Void> update(@RequestBody ProductDto dto) throws Exception {
        service.update(dto);
        return new Ext<>(null);
    }
}
