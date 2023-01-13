package com.ivx;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

import java.io.*;
import java.util.List;
import java.util.Random;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote 给word添加印章
 * @since 2023/1/12 10:15
 */

public class AddStamp {
    public static void creatSealDocument(ByteArrayInputStream byteArrayInputStream) throws Exception {
        XWPFDocument doc = new XWPFDocument(byteArrayInputStream);
        List<XWPFFooter> footerList = doc.getFooterList();
        for (XWPFFooter xwpfFooter : footerList) {
             xwpfFooter.getParagraphs().get(0).getPart().getParent();
            // renderStamp(xwpfParagraph,doc);
        }
        doc.close();
    }

    private static void renderStamp(XWPFParagraph paragraph, XWPFDocument doc) throws Exception {
        if (paragraph != null) {
            //添加印章图片
            XWPFRun run = paragraph.createRun();

            String imgFile1 = "d:/test.jpg";
            FileInputStream is1 = new FileInputStream(imgFile1);
            run.addPicture(is1, XWPFDocument.PICTURE_TYPE_JPEG, imgFile1, Units.toEMU(60), Units.toEMU(60));
            is1.close();

            CTDrawing drawing1 = run.getCTR().getDrawingArray(0);
            CTGraphicalObject graphicalobject1 = drawing1.getInlineArray(0).getGraphic();
            Random random = new Random();
            int number = random.nextInt(999) + 1;
            //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
            CTAnchor anchor1 = getAnchorWithGraphic(graphicalobject1, "Seal" + number,
                    Units.toEMU(60), Units.toEMU(60),//图片大小
                    Units.toEMU(250), Units.toEMU(0), true);//相对当前段落位置及偏移
            drawing1.setAnchorArray(new CTAnchor[]{anchor1});//添加浮动属性
            drawing1.removeInline(0);//删除行内属性
            //添加签名图片
            run = paragraph.createRun();
            imgFile1 = "d:/test.jpg";
            FileInputStream is2 = new FileInputStream(imgFile1);
            run.addPicture(is2, XWPFDocument.PICTURE_TYPE_JPEG, imgFile1, Units.toEMU(60), Units.toEMU(60));
            is2.close();

            random = new Random();
            CTDrawing drawing2 = run.getCTR().getDrawingArray(0);
            CTGraphicalObject graphicalobject2 = drawing2.getInlineArray(0).getGraphic();
            number = random.nextInt(999) + 1;
            CTAnchor anchor2 = getAnchorWithGraphic(graphicalobject2, "Seal" + number,
                    Units.toEMU(60), Units.toEMU(40),//图片大小
                    Units.toEMU(300), Units.toEMU(-5), false);
            drawing2.setAnchorArray(new CTAnchor[]{anchor2});//添加浮动属性
            drawing2.removeInline(0);//删除行内属性

            doc.write(new FileOutputStream("d:/result.docx"));
        }
    }


    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
                                                String deskFileName, int width, int height,
                                                int leftOffset, int topOffset, boolean behind) {

        String anchorXML =
                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0) + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
                        + "<wp:positionH relativeFrom=\"column\">"
                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
                        + "</wp:positionH>"
                        + "<wp:positionV relativeFrom=\"paragraph\">"
                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
                        "</wp:positionV>"
                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
                        + "<wp:wrapNone/>"
                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
                        + "</wp:anchor>";

        CTDrawing drawing = null;
        try {
            drawing = CTDrawing.Factory.parse(anchorXML);
        } catch (XmlException e) {
            e.printStackTrace();
        }
        assert drawing != null;
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }

}
