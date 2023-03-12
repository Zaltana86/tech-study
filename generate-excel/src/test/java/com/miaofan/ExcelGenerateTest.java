package com.miaofan;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.style.StyleUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/1 14:11
 */

public class ExcelGenerateTest {
    @Data
    @Accessors(chain = true)
    public static class Model {
        private String title;
        private List<Item> itemList;
    }

    @Data
    @Accessors(chain = true)
    public static class Item {
        private String name1;
        private String name2;
        private String name3;
        private String name4;
    }

    public enum Names {
        NAME1, NAME2, NAME3, NAME4
    }


    @Test
    public void test1() {
        ArrayList<String> strings = new ArrayList<String>() {{
            add("name1");
            add("name2");
            add("name3");
            add("name4");
        }};

        // 准备数据
        Item item1 = new Item();
        item1.setName1("name1").setName2("name2").setName3("name3").setName4("name4");
        Item item2 = new Item();
        item2.setName1("name1").setName2("name2").setName3("name3").setName4("name4");
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item2);
        itemList.add(item2);
        Model model = new Model().setTitle("title").setItemList(itemList);

        ExcelWriter excelWriter = ExcelUtil.getWriter(true);
        // 表头
        CellStyle cellStyle = StyleUtil.setBorder(excelWriter.getCellStyle(), BorderStyle.THIN, IndexedColors.BLACK1);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Row header = excelWriter.getOrCreateRow(0);
        header.setHeight((short) 400);
        excelWriter.setRowStyleIfHasData(0, cellStyle);
        excelWriter.merge(itemList.size(), model.getTitle());
        for (int i = 0; i < Names.values().length; i++) {
            // 创建行
            Row row = excelWriter.getOrCreateRow(i + 1);
            row.setHeight((short) 400);
            // 每行的第一个格子
            Cell firstCell = row.createCell(0);
            firstCell.setCellValue(Names.values()[i].name());
            for (int j = 0; j < itemList.size(); j++) {
                // 分别设置每行的内容
                row.createCell(j + 1).setCellValue(JSONUtil.parseObj(itemList.get(j)).toJSONArray(strings).get(i).toString());
            }
            excelWriter.setRowStyleIfHasData(i + 1, cellStyle);
        }

        String path = System.getProperty("user.dir") + "/excel/";
        File file = new File(path + "testExcel.xlsx");
        excelWriter.flush(file);
    }
}
