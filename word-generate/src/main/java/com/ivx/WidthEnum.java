package com.ivx;

import lombok.Getter;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @since 2023/1/8 18:01
 * @apiNote 表格宽度数据
*/
@Getter
public enum WidthEnum {
    WIDTH1("序号","8%"),
    WIDTH2("中文名称","19%"),
    WIDTH3("INCI名称/英文名称","17%"),
    WIDTH4("使用目的","15%"),
    WIDTH5("在《已使用原料目录》中的序号","14%"),
    WIDTH6("备注","27%");
    private final String width;
    private final String title;
    WidthEnum(String title, String width) {
        this.width = width;
        this.title = title;
    }
}
