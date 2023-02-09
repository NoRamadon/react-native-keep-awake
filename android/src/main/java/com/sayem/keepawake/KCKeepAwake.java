// Adapted from
// https://github.com/gijoehosaphat/react-native-keep-screen-on

package com.sayem.keepawake;

import static android.content.Context.POWER_SERVICE;

import android.app.Activity;
import android.os.Build;
import android.os.PowerManager;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class KCKeepAwake extends ReactContextBaseJavaModule {

    public KCKeepAwake(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    private Activity mActivity;
    private PowerManager.WakeLock mWakeLock;

    @Override
    public void initialize() {
        super.initialize();
        mActivity = getCurrentActivity();
        PowerManager powerManager = (PowerManager) mActivity.getSystemService(POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
    }

    @Override
    public String getName() {
        return "KCKeepAwake";
    }

    @ReactMethod
    public void activate() {

        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
        }
    }

    @ReactMethod
    public void activateLockScreen() {
        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                         mActivity.setShowWhenLocked(true);
                         mActivity.setTurnScreenOn(true);
                     } else {

                        if (mWakeLock.isHeld()) {
                            mWakeLock.release();
                        }
                        mWakeLock.acquire();

                        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
                        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


                    }

                    System.out.println("activateLockScreen active ...");

                }
            });
        }
    }

    @ReactMethod
    public void deactivateLockScreen() {

        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                         mActivity.setShowWhenLocked(false);
                         mActivity.setTurnScreenOn(false);
                     } else {
                        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
                        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


                        if (mWakeLock.isHeld()) {
                            mWakeLock.release();
                        }
                    }

                    System.out.println("activateLockScreen deactivate ...");

                }
            });
        }
    }

    @ReactMethod
    public void deactivate() {
        final Activity activity = getCurrentActivity();

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
        }
    }
}
