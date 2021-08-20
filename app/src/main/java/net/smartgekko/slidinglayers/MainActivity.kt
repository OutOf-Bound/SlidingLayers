package net.smartgekko.slidinglayers

import android.graphics.Color
import android.hardware.Sensor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import android.hardware.SensorEvent

import android.hardware.SensorEventListener
import android.widget.TextView
import java.lang.Math.abs


class MainActivity : AppCompatActivity() {
    val SHADOW_SIZE_LIMIT_X = 8F
    val SHADOW_SIZE_LIMIT_Y = 8F
    lateinit var textView: TextView
    var sensorManager: SensorManager? = null
    var sensorMove: Sensor? = null
    var valueX =0F
    var valueY = 0F
    var valueZ = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager?
        sensorMove = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager!!.registerListener(listenerMove, sensorMove,
            SensorManager.SENSOR_DELAY_NORMAL);

    }

    var listenerMove: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            valueX = 0+event.values[0]*(SHADOW_SIZE_LIMIT_X/10)
            valueY = 0+event.values[1]*(SHADOW_SIZE_LIMIT_Y/10)
            valueZ = event.values[2]
            textView.setText("Shadow")
            setTextShadow(valueX,valueY)
        }
    }

    private fun setTextShadow(x: Float,y: Float,limit: Float=5F){

            textView.setShadowLayer(5F,0-x,y,getColor(R.color.gray))


    }
}