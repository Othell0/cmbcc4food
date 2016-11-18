package com.cs.cmbcc4food.Service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class GrabService extends AccessibilityService implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String CMBCC_COUPON_GENERAL_ACTIVITY = "LauncherUI";
    private SharedPreferences sharedPreferences;
    private String currentActivityName = CMBCC_COUPON_GENERAL_ACTIVITY;
    private AccessibilityNodeInfo rootNodeInfo,mReceiveNode;

    public GrabService() {
    }
    /**接收事件,如触发了通知栏变化、界面变化等  */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        setCurrentActivityName(event);
        monitorCmbcc(event);
    }

    private void setCurrentActivityName(AccessibilityEvent event) {
        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return;
        }

        try {
            ComponentName componentName = new ComponentName(
                    event.getPackageName().toString(),
                    event.getClassName().toString()
            );

            getPackageManager().getActivityInfo(componentName, 0);
            currentActivityName = componentName.flattenToShortString();
        } catch (PackageManager.NameNotFoundException e) {
            currentActivityName = CMBCC_COUPON_GENERAL_ACTIVITY;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void monitorCmbcc(AccessibilityEvent event) {
        this.rootNodeInfo = getRootInActiveWindow();

        if (rootNodeInfo == null) return;
        getNodeInfo(event.getEventType());
        mReceiveNode = null;
        if (mReceiveNode != null) {
            mReceiveNode.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }

    }

    private void getNodeInfo(int eventType) {
        if (this.rootNodeInfo == null) return;
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


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
