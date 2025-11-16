package cr.ac.utn.proyectoempleoscr

import Controller.JobController
import Entity.Job
import Util.Util
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainJobs : AppCompatActivity(), JobListAdapter.OnJobClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_jobs)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycler = findViewById<RecyclerView>(R.id.recyclerEmpleos)
        val jobController = JobController(this)
        val customAdapter = JobListAdapter(jobController.getJobs(), this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recycler.layoutManager = layoutManager
        recycler.adapter = customAdapter
    }

    override fun onItemClicked(job: Job) {
        Util.openActivity(this, MainPostJob::class.java, "EXTRA_JOB_ID", job.ID)
    }
}
