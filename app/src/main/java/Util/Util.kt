package Util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import cr.ac.utn.proyectoempleoscr.R

class Util {
    companion object {

        fun openActivity(
            context: Context,
            objClass: Class<*>,
            extraName: String = "",
            value: String? = null
        ) {
            val intent = Intent(context, objClass).apply {
                putExtra(extraName, value)
            }
            context.startActivity(intent)
        }

        fun showDialogCondition(
            context: Context,
            questionText: String,
            callback: () -> Unit
        ) {
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage(questionText)
                .setCancelable(false)
                .setPositiveButton(
                    context.getString(R.string.TextYes),
                    DialogInterface.OnClickListener { dialog, id ->
                        callback()
                    })
                .setNegativeButton(
                    context.getString(R.string.TextNo),
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            val alert = dialogBuilder.create()
            alert.setTitle(context.getString(R.string.TextTitleQuestion))
            alert.show()
        }
    }
}
