package com.example.toolbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 定义按钮
    private Button btn_tool_01, btn_tool_02, btn_tool_03, btn_tool_04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化按钮
        btn_tool_01 = (Button) findViewById(R.id.btn_tool_01);
        btn_tool_02 = (Button) findViewById(R.id.btn_tool_02);
        btn_tool_03 = (Button) findViewById(R.id.btn_tool_03);
        btn_tool_04 = (Button) findViewById(R.id.btn_tool_04);

        //设置监听器
        btn_tool_01.setOnClickListener(this);
        btn_tool_02.setOnClickListener(this);
        btn_tool_03.setOnClickListener(this);
    }

    // 实现这个活动中的按钮的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 按钮1（备忘录的点击事件）
            case R.id.btn_tool_01:
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_tool_02:
                Intent intent2 = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivityForResult(intent2, 1);
                break;
            case R.id.btn_tool_03:
                Intent intent3 = new Intent(MainActivity.this, WebWeatherActivity.class);
                startActivityForResult(intent3, 1);
                break;
            case R.id.btn_tool_04:
                break;
        }
    }

    //在主界面label上右上角添加三个点，点击拉起菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, (Menu) menu);//这里是调用menu文件夹中的main.xml，在登陆界面label右上角的三角里显示其他功能
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                // Toast.makeText(MainActivity.this, "关于", 1000).show();
                // 声明AlertDialog
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("关于")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("版　本：" + getAppVersionName(this) + "\n开发者：李德银 于小雨 张守华")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //关闭对话框
                            }
                        }).setNegativeButton("跳转到本项目开源仓库地址", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                intent.setAction("android.intent.action.VIEW");
                                Uri content_url = Uri.parse("https://github.com/Android-final-exam-2020/toolbox");
                                intent.setData(content_url);
                                startActivity(intent);
                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
