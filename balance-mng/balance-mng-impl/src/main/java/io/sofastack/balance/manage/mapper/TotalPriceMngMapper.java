package io.sofastack.balance.manage.mapper;
import	java.sql.ResultSet;

import io.sofastack.balance.manage.model.TotalPrice;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;

@Mapper
public interface TotalPriceMngMapper {
    @Insert("insert into total_price_tb (user_name,price) values(#{userName},#{price})")
    public void insertUserPrice(@Param("price") BigDecimal price, @Param("userName") String userName);

    @Select("select * from total_price_tb where user_name=#{userName}")
    @Results({
            @Result(column = "user_name",property ="userName")
    })
    public TotalPrice queryPrice(@Param("userName") String userName);

    @Update("update total_price_tb set price = price + #{price} where user_name = #{userName}")
    public void addPrice(@Param("price") BigDecimal price,@Param("userName")String userName);
}
