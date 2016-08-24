package com.wjustudio.rxbusdemo;

/**
 * songwenju on 16-8-24 : 16 : 56.
 * 邮箱：songwenju@outlook.com
 */
public class CalculateEvent {
    String result;    //计算的结果

    public CalculateEvent(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "CalculateEvent{" +
                "result='" + result + '\'' +
                '}';
    }
}
