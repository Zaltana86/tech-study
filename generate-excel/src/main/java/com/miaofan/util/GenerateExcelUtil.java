package com.miaofan.util;


import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.miaofan.controller.GenerateExcelController;
import com.miaofan.entity.OrderBill;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Objects;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/7/29 20:49
 * Target: 生成excel文件
 */

public class GenerateExcelUtil {
    public static void generateExcel(OrderBill orderBill, HttpServletResponse response) throws IOException {
        // 模板文件
        String templateFile = Objects.requireNonNull(GenerateExcelController.class.getResource("/")).getPath() + "测试/order_template.xlsx";
        // 设置响应类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String filename = DateUtil.format(new Date(), "yyyy-MM-dd_HH_mm")+"报价单";
        filename = URLEncoder.encode(filename,"utf-8");
        // 文件名设置
        response.setHeader("content-disposition", "attachment;filename=" + filename + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel
                .write(outputStream, OrderBill.class)
                .withTemplate(templateFile).excelType(ExcelTypeEnum.XLSX).build();
        // 获取sheet
        WriteSheet sheet = EasyExcel.writerSheet().build();
        // 数据填充表格
        excelWriter.fill(orderBill, sheet);
        excelWriter.fill(orderBill.getBillItemList(), sheet);
        excelWriter.finish();
    }
}
