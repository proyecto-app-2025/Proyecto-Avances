package Controller

import Data.PDataManager
import Data.ProfileDataManager
import Entity.Profile
import android.content.Context
import cr.ac.utn.proyectoempleoscr.R

class ProfileController {

     private var profileDataManager: ProfileDataManager = PDataManager
    private lateinit var context: Context

    constructor(context: Context){
        this.context=context
    }

    fun addProfile(profile: Profile){
        try {
            profileDataManager.add(profile)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateProfile(profile: Profile){
        try {
            profileDataManager.update(profile)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getById(id:String): Profile?{
        try {
            return profileDataManager.getById(id)
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByFullName(fullName:String): Profile?{
        try {
            return profileDataManager.getByFullName(fullName)
                //Tira exception y se sale del metodo por eso
                // no ocupo poner implicitamente el else
        }catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun removeProfile(id: String){
        try {
            val result = profileDataManager.getById(id)
            if (result == null){
                throw Exception(context.getString(R.string.MsgDataNoFound))
            }
            profileDataManager.remove(id)
        }catch (e: Exception){
            throw Exception(context.getString((R.string.ErrorMsgRemove)))
        }
    }
}