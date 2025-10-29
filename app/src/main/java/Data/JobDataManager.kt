package Data

import Entity.Job

object JobDataManager: JobManager {

    private var jobList = mutableListOf<Job>()

    //Create and add
    override fun add(job: Job){
        jobList.add(job)
    }

    //Delete
    override fun remove(id: String) {
        jobList.removeIf { it.ID.trim() == id.trim() }
    }

    //Update
    override fun update(person: Job) {
        remove(person.ID)
        add(person)
    }

    //Get all the information
    override fun getAll()= jobList

    //Get information by ID
    override fun getById(id: String): Job? {
        val result = jobList.filter { it.ID.trim() == id.trim() }
        return if(result.any()) result[0] else null
    }

}