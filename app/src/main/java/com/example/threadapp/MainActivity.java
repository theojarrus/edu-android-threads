package com.example.threadapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int ID_MY_THREAD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyThread myThread = new MyThread("+");
        myThread.start();
    }

    public Handler myHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ID_MY_THREAD:
                    // ui logic
                    ((TextView) findViewById(R.id.textView)).setText((String) msg.obj);
                    break;
                case 2:
                    break;
            }
        }
    };

    public class MyThread extends Thread {

        private final String msg;

        public MyThread(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
            Message message = new Message();
            message.what = ID_MY_THREAD;
            message.obj = msg + " done!";
            myHandler.sendMessage(message);
        }
    }
}
