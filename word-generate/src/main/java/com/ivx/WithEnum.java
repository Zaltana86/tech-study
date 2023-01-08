package com.ivx;

import lombok.Getter;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @since 2023/1/8 18:01
 * @apiNote
*/
@Getter
public enum WithEnum {
    WIDTH1("8.4%"),
    WIDTH2("19.3%"),
    WIDTH3("29.4%"),
    WIDTH4("24%"),
    WIDTH5("11.8%"),
    WIDTH6("31%");
    private final String with;
    WithEnum(String s) {
        this.with = s;
    }
}
