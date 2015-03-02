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
import ru.kichenko.sales.web.dto.SaleDto;
import ru.kichenko.sales.web.service.SaleService;

/**
 * Контроллер продаж
 *
 * @author Сергей Киченко
 * @created 21.02.15 00:00
 */
@Slf4j
@Controller
@RequestMapping(value = "/sales")
public class SaleController extends AbstractController {

    @Autowired
    private SaleService service;

    @RequestMapping(method = RequestMethod.GET)
    public String sale(Model model) {
        return "sale";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ExtListPage<SaleDto> list(Pageable pageable) throws Exception {
        return new ExtListPage<>(service.list(pageable));
    }

    @ResponseBody
    @RequestMapping(value = "/list/{productId}", method = RequestMethod.GET)
    public ExtList<SaleDto> findByProductId(@PathVariable("productId") Long productId) throws Exception {
        return new ExtList<>(service.findByProductId(productId));
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Ext<SaleDto> insert(@RequestBody SaleDto dto) throws Exception {
        return new Ext<>(service.insert(dto));
    }
}
