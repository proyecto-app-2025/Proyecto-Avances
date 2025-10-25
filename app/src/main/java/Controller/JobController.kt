package Controller

import Data.JobDataManager
import Data.JobManager
import Entity.Job
import android.content.Context
import cr.ac.utn.empleoscr.R

class JobController {

    private var data: JobManager = JobDataManager

    private var context: Context

    constructor(context: Context){
        this.context=context
    }

    fun addJob(job: Job){
        try {
            data.add(job)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateJob(job: Job){
        try {
            data.update(job)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getById(id: String): Job{
        try {
            val result = data.getById(id)
            if(result==null){
                throw Exception(context.getString(R.string.MsgDataNoFound))
            }
            return result
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

}