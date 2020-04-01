package com.example.lab2android;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SensorsInfo extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "mySensors";
    private SensorManager sensorManager;
    private Sensor sAccelerometer, sGyroscope, sMagneticField, sRotation, sLight, sPressure, sTemp, sHumi, sProximity, sGeoMag, sGameRotation, sLinearAcc, sGravity;

    TextView accelerometer, gyroscope, magneticField, rotation, light, pressure, temp, humi, proximity, geomag, gameRotation, linearAcc, gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_info);

        checkBackgroundColor();  // LAB 5

        // LAB 6
        // Create a new Activity in your application and display information from the sensors available on the device.(3 p)

        // TEXTVIEWS
        accelerometer = (TextView) findViewById(R.id.accelerometerValues);
        gyroscope = (TextView) findViewById(R.id.gyrsocopeValues);
        magneticField = (TextView) findViewById(R.id.magneticFieldValues);

        rotation = (TextView) findViewById(R.id.rotationValue);
        light = (TextView) findViewById(R.id.lightValue);
        pressure = (TextView) findViewById(R.id.pressureValue);
        temp = (TextView) findViewById(R.id.tempValue);
        humi = (TextView) findViewById(R.id.humidValue);
        proximity = (TextView) findViewById(R.id.proximityValue);

        geomag = (TextView) findViewById(R.id.geoMagValue);
        gameRotation = (TextView) findViewById(R.id.gameRotationValue);
        gravity = (TextView) findViewById(R.id.gravityValue);
        linearAcc = (TextView) findViewById(R.id.linearAccValue);

        // SETTING SENSORS

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // list of all sensors
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int index = 0; index < sensorList.size(); index++) {
            Log.d("mySensors", String.valueOf(sensorList.get(index)));
        }

        // ACCELEROMETER
        sAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sAccelerometer != null) {
            sensorManager.registerListener(SensorsInfo.this, sAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            accelerometer.setText("Accelerometer Not Supported");
        }

        // GYROSCOPE
        sGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sGyroscope != null) {
            sensorManager.registerListener(SensorsInfo.this, sGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            gyroscope.setText("Gyroscope Not Supported");
        }

        // MAGNOMETER
        sMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (sMagneticField != null) {
            sensorManager.registerListener(SensorsInfo.this, sMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            magneticField.setText("Magnometer Not Supported");
        }

        // ROTATION VECTOR
        sRotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (sRotation != null) {
            sensorManager.registerListener(SensorsInfo.this, sRotation, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            rotation.setText("Rotation Not Supported");
        }

        // LIGHT
        sLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sLight != null) {
            sensorManager.registerListener(SensorsInfo.this, sLight, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            light.setText("Light Not Supported");
        }

        // PRESSURE
        sPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (sPressure != null) {
            sensorManager.registerListener(SensorsInfo.this, sPressure, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            pressure.setText("Pressure Not Supported");
        }

        // AMBIENT TEMPERATURE
        sTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sTemp != null) {
            sensorManager.registerListener(SensorsInfo.this, sTemp, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            temp.setText("Ambient Temperature Not Supported");
        }

        // HUMIDITY
        sHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (sHumi != null) {
            sensorManager.registerListener(SensorsInfo.this, sHumi, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            temp.setText("Relative Humidity Not Supported");
        }

        // PROXIMITY
        sProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (sProximity != null) {
            sensorManager.registerListener(SensorsInfo.this, sProximity, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            proximity.setText("Proximity Not Supported");
        }

        // Geomagnetic Rotation Vector
        sGeoMag = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        if (sGeoMag != null) {
            sensorManager.registerListener(SensorsInfo.this, sGeoMag, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            geomag.setText("Geomagnetic Rotation Vector Not Supported");
        }

        // Game Rotation Vector
        sGameRotation = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        if (sGameRotation != null) {
            sensorManager.registerListener(SensorsInfo.this, sGameRotation, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            geomag.setText("Game Rotation Vector Not Supported");
        }

        // LINEAR ACCELERATION
        sLinearAcc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (sLinearAcc != null) {
            sensorManager.registerListener(SensorsInfo.this, sLinearAcc, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            linearAcc.setText("Linear Acceleration Sensor Not Supported");
        }

        // gravity
        sGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (sGravity != null) {
            sensorManager.registerListener(SensorsInfo.this, sGravity, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            gravity.setText("Gravity Sensor Not Supported");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometer.setText(sAccelerometer.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscope.setText(sGyroscope.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticField.setText(sMagneticField.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            rotation.setText(sRotation.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_LIGHT) {
            light.setText(sLight.getName() + " " +sensorEvent.values[0]);
        }
        if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            pressure.setText(sPressure.getName() + " " +sensorEvent.values[0]);
        }
        if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText(sTemp.getName() + " " +sensorEvent.values[0]);
        }
        if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humi.setText(sHumi.getName() + " " +sensorEvent.values[0] + "%");
        }
        if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximity.setText(sProximity.getName() + " " +sensorEvent.values[0]);
        }
        if (sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) {
            geomag.setText(sGeoMag.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
            gameRotation.setText(sGameRotation.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            linearAcc.setText(sLinearAcc.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
        if (sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravity.setText(sGravity.getName() + " " +sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // LAB 5
    private void checkBackgroundColor() {
        RelativeLayout relativeLayout = findViewById(R.id.layoutSensorsInfo);
        SharedPreferences settingsSharedPref = this.getSharedPreferences("bgColor", MODE_PRIVATE);
        String background = settingsSharedPref.getString("bgColor", "");
        if (!background.equals("")) {
            switch (background) {
                case "Gray":
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorGray));
                    break;
                case "Blue":
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                    break;
                case "Green":
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorLightGreen));
                    break;
                case "Pink":
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorLightPink));
                    break;
            }
        }
    }
}
