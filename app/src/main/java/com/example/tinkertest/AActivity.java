package com.example.tinkertest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TinkerInstaller.onReceiveUpgradePatch(this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/test");
        TextView tv = (TextView) findViewById(R.id.tva);
        tv.setText("我是修复之后的app");
    }
}
