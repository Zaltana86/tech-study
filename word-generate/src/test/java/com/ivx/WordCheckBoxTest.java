package com.ivx;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.xwpf.NiceXWPFDocument;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2023/2/24 16:48
 */

public class WordCheckBoxTest {
    @Test
    public void testAddCheckBox() throws Exception {
        String filePath = System.getProperty("user.dir") + "/resource/";
        XWPFTemplate template = XWPFTemplate.compile(filePath + "template.docx");
        NiceXWPFDocument document = template.getXWPFDocument();
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun titleFun = titleParagraph.createRun();
        String checkBox = "☑";
        titleFun.setText(checkBox);
        titleFun.setFontSize(12);
        titleFun.setFontFamily("宋体");
        titleFun.addBreak();
        template.writeAndClose(new FileOutputStream(filePath + "output.docx"));

    }

}
