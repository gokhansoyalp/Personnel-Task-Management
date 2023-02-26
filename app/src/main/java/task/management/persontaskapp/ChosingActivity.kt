package task.management.persontaskapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChosingActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secim_ekrani)
        secimYap()
    }


    private fun secimYap(){
        val btnAlarm = findViewById<Button>(R.id.alarm_kur)
        val btnHatirlatici = findViewById<Button>(R.id.hatirlatici)
        val btnNot = findViewById<Button>(R.id.not_ekle)

        btnAlarm.setOnClickListener {
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }

        btnHatirlatici.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnNot.setOnClickListener {
            val intent = Intent(this, NotActivity::class.java)
            startActivity(intent)
        }
    }
}