package com.miaofan.controller;


import com.miaofan.entity.OrderBill;
import com.miaofan.entity.BillItem;
import com.miaofan.util.GenerateExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/7/29 20:16
 * Target:
 */

@RestController
@RequestMapping("outexcel")
public class GenerateExcelController {
    private final OrderBill orderBill = new OrderBill();

    @PostConstruct
    private void init() {
        BillItem billItem1 = new BillItem(1, "黄连花清道夫套（8次/套)", 1, 1580.0, 0.28, 884.85, null);
        BillItem billItem2 = new BillItem(1, "黄连花清道夫套（8次/套)", 1, 1580.0, 0.28, 884.8, "特批价");
        BillItem billItem3 = new BillItem(1, "黄连花清道夫套（8次/套)", 1, 1580.0, 0.28, 884.8, null);

        List<BillItem> billItemList = new ArrayList<>();
        billItemList.add(billItem1);
        billItemList.add(billItem2);
        billItemList.add(billItem3);

        orderBill.setBillId("000071");
        orderBill.setUserAddr("湖南长沙");
        orderBill.setUserName("skyler");
        orderBill.setMakeBillPerson("胡亚伟");
        orderBill.setUserTelphone("15391033165");
        orderBill.setBillItemList(billItemList);
        orderBill.setOrderDate(new Date());
        orderBill.setOrderState(1);
    }

    @GetMapping
    public void generateExcel(HttpServletResponse response) throws IOException {
        GenerateExcelUtil.generateExcel(orderBill,response);
    }
}

