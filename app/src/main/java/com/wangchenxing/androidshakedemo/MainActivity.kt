package com.wangchenxing.androidshakedemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlin.math.abs

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event!!.values[0]
        val y = event.values[1]
        val z = event.values[2]
        if (abs(x) >= 15 && abs(y) >= 15 && abs(z) >= 15){
            Toast.makeText(applicationContext, "Shaking", Toast.LENGTH_SHORT).show()
            try {
                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val r = RingtoneManager.getRingtone(applicationContext, notification)
                r.play()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}