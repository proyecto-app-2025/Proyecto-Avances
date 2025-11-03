package Controller

import Data.PDataManager
import Data.PersonDataManager
import Entity.Person
import android.content.Context
import cr.ac.utn.proyectoempleoscr.R

class PersonController {

     private var personDataManager: PersonDataManager = PDataManager
    private lateinit var context: Context

    constructor(context: Context){
        this.context=context
    }

    fun add(person: Person){
        try {
            personDataManager.add(person)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updatePerson(person: Person){
        try {
            personDataManager.update(person)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getById(id:String): Person?{
        try {
            return personDataManager.getById(id)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByFullName(fullName:String): Person?{
        try {
            return personDataManager.getByFullName(fullName)
                //Tira exception y se sale del metodo por eso
                // no ocupo poner implicitamente el else
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun removePerson(id: String){
        try {
            val result = personDataManager.getById(id)
            if (result == null){
                throw Exception(context.getString(R.string.MsgDataNoFound))
            }
            personDataManager.remove(id)
        }catch (e: Exception){
            throw Exception(context.getString((R.string.ErrorMsgRemove)))
        }
    }
}