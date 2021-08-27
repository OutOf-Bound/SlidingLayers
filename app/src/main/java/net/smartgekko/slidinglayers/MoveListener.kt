package net.smartgekko.slidinglayers

interface MoveListener {
    fun onMove(x: Float,y: Float,z: Float,angleA: Float,angleB: Float)
}