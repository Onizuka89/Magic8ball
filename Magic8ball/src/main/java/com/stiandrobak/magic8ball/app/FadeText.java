package com.stiandrobak.magic8ball.app;

import android.widget.TextView;

/**
 * Created by stiansd on 16.04.14.
 */
public class FadeText implements Runnable{
    private TextView textView;
    private String newString;
    public FadeText(TextView textView, String newString){
        this.textView = textView;
        this.newString = newString;
    }
    @Override
    public void run() {
        textView.setText(this.newString);
    }
}
