package Data

import Entity.Profile

interface ProfileDataManager {

    fun add(profile: Profile)
    fun update(profile: Profile)
    fun remove(id: String)
    fun getAll(): List<Profile>
    fun getById(id: String): Profile? //Pueder retornar persona o nulo(?)
    fun getByFullName(fullname: String): Profile?

}