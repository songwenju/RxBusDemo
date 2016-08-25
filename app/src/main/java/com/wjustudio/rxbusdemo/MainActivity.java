package com.wjustudio.rxbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    /**
     * 订阅事件
     */
    private CompositeSubscription rxSubscriptions = new CompositeSubscription();
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        //订阅下载事件
        subscribeDownloadEvent();
    }

    public void open(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    /**
     * RxBus  subscribeDownloadEvent()
     */
    private void subscribeDownloadEvent() {
        rxSubscriptions.add(RxBus.getInstance().toObservable(CalculateEvent.class)
                .map(new Func1<Object, CalculateEvent>() {
                    @Override
                    public CalculateEvent call(Object o) {
                        return (CalculateEvent) o;
                    }
                })
                .subscribe(new Action1<CalculateEvent>() {
                    @Override
                    public void call(CalculateEvent calculateEvent) {
                        Log.i(TAG, "MainActivity.call." + calculateEvent.toString());
                        mTvResult.setText(calculateEvent.getResult());
                    }
                }));
    }


    /**
     * 取消订阅，防止内存泄漏
     */
    private void unSubscribe() {
        Log.i(TAG,"MainActivity.unSubscribe.");
        if (!rxSubscriptions.isUnsubscribed()) {
            rxSubscriptions.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        unSubscribe();
    }
}
