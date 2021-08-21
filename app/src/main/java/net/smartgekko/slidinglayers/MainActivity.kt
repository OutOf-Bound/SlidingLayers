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

    lateinit var textView: TextView
    lateinit var movieAdapter: MoveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        movieAdapter = MoveAdapter(this)
        movieAdapter.setListener(object:MoveListener{
            override fun onMove(x: Float, y: Float) {
                setTextShadow(x,y)
            }
        })
    }

    private fun setTextShadow(x: Float,y: Float){
            textView.setShadowLayer(10F,0-x,y,getColor(R.color.gray))
    }
}