package io.sofastack.balance.manage.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import io.sofastack.balance.manage.facade.TotalPriceMngFacade;
import io.sofastack.balance.manage.mapper.TotalPriceMngMapper;
import io.sofastack.balance.manage.model.TotalPrice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@SofaService(interfaceType = TotalPriceMngFacade.class, uniqueId = "${service.unique.id}", bindings = { @SofaServiceBinding(bindingType = "bolt") })
public class TotalPriceMngImpl implements TotalPriceMngFacade {
    @Resource
    private TotalPriceMngMapper mapper;

    @Override
    public void insertUserPrice(BigDecimal price, String userName) {
        TotalPrice totalPrice = mapper.queryPrice(userName);
        if(null == totalPrice){
            mapper.insertUserPrice(price,userName);
        }
    }

    @Override
    public TotalPrice queryPrice(String userName) {
        TotalPrice price = mapper.queryPrice(userName);
        return price;
    }

    @Override
    public void addPrice(BigDecimal price, String userName) {
        TotalPrice totalPrice = mapper.queryPrice(userName);
        if(null != totalPrice){
            mapper.addPrice(price,userName);
        }
    }
}
