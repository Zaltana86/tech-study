package com.ivx;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.ParagraphRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.xwpf.NiceXWPFDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.junit.Test;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote 通过模板生成word
 * @since 2022/11/25 15:10
 */

public class GenerateWord {
    public static void main(String[] args) throws Exception {
        MyDynamicTableRenderPolicy myDynamicTableRenderPolicy = new MyDynamicTableRenderPolicy();
        Configure configure = Configure.builder()
                .bind("table0", myDynamicTableRenderPolicy)
                .bind("table1", myDynamicTableRenderPolicy)
                .bind("table2", myDynamicTableRenderPolicy)
                .bind("table3", myDynamicTableRenderPolicy)
                // .bind("horizontal", new HorizontalAndVerticalPolicy())
                .build();
        // 数据模型
        Map<String, Object> module = new HashMap<>();
        TableRenderData tableRenderData = new TableRenderData();
        for (int i = 0; i < 5; i++) {
            RowRenderData rowRenderData = new RowRenderData();
            // rowRenderData.setCells();
            // CellUtil.
            // list1.add(new Temp(i + "", i * 100 + ""));
            List<CellRenderData> cellRenderDataList = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                ParagraphRenderData paragraphRenderData = new ParagraphRenderData();
                paragraphRenderData.addText("test");
                CellRenderData cellRenderData = new CellRenderData();
                cellRenderData.addParagraph(paragraphRenderData);
                cellRenderDataList.add(cellRenderData);
            }
            rowRenderData.setCells(cellRenderDataList);
            tableRenderData.addRow(rowRenderData);
        }
        // 填充表头
        fillTableHead(tableRenderData);

        module.put("table0", tableRenderData);
        module.put("table1", tableRenderData);
        module.put("table2", tableRenderData);
        module.put("table3", tableRenderData);
        module.put("title0", "标题1");
        module.put("title1", "标题2");
        module.put("title2", "标题3");
        module.put("title3", "标题4");

        String filePath = System.getProperty("user.dir") + "/resource/";
        XWPFTemplate template0 = XWPFTemplate.compile(filePath + "template.docx");

        // 模板变量初始化
        NiceXWPFDocument xwpfDocument = template0.getXWPFDocument();
        List<List<String>> runTextList = new ArrayList<>();
        List<String> runTexts = new ArrayList<>();
        runTexts.add("table0");
        runTexts.add("table1");
        runTexts.add("table2");
        runTexts.add("table3");
        runTextList.add(runTexts);
        initTempalte(xwpfDocument, runTextList);


