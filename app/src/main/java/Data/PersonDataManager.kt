package Data

import Entity.Person

interface PersonDataManager {

    fun add(person: Person)
    fun update(person: Person)
    fun remove(id: String)
    fun getAll(): List<Person>
    fun getById(id: String): Person? //Pueder retornar persona o nulo(?)
    fun getByFullName(fullname: String): Person?

}