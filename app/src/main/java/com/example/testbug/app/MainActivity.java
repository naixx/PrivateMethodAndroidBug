package com.example.testbug.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import rx.Observable;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        methodCall();
    }

    private void problemPrivateMethod() {
    }

    void methodCall() {
        Observable.from("foo").lift((Observable.Operator<String, String>) (subscriber) -> {
            Runnable ref = MainActivity.this::problemPrivateMethod;
            ref.run();
            return subscriber;
        }).subscribe(s -> {

        });
        problemPrivateMethod();
    }
}
