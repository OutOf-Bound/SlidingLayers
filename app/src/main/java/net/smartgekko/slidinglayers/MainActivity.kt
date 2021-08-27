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
    lateinit var valueX: TextView
    lateinit var valueY: TextView
    lateinit var valueZ: TextView
    lateinit var valueA: TextView
    lateinit var movieAdapter: MoveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        valueX = findViewById(R.id.valueX)
        valueY = findViewById(R.id.valueY)
        valueZ = findViewById(R.id.valueZ)
        valueA = findViewById(R.id.valueAngle)

        movieAdapter = MoveAdapter(this)
        movieAdapter.setListener(object : MoveListener {
            override fun onMove(x: Float, y: Float,z: Float,a: Float) {
                setGlobalPosition(x, y, z, a)
            }
        })
    }

    private fun setGlobalPosition(x: Float, y: Float, z: Float, a: Float) {
        textView.setShadowLayer(10F, 0 - x, y, getColor(R.color.gray))
        textView.rotation = a
        valueX.setText(x.toString())
        valueY.setText(y.toString())
        valueZ.setText(z.toString())
        valueA.setText(a.toString())
    }
}