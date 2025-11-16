package Controller

import Data.JobDataManager
import Data.JobManager
import Entity.Job
import android.content.Context
import cr.ac.utn.proyectoempleoscr.R

class JobController(private val context: Context) {

    private var jobData: JobManager = JobDataManager

    fun addJob(job: Job) {
        try {
            jobData.add(job)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateJob(job: Job) {
        try {
            jobData.update(job)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getById(id: String): Job? {
        return try {
            jobData.getById(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getJobs(): List<Job> {
        return try {
            jobData.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun removeJob(id: String) {
        try {
            val result = jobData.getById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNoFound))
            }
            jobData.remove(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }
}
