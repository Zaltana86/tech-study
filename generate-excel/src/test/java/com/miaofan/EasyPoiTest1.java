package com.miaofan;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.poi.excel.ExcelReader;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
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
        private String remark;
    }

    public static void main(String[] args) throws IOException {
        Map<String, List<Order>> orderMap = getOrderListMap("表1.xlsx");
        Map<String, List<Order>> orderMap2 = getOrderListMap("表2.xlsx");
        Set<Map.Entry<String, List<Order>>> entries = orderMap.entrySet();
        Set<Map.Entry<String, List<Order>>> entries2 = orderMap.entrySet();
        TemplateExportParams templateExportParams = new TemplateExportParams(System.getProperty("user.dir") + "/excel/test.xlsx");
        for (Map.Entry<String, List<Order>> entry : entries) {
            String entrustClient = entry.getKey().trim();
            List<Order> orders = entry.getValue();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                order.setIndex(i + 1);
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("entrustClient", entrustClient);
            resultMap.put("orders", orders);

            if (orderMap2.containsKey(entrustClient)) {
                List<Order> toBeSort = orderMap2.get(entrustClient);
                // System.out.println(toBeSort);
                List<Order> orders2 = new ArrayList<>();
                for (Order order : orders) {
                    for (Order order2 : toBeSort) {
                        String productName = order.getProductName();
                        if (productName != null && productName.equals(order2.getProductName())) {
                            orders2.add(order2);
                        }
                    }
                }
                resultMap.put("orders2", orders2);
                // System.out.println(orders2);
            }
            Workbook sheets = ExcelExportUtil.exportExcel(templateExportParams, resultMap);
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/excel/result/" + entrustClient + "-对账单.xlsx");
            sheets.write(fos);
            fos.close();
        }

        for (Map.Entry<String, List<Order>> entry : entries2) {
            String entrustClient = entry.getKey().trim();
            List<Order> orders = entry.getValue();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                order.setIndex(i + 1);
            }
            if(!orderMap.containsKey(entrustClient)) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("entrustClient", entrustClient);
                resultMap.put("orders", orders);
                Workbook sheets = ExcelExportUtil.exportExcel(templateExportParams, resultMap);
                FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/excel/result1/" + entrustClient + "-对账单.xlsx");
                sheets.write(fos);
                fos.close();
            }
        }
    }

    private static Map<String, List<Order>> getOrderListMap(String target) {
        ExcelReader excelReader = new ExcelReader(System.getProperty("user.dir") + "/excel/" + target, 0);

        excelReader.addHeaderAlias("备案报告号", "recordCode");
        excelReader.addHeaderAlias("流通报告号", "code");
        excelReader.addHeaderAlias("下单时间", "date");
        excelReader.addHeaderAlias("产品名称", "productName");
        excelReader.addHeaderAlias("委托方", "entrustClient");
        excelReader.addHeaderAlias("生产方", "producer");
        excelReader.addHeaderAlias("备案全套", "ifComplete");
        excelReader.addHeaderAlias("检测项目", "testProject");
        excelReader.addHeaderAlias("备注", "remark");


        List<Order> orderList = excelReader.read(0, 0, Order.class);
        for (Order order : orderList) {
            order.setEntrustClient(order.getEntrustClient().trim());
        }
        Map<String, List<Order>> orderListMap = orderList.stream().collect(Collectors.groupingBy(Order::getEntrustClient));
        Map<String, List<Order>> newMap = new HashMap<>();
        for (String s : orderListMap.keySet()) {
            List<Order> orders = orderListMap.get(s);
            for (Order order : orders) {
                order.setProductName(order.getProductName().trim());
            }
            newMap.put(s.trim(), orders);
        }
        return newMap;
    }
}
