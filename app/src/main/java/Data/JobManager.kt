package Data

import Entity.Job

interface JobManager {

    fun add (job: Job)
    fun update (job: Job)
    fun remove (id: String)
    fun getAll(): List<Job>
    fun getById(id: String): Job?


}