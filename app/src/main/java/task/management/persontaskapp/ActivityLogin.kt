package task.management.persontaskapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class ActivityLogin() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        login()
    }

    private fun login(){
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            val kul_ad = findViewById<EditText>(R.id.kul_ad)
            val kul_sifre = findViewById<android.widget.EditText>(R.id.kul_sifre)
            val ad = kul_ad.text.toString().trim()
            val sifre = kul_sifre.text.toString().trim()
            if ((ad == "erol" || ad == "Erol") && sifre == "ES.65gs94") {
                val intent = Intent(this, ChosingActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Giriş Başarılı !", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Kullanıcı Adı veya Şifre Yanlış !", Toast.LENGTH_LONG).show()
            }
        }
        }
}