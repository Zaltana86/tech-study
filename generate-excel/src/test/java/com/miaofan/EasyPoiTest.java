package com.miaofan;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.style.StyleUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote 测试easypoi
 * @since 2023/1/3 14:31
 */
@SpringBootTest
public class EasyPoiTest {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Person {
        @Excel(name = "id")
        private Integer id;
        @Excel(name = "名字")
        private String name;
        @Excel(name = "年齡")
        private Integer age;
    }

    static List<Person> people = new ArrayList<>();

    @BeforeAll
    static void init() {
        people.add(new Person(1, "张三", 18));
        people.add(new Person(2, "李四", 19));
        people.add(new Person(2, "王五", 19));
    }

    @Test
    public void testExport() throws IOException {
        final ExportParams exportParams = new ExportParams("用户导出", "配方表", ExcelType.XSSF);
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, Person.class, people);
        Sheet sheet = sheets.getSheetAt(0);
        sheet.setColumnWidth(0,100 * 256);

        ExcelWriter excelWriter = new ExcelWriter(sheet);
        CellStyle cellStyle = excelWriter.getCellStyle();
        StyleUtil.setBorder(cellStyle, BorderStyle.THIN, IndexedColors.BLACK);
        StyleUtil.setAlign(cellStyle, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);


        // 设置表头的样式
        setHeaderStyle(excelWriter);

        int lastRowNum = sheet.getLastRowNum() + 1;
        int start = -1;
        int end;
        String currentId;
        String nextId = null;
        boolean isCombine = false;

        // Map<Integer, Integer> mergeMap = new HashMap<>();
        for (int i = 2; i < lastRowNum; i++) {
            excelWriter.setRowStyleIfHasData(i, cellStyle);

            Row row = sheet.getRow(i);
            currentId = row.getCell(0).getRichStringCellValue().getString();
            System.out.println(currentId);
            if (Objects.equals(nextId, currentId)) {
                if (!isCombine) {
                    start = i - 1;
                }
                isCombine = true;
            } else {
                end = i - 1;
                if (start != -1) {
                    excelWriter.merge(start, end, 0, 0, null, false);
                    // mergeMap.put(start, end);
                }
                start = -1;
                isCombine = false;
            }
            nextId = currentId;
        }
        if (isCombine) {
            // mergeMap.put(start, lastRowNum - 1);
            excelWriter.merge(start, lastRowNum - 1, 0, 0, null, false);
        }
        // System.out.println(mergeMap);
        // // sheets.write(new FileOutputStream("d://test-file/a.xlsx"));
        excelWriter.flush(new File("d://test-file/a.xlsx"));
    }

    private void setHeaderStyle(ExcelWriter excelWriter) {
        CellStyle cellStyle = excelWriter.getHeadCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        excelWriter.setRowStyleIfHasData(0, cellStyle);
        excelWriter.setRowStyleIfHasData(1, cellStyle);
    }
}
