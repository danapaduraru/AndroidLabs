package com.example.lab2android;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorsInfo extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "mySensors";
    private SensorManager sensorManager;
    private Sensor sAccelerometer, sGyroscope, sMagneticField, sLight, sPressure, sTemp, sHumi, sProximity;

    TextView xAccVal, yAccVal, zAccVal, xGyroVal, yGyroVal, zGyroVal, xMagneticFieldVal, yMagneticFieldVal, zMagneticFieldVal, light, pressure, temp, humi, proximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_info);

        checkBackgroundColor();  // LAB 5

        // LAB 6
        // Create a new Activity in your application and display information from the sensors available on the device.(3 p)

        // TEXTVIEWS
        xAccVal = (TextView) findViewById(R.id.xAccValue);
        yAccVal = (TextView) findViewById(R.id.yAccValue);
        zAccVal = (TextView) findViewById(R.id.zAccValue);

        xGyroVal = (TextView) findViewById(R.id.xGyroscopeValue);
        yGyroVal = (TextView) findViewById(R.id.yGyroscopeValue);
        zGyroVal = (TextView) findViewById(R.id.zGyroscopeValue);

        xMagneticFieldVal = (TextView) findViewById(R.id.xMagnometereValue);
        yMagneticFieldVal = (TextView) findViewById(R.id.yMagnometereValue);
        zMagneticFieldVal = (TextView) findViewById(R.id.zMagnometereValue);

        light = (TextView) findViewById(R.id.lightValue);
        light.setText("Light: ");
        pressure = (TextView) findViewById(R.id.pressureValue);
        temp = (TextView) findViewById(R.id.tempValue);
        humi = (TextView) findViewById(R.id.humidValue);
        proximity = (TextView) findViewById(R.id.proximityValue);

        // SETTING SENSORS

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // ACCELEROMETER
        sAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sAccelerometer != null) {
            sensorManager.registerListener(SensorsInfo.this, sAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            xAccVal.setText("Accelerometer Not Supported");
            yAccVal.setText("");
            zAccVal.setText("");
        }

        // GYROSCOPE
        sGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(sGyroscope != null) {
            sensorManager.registerListener(SensorsInfo.this, sGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            xGyroVal.setText("Gyroscope Not Supported");
            yGyroVal.setText("");
            zGyroVal.setText("");
        }

        // MAGNOMETER
        sMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(sMagneticField != null) {
            sensorManager.registerListener(SensorsInfo.this, sMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            xMagneticFieldVal.setText("Magnometer Not Supported");
            yMagneticFieldVal.setText("");
            zMagneticFieldVal.setText("");
        }

        // LIGHT
        sLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sLight != null) {
            sensorManager.registerListener(SensorsInfo.this, sLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            light.setText("Light Not Supported");
        }

        // PRESSURE
        sPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(sPressure != null) {
            sensorManager.registerListener(SensorsInfo.this, sPressure, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            pressure.setText("Pressure Not Supported");
        }

        // AMBIENT TEMPERATURE
        sTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(sTemp != null) {
            sensorManager.registerListener(SensorsInfo.this, sTemp, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            temp.setText("Ambient Temperature Not Supported");
        }

        // HUMIDITY
        sHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(sHumi != null) {
            sensorManager.registerListener(SensorsInfo.this, sHumi, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            temp.setText("Relative Humidity Not Supported");
        }

        // PROXIMITY
        sProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(sProximity != null) {
            sensorManager.registerListener(SensorsInfo.this, sProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            proximity.setText("Proximity Not Supported");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccVal.setText("x: " + sensorEvent.values[0]);
            yAccVal.setText(" y: " + sensorEvent.values[1]);
            zAccVal.setText(" z: " + sensorEvent.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            xGyroVal.setText("x: " + sensorEvent.values[0]);
            yGyroVal.setText(" y: " + sensorEvent.values[1]);
            zGyroVal.setText(" z: " + sensorEvent.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            xMagneticFieldVal.setText("x: " + sensorEvent.values[0]);
            yMagneticFieldVal.setText(" y: " + sensorEvent.values[1]);
            zMagneticFieldVal.setText(" z: " + sensorEvent.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_LIGHT) {
            light.setText("Light (SI lux units): " + sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            pressure.setText("Atmospheric Pressure (hPa): " + sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText("Ambient Temperature (°C): " + sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humi.setText("Relative Humidity: " + sensorEvent.values[0] + "%");
        }
        else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximity.setText("Proximity (cm): " + sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // LAB 5
    private void checkBackgroundColor(){
        RelativeLayout relativeLayout = findViewById(R.id.layoutSensorsInfo);
        SharedPreferences settingsSharedPref = this.getSharedPreferences("bgColor", MODE_PRIVATE);
        String background = settingsSharedPref.getString("bgColor","");
        if(!background.equals("")){
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
