package Data

import Entity.Profile

object PDataManager : ProfileDataManager {

    private var profileList = mutableListOf<Profile>()

    override fun add(profile: Profile) {
        profileList.add(profile)
    }

    override fun remove(id: String) {
        //Lo que hace it es acceder a los atributos
        // y métodos de persona por medio de la lista
        profileList.removeIf { it.ID.trim() == id.trim() }
    }

    override fun update(profile: Profile) {
        remove(profile.ID) //remove en este caso llama al metodo de arriba
        add(profile) //add en este caso llama al metodo de arriba
    }

    override fun getAll() = profileList

    override fun getById(id: String): Profile? {
        val result = profileList.filter { it.ID.trim() == id.trim() }
        //Lo siguiente valida que el primer modulo de la lista esta lleno
        return if(result.any()) result[0] else null
    }

    override fun getByFullName(fullname: String): Profile? {
        val result = profileList.filter { it.FullName() == fullname.trim() }
        //Lo siguiente valida que el primer modulo de la lista esta lleno
        return if(result.any()) result[0] else null
    }

}