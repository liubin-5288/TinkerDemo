package com.example.tinkertest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.example.tinkertest.tinker.log.MyLogImp;
import com.example.tinkertest.tinker.util.TinkerApplicationContext;
import com.example.tinkertest.tinker.util.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by liubin on 2017/5/6.
 */
@DefaultLifeCycle(
        application = "com.example.tinkertest.AppApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false
)
public class MyApplication extends DefaultApplicationLike {

    public MyApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        TinkerApplicationContext.application = getApplication();
        TinkerApplicationContext.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);

        TinkerManager.initFastCrashProtect();
        //在tinker安装之前调用
        TinkerManager.setUpgradeRetryEnable(true);

        //设置Tinker的日志
        TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker 在 加载multiDex之后调用
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
        Tinker.with(getApplication());

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
