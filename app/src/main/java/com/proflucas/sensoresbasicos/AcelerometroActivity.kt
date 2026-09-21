package com.proflucas.sensoresbasicos

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AcelerometroActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null

    private lateinit var textViewX: TextView
    private lateinit var textViewY: TextView
    private lateinit var textViewZ: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acelerometro)

        textViewX = findViewById(R.id.textViewAcelerometroX)
        textViewY = findViewById(R.id.textViewAcelerometroY)
        textViewZ = findViewById(R.id.textViewAcelerometroZ)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (acelerometro == null) {
            Toast.makeText(this, "Acelerômetro não disponível neste dispositivo.", Toast.LENGTH_LONG).show()
            finish() // Fecha a atividade se o sensor não estiver disponível
        }
    }

    override fun onResume() {
        super.onResume()
        // Registra o listener para começar a receber dados do sensor
        acelerometro?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // Desregistra o listener para economizar bateria quando a atividade não está visível
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Este método é chamado sempre que os dados do sensor mudam
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0] // Aceleração no eixo X
            val y = event.values[1] // Aceleração no eixo Y
            val z = event.values[2] // Aceleração no eixo Z

            textViewX.text = "X: %.2f m/s²".format(x)
            textViewY.text = "Y: %.2f m/s²".format(y)
            textViewZ.text = "Z: %.2f m/s²".format(z)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Este método é chamado quando a precisão do sensor muda.
        // Para o acelerômetro, geralmente não precisamos fazer nada aqui.
    }
}