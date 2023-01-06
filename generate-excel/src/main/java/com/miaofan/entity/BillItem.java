package com.miaofan.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/7/29 14:14
 * Target: 订单商品信息
 */
@Data
@AllArgsConstructor
public class BillItem {
    private Integer id;
    private String productName;
    private Integer count;
    private Double singlePrice;
    private Double discount;
    private Double singleTotalPrice;
    private String remark;
}
