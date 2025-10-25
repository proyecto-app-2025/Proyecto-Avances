package Util

import android.content.Context
import android.content.Intent

class Util {
    companion object{
        fun openActivity(context: Context, objActivity: Class<*>){
            val objIntent = Intent(context, objActivity)
            context.startActivity(objIntent)
        }
    }
}