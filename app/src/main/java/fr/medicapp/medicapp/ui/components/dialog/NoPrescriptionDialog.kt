package fr.medicapp.medicapp.ui.components.dialog

import android.app.AlertDialog
import android.content.Context

class NoPrescriptionDialog {
    companion object {
        fun show(ctx: Context){
            AlertDialog.Builder(ctx)
                .setTitle("Erreur")
                .setMessage("Vous n'avez pas de prescription pour le moment.")
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}