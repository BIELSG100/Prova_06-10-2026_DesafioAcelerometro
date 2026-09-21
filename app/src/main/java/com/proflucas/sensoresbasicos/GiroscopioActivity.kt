package com.proflucas.sensoresbasicos

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GiroscopioActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var giroscopio: Sensor? = null

    private lateinit var textViewX: TextView
    private lateinit var textViewY: TextView
    private lateinit var textViewZ: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giroscopio)

        textViewX = findViewById(R.id.textViewGiroscopioX)
        textViewY = findViewById(R.id.textViewGiroscopioY)
        textViewZ = findViewById(R.id.textViewGiroscopioZ)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (giroscopio == null) {
            Toast.makeText(this, "Giroscópio não disponível neste dispositivo.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        giroscopio?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val x = event.values[0] // Taxa de rotação em torno do eixo X (rad/s)
            val y = event.values[1] // Taxa de rotação em torno do eixo Y (rad/s)
            val z = event.values[2] // Taxa de rotação em torno do eixo Z (rad/s)

            textViewX.text = "X: %.2f rad/s".format(x)
            textViewY.text = "Y: %.2f rad/s".format(y)
            textViewZ.text = "Z: %.2f rad/s".format(z)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Não precisamos fazer nada aqui para o giroscópio.
    }
}