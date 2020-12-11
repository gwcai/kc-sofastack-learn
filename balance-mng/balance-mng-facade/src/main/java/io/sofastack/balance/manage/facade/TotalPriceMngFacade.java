package io.sofastack.balance.manage.facade;

import io.sofastack.balance.manage.model.TotalPrice;

import java.math.BigDecimal;

/**
 * @author FUDIAN
 */
public interface TotalPriceMngFacade {
    /***
     * 插入用户总价
     * @param price
     * @param userName
     */
    public void insertUserPrice(BigDecimal price,String userName);

    /***
     * 查询用户总价
     * @param userName
     * @return
     */
    public TotalPrice queryPrice(String userName);

    /***
     * 增加价格
     * @param price
     * @param userName
     * @return
     */
    public void addPrice(BigDecimal price,String userName);
}
