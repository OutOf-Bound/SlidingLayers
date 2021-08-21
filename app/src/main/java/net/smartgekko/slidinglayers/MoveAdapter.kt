package net.smartgekko.slidinglayers

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity

class MoveAdapter(activity: AppCompatActivity) {
    var valueX =0F
    var valueY = 0F
    var valueZ = 0F
    val SHADOW_SIZE_LIMIT_X = 8F
    val SHADOW_SIZE_LIMIT_Y = 8F

   private lateinit var listener: MoveListener

    var listenerMove: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            valueX = 0+event.values[0]*(SHADOW_SIZE_LIMIT_X/10)
            valueY = 0+event.values[1]*(SHADOW_SIZE_LIMIT_Y/10)
            valueZ = event.values[2]
            if(listener != null) listener.onMove(valueX,valueY);
        }
    }

   fun setListener(listener: MoveListener){
       this.listener = listener
   }

    init {
        var sensorManager: SensorManager? = null
        var sensorMove: Sensor? = null
        sensorManager = activity.getSystemService(SENSOR_SERVICE) as SensorManager?
        sensorMove = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager!!.registerListener(listenerMove, sensorMove,
            SensorManager.SENSOR_DELAY_NORMAL)
    }
}