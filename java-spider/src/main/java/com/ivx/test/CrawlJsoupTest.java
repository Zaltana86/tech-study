package com.ivx.test;


import cn.hutool.core.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote 使用joup进行html解析
 * @since 2022/9/1 16:14
 */

public class CrawlJsoupTest {
    @Test
    public void testJsoup() throws IOException {
        Document doc = Jsoup.parse(new URL("https://www.baidu.com"), 1000);
        String title = doc.getElementsByTag("title").first().text();
        Elements elements = doc.getElementsByClass("hot-title");
        String hotTitle = elements.attr("href");
        System.out.println(hotTitle);
        System.out.println(doc);
        System.out.println(title);
    }

    @Test
    public void test() {
        String s = "1234";
        System.out.println(StrUtil.split(s, 2)[1]);
        Integer count = Integer.valueOf("00011");
        count++;
        System.out.println(count);
    }

    @Test
    public void testSelector() throws IOException {
        Document doc = Jsoup.parse(new URL("https://www.baidu.com"), 1000);
        Elements select = doc.select("link[rel=search]");
        select.forEach(System.out::println);
        System.out.println(doc.select(".hot-title").first().attr("href"));
    }
}
