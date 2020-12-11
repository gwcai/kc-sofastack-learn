package io.sofastack.balance.manage.model;
import	java.math.BigDecimal;

/***
 * 买书所花费用
 * @author FUDIAN
 */
public class TotalPrice {
    private int id;
    private String userName;
    private BigDecimal price = new BigDecimal(0);

//    public TotalPrice addPrice(BigDecimal price){
//        this.price.add(price);
//        return this;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
