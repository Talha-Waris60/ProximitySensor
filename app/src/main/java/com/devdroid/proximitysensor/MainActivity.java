package com.devdroid.proximitysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 1: Take SensorManager
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager != null)
        {
            // Step 2: Take Sensor From sensor Class
            Sensor proixiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (proixiSensor != null)
            {
                // step 3: Register Sensor
                sensorManager.registerListener(this,proixiSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // step 4
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
            ((TextView)findViewById(R.id.sensorValues)).setText(" Value: " +sensorEvent.values[0]);
            if (sensorEvent.values[0] > 0)
            {
                Toast.makeText(this,"Object is Far", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Object is Near", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}