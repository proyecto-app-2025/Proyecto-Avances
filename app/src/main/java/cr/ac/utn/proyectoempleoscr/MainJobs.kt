package cr.ac.utn.proyectoempleoscr

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainJobs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_jobs)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPost = findViewById<Button>(R.id.btnPublish)
        btnPost.setOnClickListener(View.OnClickListener{ view ->
            Util.Util.openActivity(this, MainPostJob::class.java)
        })

        val btnReturn = findViewById<ImageButton>(R.id.btnBack)
        btnReturn.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, MainActivity::class.java)
        })



    }

}