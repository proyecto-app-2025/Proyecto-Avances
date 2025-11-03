package Data

import Entity.Person

object PDataManager : PersonDataManager {

    private var personList = mutableListOf<Person>()

    override fun add(person: Person) {
        personList.add(person)
    }

    override fun remove(id: String) {
        //Lo que hace it es acceder a los atributos
        // y métodos de persona por medio de la lista
        personList.removeIf { it.ID.trim() == id.trim() }
    }

    override fun update(person: Person) {
        remove(person.ID) //remove en este caso llama al metodo de arriba
        add(person) //add en este caso llama al metodo de arriba
    }

    override fun getAll() = personList

    override fun getById(id: String): Person? {
        val result = personList.filter { it.ID.trim() == id.trim() }
        //Lo siguiente valida que el primer modulo de la lista esta lleno
        return if(result.any()) result[0] else null
    }

    override fun getByFullName(fullname: String): Person? {
        val result = personList.filter { it.FullName() == fullname.trim() }
        //Lo siguiente valida que el primer modulo de la lista esta lleno
        return if(result.any()) result[0] else null
    }

}