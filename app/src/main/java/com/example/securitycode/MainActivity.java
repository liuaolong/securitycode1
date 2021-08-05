package com.example.securitycode;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        new TimeThread().start();

    }
    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(10);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /*long sysTime = System.currentTimeMillis();//获取系统时间
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss SSS", sysTime);//时间显示格式*/
                    //创建一个Date类，得到当前系统时间
                    Date dateTime=new Date();
                    //日期格式函数
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:SS");
                    //将当前时间转换格式，返回String类型
                    String currentTime=df.format(dateTime);
                    //将String类型转换成date类型，主要格式必须是yyyy-MM-dd HH:mm:ss相同
                    //Date dates=df.parse(currentTime);

                    textView.setText(currentTime); //更新时间
                    break;
                default:
                    break;

            }
        }
    };



}

