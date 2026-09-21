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

class MagnetometroActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var magnetometro: Sensor? = null

    private lateinit var textViewX: TextView
    private lateinit var textViewY: TextView
    private lateinit var textViewZ: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magnetometro)

        textViewX = findViewById(R.id.textViewMagnetometroX)
        textViewY = findViewById(R.id.textViewMagnetometroY)
        textViewZ = findViewById(R.id.textViewMagnetometroZ)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        magnetometro = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        if (magnetometro == null) {
            Toast.makeText(this, "Magnetômetro não disponível neste dispositivo.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        magnetometro?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
            val x = event.values[0] // Componente X do campo magnético (microtesla - µT)
            val y = event.values[1] // Componente Y do campo magnético (microtesla - µT)
            val z = event.values[2] // Componente Z do campo magnético (microtesla - µT)

            textViewX.text = "X: %.2f µT".format(x)
            textViewY.text = "Y: %.2f µT".format(y)
            textViewZ.text = "Z: %.2f µT".format(z)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Não precisamos fazer nada aqui para o magnetômetro.
    }
}