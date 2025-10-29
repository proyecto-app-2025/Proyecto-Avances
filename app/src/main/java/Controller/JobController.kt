package Controller

import Data.JobDataManager
import Data.JobManager
import Entity.Job
import android.content.Context
import cr.ac.utn.proyectoempleoscr.R

class JobController {

    private var Jobdata: JobManager = JobDataManager

    private var context: Context

    constructor(context: Context){
        this.context=context
    }

    fun addJob(job: Job){
        try {
            Jobdata.add(job)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateJob(job: Job){
        try {
            Jobdata.update(job)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getById(id: String): Job{
        try {
            val result = Jobdata.getById(id)
            if(result==null){
                throw Exception(context.getString(R.string.MsgDataNoFound))
            }
            return result
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }


    }

    fun removePerson(id: String){
        try{
            val result = Jobdata.getById(id)
            if (result == null){
                throw Exception(context
                    .getString(R.string.MsgDataNoFound))
            }
            Jobdata.remove(id)
        }catch (e: Exception){
            throw Exception(context
                .getString(R.string.ErrorMsgRemove))
        }

    }

}