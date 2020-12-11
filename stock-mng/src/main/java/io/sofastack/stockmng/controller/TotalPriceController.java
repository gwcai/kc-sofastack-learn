package io.sofastack.stockmng.controller;

import io.sofastack.balance.manage.model.Balance;
import io.sofastack.balance.manage.model.TotalPrice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/price")
public interface TotalPriceController {
    @RequestMapping("/query")
    @ResponseBody
    public TotalPrice queryPrice(@RequestBody String body);

    @RequestMapping("/save")
    public void savePrice(@RequestBody String body);

    @RequestMapping("/add")
    public void addPrice(@RequestBody String body);

    @RequestMapping("/index")
    public String price();

    @RequestMapping("/balance")
    @ResponseBody
    public Balance queryBalance(@RequestBody String body);
}
