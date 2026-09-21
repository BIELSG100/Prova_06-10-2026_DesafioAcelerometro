package com.proflucas.sensoresbasicos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAcelerometro: Button = findViewById(R.id.buttonAcelerometro)
        val buttonGiroscopio: Button = findViewById(R.id.buttonGiroscopio)
        val buttonMagnetometro: Button = findViewById(R.id.buttonMagnetometro)

        buttonAcelerometro.setOnClickListener {
            val intent = Intent(this, AcelerometroActivity::class.java)
            startActivity(intent)
        }

        buttonGiroscopio.setOnClickListener {
            val intent = Intent(this, GiroscopioActivity::class.java)
            startActivity(intent)
        }

        buttonMagnetometro.setOnClickListener {
            val intent = Intent(this, MagnetometroActivity::class.java)
            startActivity(intent)
        }
    }
}