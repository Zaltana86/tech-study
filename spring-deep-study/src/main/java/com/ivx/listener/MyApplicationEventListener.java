package com.ivx.listener;

import com.ivx.event.MyApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @className: MyApplicationEventListener
 * @description:事件监听器
 * @author: sh.Liu
 * @date: 2022-03-23 14:50
 */
@Component
public class MyApplicationEventListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("收到消息：" + event);
    }
}
