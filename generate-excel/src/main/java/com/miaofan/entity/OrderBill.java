package com.miaofan.entity;


import com.miaofan.util.BillUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/7/29 14:01
 * Target: 出货单
 */
@Data
public class OrderBill {
    /**
     * 客户名
     */
    private String userName;
    /**
     * 客户电话
     */
    private String userTelphone;
    /**
     * 出货单号，自增
     */
    private String billId;
    /**
     * 用户地址
     */
    private String userAddr;
    /**
     * 出货日期
     */
    private Date orderDate;
    /**
     * 出货单具体商品
     */
    private List<BillItem> billItemList;
    /**
     * 制单人
     */
    private String makeBillPerson;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 拼音价格
     */
    private String pinyinPrice;
    /**
     * 订单状态
     */
    private Integer orderState;
    /**
     * 中文的订单状态
     */
    private String orderState2CN;

    public String getOrderState2CN() {
        if (orderState == 0) {
            return "否";
        }
        return "是";
    }

    public Double getTotalPrice() {
        return BillUtil.getTotalPrice(this.billItemList);
    }
    public String getPinyinPrice() {
        return BillUtil.getpPinyinPrice(this.billItemList);
    }
}
