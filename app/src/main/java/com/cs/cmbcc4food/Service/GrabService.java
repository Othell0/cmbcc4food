package com.cs.cmbcc4food.Service;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class GrabService extends AccessibilityService {
    public GrabService() {
    }
    /**接收事件,如触发了通知栏变化、界面变化等  */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }
    /** 接收按键事件*/
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    /** 连接服务后,一般是在授权成功后会接收到*/
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }
    /**服务中断，如授权关闭或者将服务杀死*/
    @Override
    public void onInterrupt() {

    }


}
