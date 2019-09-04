package com.alekskuzmin.flyhi.core.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

class DialogControllerImpl(private val fragment: Fragment) : DialogController {

    private var dialog: Dialog? = null

    override fun showMessage(
        titleString: CharSequence?,
        messageString: CharSequence,
        positiveButton: CharSequence,
        dialogListener: DialogInterface.OnClickListener,
        onCancelListener: DialogInterface.OnCancelListener
    ) {
        fragment.context?.let {
            dismissCurrent()

            val builder = AlertDialog.Builder(it)
            builder.setTitle(titleString)
                .setMessage(messageString)
                .setPositiveButton(positiveButton, dialogListener)
                .setOnCancelListener(onCancelListener)
            dialog = builder.show()
        }
    }

    override fun dismissCurrent() {
        dialog?.dismiss()
    }
}