package com.miaofan;


import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.miaofan.entity.OrderBill;
import com.miaofan.entity.BillItem;
import com.miaofan.controller.GenerateExcelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/7/29 13:54
 * Target:
 */

public class ExcelTest {
    private final OrderBill orderBill = new OrderBill();

    @BeforeEach  // 在每个方法执行之前执行
    public void init() {
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

    @Test
    public void testGeneratExcel() {
        // 模板文件
        String templateFile = Objects.requireNonNull(GenerateExcelController.class.getResource("/")).getPath() + "order_template.xlsx";
        // 结果文件，省去了根据模板文件生成的步骤
        String format = DateUtil.format(new Date(), "yyyy-MM-dd_HH_mm");
        String resultFile = Objects.requireNonNull(GenerateExcelController.class.getResource("/")).getPath() + format + ".xlsx";
        // 根据模板文件生成目标文件
        ExcelWriter excelWriter = EasyExcel
                .write(resultFile, OrderBill.class)
                .withTemplate(templateFile).excelType(ExcelTypeEnum.XLSX).build();

        WriteSheet sheet = EasyExcel.writerSheet().build();

        excelWriter.fill(orderBill, sheet);
        excelWriter.fill(orderBill.getBillItemList(), sheet);
        excelWriter.finish();
    }
}
