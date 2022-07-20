package com.android.menusandpickers

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SimpleDialog :DialogFragment () {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireActivity())
            .setTitle("Information")
            .setMessage("this is just a simple dialog")
            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(activity, "Ok Button is pressed", Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(activity, "Cancel Button is pressed", Toast.LENGTH_SHORT).show()
            })
            .create()
        return dialog

    }
}