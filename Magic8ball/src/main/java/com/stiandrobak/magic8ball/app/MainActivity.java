package com.stiandrobak.magic8ball.app;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;


public class MainActivity extends Activity implements SensorEventListener{
    final private Handler handler = new Handler();
    private TextView msg;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Position position;
    private boolean initialPositionSet = false;
    private static float NOISE = 30.0f;
    private Animation fadeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        msg = (TextView) findViewById(R.id.message);
        Button b = (Button) findViewById(R.id.switchMessage);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText();
            }
        });
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Position newPosition = Position.positionFromArray(event.values);
        if(initialPositionSet) {
            Delta delta = position.delta(newPosition);
            if(delta.anyLargerThan(NOISE)){
                changeText();
            }
        }else {
            position = newPosition;
            initialPositionSet = true;
        }
    }

    private void changeText(){
        msg.startAnimation(fadeAnimation);
        handler.postDelayed(new FadeText(msg, AnswerPicker.getAnswer()),400);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
