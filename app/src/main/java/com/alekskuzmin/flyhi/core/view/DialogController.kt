package com.alekskuzmin.flyhi.core.view

import android.content.DialogInterface

interface DialogController {
    fun showMessage(
        titleString: CharSequence?,
        messageString: CharSequence,
        positiveButton: CharSequence,
        dialogListener: DialogInterface.OnClickListener,
        onCancelListener: DialogInterface.OnCancelListener
    )

    fun dismissCurrent()

}