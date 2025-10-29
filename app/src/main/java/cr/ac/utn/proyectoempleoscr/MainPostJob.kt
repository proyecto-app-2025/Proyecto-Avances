package cr.ac.utn.proyectoempleoscr

import Util.Util
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainPostJob : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_post_job)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnVolver = findViewById<ImageButton>(R.id.btnBack)
        btnVolver.setOnClickListener(View.OnClickListener { view ->
            Util.openActivity(this, MainJobs::class.java)
        })

        val btnCanc = findViewById<Button>(R.id.btnCancelar)
        btnCanc.setOnClickListener(View.OnClickListener { view ->
            Util.openActivity(this, MainJobs::class.java)
        })

    }
}