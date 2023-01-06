package com.ivx.monitor;

import java.util.EventListener;

//所有事件监听器接口都要继承这个标签接口
public interface MonitorListener extends EventListener {
    void handleEvent(PrintEvent event);
}
