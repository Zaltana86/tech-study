package com.ivx.monitor;


import org.junit.Test;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/11/7 16:09
 */

public class MonitorTest {
    @Test
    public void testMonitor() {
        EventSource eventSource = new EventSource();
        eventSource.addListener(event -> {
            event.doEvent();
            if (event.getSource().equals("openWindows")) {
                System.out.println("doOpen");
            }
            if (event.getSource().equals("closeWindows")) {
                System.out.println("doClose");
            }
        });

        /*
         * 传入openWindows事件，通知所有的事件监听器
         * 对open事件感兴趣的listener将会执行
         */
        eventSource.notifyListenerEvents(new PrintEvent("openWindows"));
    }
    /*
    result:
    通知一个事件源 source: openWindows
    doOpen
    这就是事件监听模式
    回调接口类: MonitorListener
    回调函数接口: handleEvent
    通过回调模型，EventSource事件源便可回调具体监听器动作
     */
}




