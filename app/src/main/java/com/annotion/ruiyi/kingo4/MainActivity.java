package com.annotion.ruiyi.kingo4;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmd(Environment.getExternalStorageDirectory().getPath()+"/kingo4");
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    public  void cmd(String s) {
        Process process = null;
        OutputStream out = null;

        try {
            process = Runtime.getRuntime().exec("su");
            out = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            // 获取文件所有权限
            dataOutputStream.writeBytes("chmod 777 " + s
                    + "\n");
            // 进行静默安装命令
            dataOutputStream
                    .writeBytes("./"
                            + s);
            dataOutputStream.flush();
            // 关闭流操作
            dataOutputStream.close();
            out.close();
            int value = process.waitFor();
            Log.e("--value--", "" + value);
            //回调执行结果
        } catch (Exception e) {
            //发生异常
        }

    }
}
