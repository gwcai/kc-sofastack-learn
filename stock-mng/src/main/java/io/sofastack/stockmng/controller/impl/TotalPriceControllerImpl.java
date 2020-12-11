package io.sofastack.stockmng.controller.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.sofastack.balance.manage.facade.BalanceMngFacade;
import io.sofastack.balance.manage.facade.TotalPriceMngFacade;
import io.sofastack.balance.manage.model.Balance;
import io.sofastack.balance.manage.model.TotalPrice;
import io.sofastack.stockmng.controller.TotalPriceController;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

/**
 * @author FUDIAN
 */
@Controller
public class TotalPriceControllerImpl implements TotalPriceController {
    @SofaReference(interfaceType = TotalPriceMngFacade.class, uniqueId = "${service.unique.id}", binding = @SofaReferenceBinding(bindingType = "bolt"))
    private TotalPriceMngFacade priceMngFacade;

    @SofaReference(interfaceType = BalanceMngFacade.class, uniqueId = "${service.unique.id}", binding = @SofaReferenceBinding(bindingType = "bolt"))
    private BalanceMngFacade balanceMngFacade;

    @Override
    public TotalPrice queryPrice(String body) {
        JSONObject obj = JSON.parseObject(body);
        String userName = obj.getString("userName");
        return priceMngFacade.queryPrice(userName);
    }

    @Override
    public void savePrice(String body) {
        JSONObject obj = JSON.parseObject(body);
        String userName = obj.getString("userName");
        BigDecimal price = obj.getBigDecimal("price");
        priceMngFacade.insertUserPrice(price,userName);
    }

    @Override
    public void addPrice(String body) {
        JSONObject obj = JSON.parseObject(body);
        String userName = obj.getString("userName");
        BigDecimal price = obj.getBigDecimal("price");
        priceMngFacade.addPrice(price,userName);
    }

    @Override
    public String price() {
        return "price.html";
    }

    @Override
    public Balance queryBalance(String body) {
        JSONObject obj = JSON.parseObject(body);
        String userName = obj.getString("userName");
        return balanceMngFacade.query(userName);
    }
}
