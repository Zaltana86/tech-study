package com.ivx;


import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;
import java.io.IOException;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2023/2/9 15:24
 */

public class GoogleTess4jTest {

    public static String readChar(String path) {
        // JNA Interface Mapping
        ITesseract instance = new Tesseract();
        // JNA Direct Mapping
        // ITesseract instance = new Tesseract1();
        File imageFile = new File(path);
        //In case you don't have your own tessdata, let it also be extracted for you
        //这样就能使用classpath目录下的训练库了
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        //Set the tessdata path
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        //英文库识别数字比较准确
        instance.setLanguage("chinese");
        return getOCRText(instance, imageFile);
    }

    /**
     * 从图片中提取文字
     *
     * @param path     图片路径
     * @param dataPath 训练库路径
     * @param language 语言字库
     * @return
     */
    public static String readChar(String path, String dataPath, String language) {
        File imageFile = new File(path);
        ITesseract instance = new Tesseract();
        instance.setDatapath(dataPath);
        //英文库识别数字比较准确
        instance.setLanguage(language);
        return getOCRText(instance, imageFile);
    }

    /**
     * 识别图片文件中的文字
     *
     * @param instance
     * @param imageFile
     * @return
     */
    private static String getOCRText(ITesseract instance, File imageFile) {
        String result = null;
        try {
            result = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws TesseractException, IOException {
        /*String path = "src/main/resources/image/text.png";
        System.out.println(readChar(path));*/

        // String path = "d:/test.png";
        // System.out.println(readChar(path));
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        instance.setDatapath("D:\\study\\workspace\\tech-study\\baidu-ocr\\tessdata");
        // 我们需要指定识别语种
        // instance.setLanguage("eng");
        // 指定识别图片
        File imgDir = new File("d:/test.jpg");
        CleanElementImage.handlImage(imgDir, "d:/");
        String ocrResult = instance.doOCR(imgDir);
        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult);
    }

}
