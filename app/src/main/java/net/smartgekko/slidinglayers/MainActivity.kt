package net.smartgekko.slidinglayers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager


class MainActivity : AppCompatActivity() {
    lateinit var rootLayout: ConstraintLayout
    lateinit var textView: TextView
    lateinit var valueX: TextView
    lateinit var valueY: TextView
    lateinit var valueZ: TextView
    lateinit var valueA: TextView
    lateinit var valueB: TextView
    lateinit var movieAdapter: MoveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.textView)
        valueX = findViewById(R.id.valueX)
        valueY = findViewById(R.id.valueY)
        valueZ = findViewById(R.id.valueZ)
        valueA = findViewById(R.id.valueAngleA)
        valueB = findViewById(R.id.valueAngleB)

        movieAdapter = MoveAdapter(this)
        movieAdapter.setListener(object : MoveListener {
            override fun onMove(x: Float, y: Float, z: Float, angleA: Float, angleB: Float) {
                setGlobalPosition(x, y, z, angleA, angleB)
            }
        })
    }

    private fun setGlobalPosition(x: Float, y: Float, z: Float, aA: Float, aB: Float) {
      //  val anticlk_rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        textView.setShadowLayer(10F, (0 - x), y, getColor(R.color.gray))
      //  textView.startAnimation(anticlk_rotate)
        textView.rotation = aB
        valueX.setText(x.toString())
        valueY.setText(y.toString())
        valueZ.setText(z.toString())
        valueA.setText(aA.toString())
        valueB.setText(aB.toString())
    }
}