        XWPFTemplate template1 = XWPFTemplate.compile(xwpfDocument, configure);
        // 10mb
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 1024 * 10);

        template1.render(module, byteArrayOutputStream);
        // template1.write(byteArrayOutputStream);
        byteArrayOutputStream.writeTo(new FileOutputStream(filePath + "output.docx"));
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // NiceXWPFDocument xwpfDocument1 = template1.getXWPFDocument();
        // 输出文档
        AddStamp.creatSealDocument(byteArrayInputStream);
        // template1.writeAndClose(new FileOutputStream(filePath + "output.docx"));
    }

    private static void initTempalte(XWPFDocument xwpfDocument, List<List<String>> runTextList) throws IOException {
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();//获得word中段落
        System.out.println(paragraphs.size());
        Map<XmlCursor, String> cursorRunList = new HashMap<>();
        for (List<String> runTexts : runTextList) {
            for (String runText : runTexts) {
                for (XWPFParagraph xwpfParagraph : paragraphs) {
                    List<XWPFRun> runs = xwpfParagraph.getRuns();
                    if (CollUtil.isEmpty(runs)) {
                        continue;
                    }
                    XWPFRun xwpfRun = runs.get(0);
                    String text = xwpfRun.getText(0);
                    text = stripBracket(text);
                    if (isSimilarTable(runText, text)) {
                        // 獲取當前游標
                        XmlCursor cursor = xwpfParagraph.getCTP().newCursor();
                        cursorRunList.put(cursor, addBracket(runText));
                    }
                }
            }
        }
        // 保证表格顺序
        ArrayList<Map.Entry<XmlCursor, String>> entries = new ArrayList<>(cursorRunList.entrySet());
        entries.sort((o1, o2) -> {
            String value1 = o1.getValue();
            String value2 = o2.getValue();
            return value2.compareTo(value1);
        });
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<XmlCursor, String> xmlCursorStringEntry = entries.get(i);
            XmlCursor cursor = xmlCursorStringEntry.getKey();
            XWPFParagraph xwpfParagraph = xwpfDocument.insertNewParagraph(cursor);
            // 设置第一个标题的模板变量
            if (i == entries.size() - 1) {
                XWPFRun run = xwpfParagraph.createRun();
                run.setText("{{title" + (entries.size() - i - 1) + "}}");
                continue;
            }
            // XWPFRun run = xwpfParagraph.createRun();
            // run.setText("{{title" + i + "}}");
            XWPFParagraph xwpfParagraph1 = xwpfDocument.insertNewParagraph(xwpfParagraph.getCTP().newCursor());
            XWPFParagraph xwpfParagraph2 = xwpfDocument.insertNewParagraph(xwpfParagraph1.getCTP().newCursor());
            xwpfParagraph2.createRun().setText(xmlCursorStringEntry.getValue());
            // 标题段落
            XWPFParagraph xwpfParagraph3 = xwpfDocument.insertNewParagraph(xwpfParagraph2.getCTP().newCursor());
            XWPFRun run = xwpfParagraph3.createRun();
            run.setText("{{title" + (entries.size() - i - 1) + "}}");
        }
        xwpfDocument.write(new FileOutputStream("d://test.docx"));
    }


    private static String addBracket(String runText) {
        return "{{" + runText + "}}";
    }

    private static String stripBracket(String text) {
        return text.substring(2, text.length() - 2);
    }

    private static boolean isSimilarTable(String s1, String s2) {
        return s1.substring(0, s1.length() - 1).equals(s2.substring(0, s2.length() - 1));
    }

    private static void fillTableHead(TableRenderData tableRenderData) {
        RowRenderData rowRenderData = new RowRenderData();
        List<CellRenderData> cellRenderDataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            CellRenderData cellRenderData = new CellRenderData();
            ParagraphRenderData paragraphRenderData = new ParagraphRenderData();
            WidthEnum value = WidthEnum.values()[i];
            paragraphRenderData.addText(value.getTitle());
            cellRenderData.addParagraph(paragraphRenderData);
            cellRenderDataList.add(cellRenderData);
        }
        rowRenderData.setCells(cellRenderDataList);
        tableRenderData.getRows().add(0, rowRenderData);
    }


    @Test
    public void testObject2Map() {
        @Data
        class A {
            private String aA;
            private TableRenderData a;
        }
        A a = new A();
        a.setAA("a");
        a.setA(new TableRenderData());
        System.out.println(BeanUtil.beanToMap(a, false, false));
    }

    @Test
    public void testEnum() throws Exception {
        WidthEnum[] widthEnum = WidthEnum.class.getEnumConstants();
        for (WidthEnum anEnum : widthEnum) {
            Enum<WidthEnum> e = anEnum;

            System.out.println(anEnum);
        }
    }

    @Data
    @AllArgsConstructor
    class A {
        private String a;
    }

    @Test
    public void testDistinct() {
        List<A> aList = new ArrayList<>();
        aList.add(new A("1"));
        aList.add(new A("1"));
        aList.add(new A("3"));
        aList.add(new A("2"));
        aList.add(new A("1"));
        aList.add(new A("3"));
        List<A> collect = aList.stream().filter(distinctByKey(A::getA)).collect(Collectors.toList());
        System.out.println(collect);
    }


    private Predicate<? super A> distinctByKey(Function<? super A, Object> function) {
        // HashSet<Object> seen = new HashSet<>();    // 为什么只调用一次
        HashSet<Object> seen = new HashSet<Object>() {
            @Override
            public boolean add(Object o) {
                System.out.println("here");
                return super.add(o);
            }
        };
        return t -> seen.add(function.apply(t));
    }
}
