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
        private String recordCodeList;
        private String codeList;
        private String remarkList;
        private String testProjectList;
    }

    public static void main(String[] args) throws IOException {
        Map<String, List<Order>> orderMap = getOrderListMap("表1.xlsx");
        Map<String, List<Order>> orderMap2 = getOrderListMap("表2.xlsx");
        // System.out.println(orderMap);
        // System.out.println("===================");
        // System.out.println(orderMap2);
        Set<Map.Entry<String, List<Order>>> entries = orderMap.entrySet();
        Set<Map.Entry<String, List<Order>>> entries2 = orderMap2.entrySet();
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
                    // for (Order order2 : toBeSort) {
                    //     String productName = order.getProductName();
                    //     if (productName != null && productName.equals(order2.getProductName())) {
                    //         orders2.add(order2);
                    //     }
                    // }
                    String productName = order.getProductName();

                    List<Order> collect = toBeSort.stream().filter(o -> o.getProductName() != null && o.getProductName().equals(productName)).collect(Collectors.toList());
                    String recordCodeList = StrUtil.join("-", collect.stream().map(Order::getRecordCode).collect(Collectors.toList()));
                    String codeList = StrUtil.join("-", collect.stream().map(Order::getCode).collect(Collectors.toList()));
                    String remarkList = StrUtil.join("-", collect.stream().map(Order::getRemark).collect(Collectors.toList()));
                    String testProjectList = StrUtil.join("-", collect.stream().map(Order::getTestProject).collect(Collectors.toList()));
                    order.setRecordCodeList(recordCodeList);
                    order.setCodeList(codeList);
                    order.setRemarkList(remarkList);
                    order.setTestProjectList(testProjectList);
                    orders2.add(order);
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
            if (!orderMap.containsKey(entrustClient)) {
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
        Map<String, List<Order>> orderListMap = orderList.stream().collect(Collectors.groupingBy(Order::getEntrustClient));
        Map<String, List<Order>> newMap = new HashMap<>();
        for (String s : orderListMap.keySet()) {
            List<Order> orders = orderListMap.get(s);
            for (Order order : orders) {
                String entrustClient = order.getEntrustClient();
                order.setEntrustClient(trimStr(entrustClient));

                String productName = order.getProductName();
                order.setProductName(trimStr(productName));

                String recordCode = order.getRecordCode();
                order.setRecordCode(trimStr(recordCode));

                String code = order.getCode();
                order.setCode(trimStr(code));

                String producer = order.getProducer();
                order.setProducer(trimStr(producer));

                String testProject = order.getTestProject();
                order.setTestProject(trimStr(testProject));

                String remark = order.getRemark();
                order.setRemark(trimStr(remark));
            }
            newMap.put(s.trim(), orders);
        }
        return newMap;
    }

    private static String trimStr(String str) {
        if (StrUtil.isNotBlank(str)) {
            return str.trim();
        }
        return null;
    }
}
