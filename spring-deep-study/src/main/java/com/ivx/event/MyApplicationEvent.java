package com.ivx.event;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/11/9 17:49
 */
@Getter
@Setter
@ToString
public class MyApplicationEvent extends ApplicationEvent {

    private Integer age;

    private String name;

    /**
     * 需要重写构造方法
     */
    public MyApplicationEvent(Object source, String name, Integer age) {
        super(source);
        this.name = name;
        this.age = age;
    }
}
