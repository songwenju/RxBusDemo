package com.wjustudio.rxbusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * 作者： songwenju on 2016/8/24 22:07.
 * 邮箱： songwenju@outlook.com
 */
public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void calc(View view){
        RxBus.getInstance().send(new CalculateEvent(""+ 3 * 2));
    }
}
