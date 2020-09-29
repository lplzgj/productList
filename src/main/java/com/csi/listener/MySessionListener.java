package com.csi.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session�������"+httpSessionBindingEvent.getName()+"---"+
                httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session�������Ƴ�����"+httpSessionBindingEvent.getName()+"---"+
                httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session�����ǻ���"+httpSessionBindingEvent.getName()+"---"+
                httpSessionBindingEvent.getValue());
    }
}
