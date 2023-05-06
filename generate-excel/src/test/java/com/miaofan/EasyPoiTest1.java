package com.miaofan;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author skyler
 * @apiNote 给公司做的excel汇总导出
 * @since 2023/5/5 14:51
 */

public class EasyPoiTest1 {
    @Data
    @Accessors(chain = true)
    private static class Order {
        private Integer index;
        private String recordCode;
        private String code;
        private String date;
        private String entrustClient;
        private String producer;
        private String productName;
        private String testProject;
        private String ifComplete;
    }

    public static void main(String[] args) throws IOException {
        ExcelReader excelReader = new ExcelReader(System.getProperty("user.dir") + "/excel/汇总订单-标准化（华科-华研-景和-康正-优捷）.xlsx", 0);

        excelReader.addHeaderAlias("备案报告号", "recordCode");
        excelReader.addHeaderAlias("流通报告号", "code");
        excelReader.addHeaderAlias("排单时间", "date");
        excelReader.addHeaderAlias("样品名称", "productName");
        excelReader.addHeaderAlias("委托方", "entrustClient");
        excelReader.addHeaderAlias("生产方", "producer");
        excelReader.addHeaderAlias("检测备案全套", "ifComplete");
        excelReader.addHeaderAlias("检验项目", "testProject");

        List<Order> orderList = excelReader.read(0, 0, Order.class);
        // for (Order order : orderList) {
        //     System.out.println(order);
        // }
        Map<String, List<Order>> orderMap = orderList.stream().collect(Collectors.groupingBy(Order::getEntrustClient));
        // System.out.println(collect.get("中易美康（深圳）生物科技有限公司")); 对账单模板（以客户名称输出）
        Set<Map.Entry<String, List<Order>>> entries = orderMap.entrySet();
        TemplateExportParams templateExportParams = new TemplateExportParams(System.getProperty("user.dir") + "/excel/test.xlsx");
        for (Map.Entry<String, List<Order>> entry : entries) {
            String entrustClient = entry.getKey();
            List<Order> orders = entry.getValue();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                order.setIndex(i + 1);
                order.setDate(order.getDate().split(" ")[0]);
                String ifComplete = order.getIfComplete();
                // if (StrUtil.isBlank(ifComplete)) {
                //     order.setIfComplete("否");
                // }
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("entrustClient", entrustClient);
            resultMap.put("orders", orders);
            Workbook sheets = ExcelExportUtil.exportExcel(templateExportParams, resultMap);
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/excel/result/" + entrustClient + "-对账单.xlsx");
            sheets.write(fos);
            fos.close();
        }
    }
}
