package net.smartgekko.slidinglayers

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.abs
import java.lang.Math.round

class MoveAdapter(activity: AppCompatActivity) {
    var valueX = 0F
    var valueY = 0F
    var valueZ = 0F
    var angleA = 0F
    var angleB = 0F
    val SHADOW_SIZE_LIMIT_X = 0.9F
    val SHADOW_SIZE_LIMIT_Y = 0.9F
    val SHADOW_SIZE_LIMIT_Z = 0.9F
    val LIMIT_ANGLE_Z = 1.0F
    private lateinit var listener: MoveListener

    var listenerMove: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            var needUpdate: Boolean = true
            valueX = 0 + event.values[0]*SHADOW_SIZE_LIMIT_X
            valueY = 0 + event.values[1]*SHADOW_SIZE_LIMIT_X
            valueZ = 0 + event.values[2]*SHADOW_SIZE_LIMIT_X

            if (valueZ < LIMIT_ANGLE_Z) {
                if (valueX > 0 && valueY>0) {
                    angleA = valueX *10
                }
                else if (valueX > 0 && valueY<0) {
                    angleA = 90+(SHADOW_SIZE_LIMIT_X*10- valueX)*10
                }
                else if (valueX < 0 && valueY<0) {
                    angleA = 180+abs(valueX)*10
                }
                else {
                    angleA =  270 + (SHADOW_SIZE_LIMIT_X*10 - abs(valueX)) *10
                }

                if(angleA<20 || angleA>340) angleB = 0F
                else if(angleA<115 && angleA>70) angleB = 90F
                else if(angleA<200 && angleA>160) angleB = 180F
                else if(angleA<290 && angleA>250) angleB = -90F

                }
            if (listener != null) listener.onMove(valueX, valueY, valueZ, angleA,angleB)
        }
    }

    fun setListener(listener: MoveListener) {
        this.listener = listener
    }

    init {
        var sensorManager: SensorManager? = null
        var sensorMove: Sensor? = null
        sensorManager = activity.getSystemService(SENSOR_SERVICE) as SensorManager?
        sensorMove = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager!!.registerListener(
            listenerMove, sensorMove,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }
}