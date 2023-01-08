package com.ivx;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.ParagraphRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote 通过模板生成word
 * @since 2022/11/25 15:10
 */

public class GenerateWord {
    public static void main(String[] args) throws Exception {
        Configure configure = Configure.builder()
                .bind("table", new MyDynamicTableRenderPolicy())
                // .bind("horizontal", new HorizontalAndVerticalPolicy())
                .build();
        // 数据模型
        Map<String, Object> module = new HashMap<>();

        TableRenderData tableRenderData = new TableRenderData();
        for (int i = 0; i < 100; i++) {
            RowRenderData rowRenderData = new RowRenderData();
            // rowRenderData.setCells();
            // CellUtil.
            tableRenderData.addRow(rowRenderData);
            // list1.add(new Temp(i + "", i * 100 + ""));
            List<CellRenderData> cellRenderDataList = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                CellRenderData cellRenderData = new CellRenderData();
                ParagraphRenderData paragraphRenderData = new ParagraphRenderData();
                if (i == 0) {
                    paragraphRenderData.addText("標題");
                } else {
                    paragraphRenderData.addText("test");
                }
                cellRenderData.addParagraph(paragraphRenderData);
                cellRenderDataList.add(cellRenderData);
            }
            rowRenderData.setCells(cellRenderDataList);
        }
        module.put("table", tableRenderData);
        String filePath = System.getProperty("user.dir") + "/resource/";
        XWPFTemplate template = XWPFTemplate.compile(filePath + "template.docx", configure)
                .render(module);
        // 输出文档
        template.writeAndClose(new FileOutputStream(filePath + "output.docx"));
    }
}
