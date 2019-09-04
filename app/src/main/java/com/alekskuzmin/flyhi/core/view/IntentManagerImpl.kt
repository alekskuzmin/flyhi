package com.alekskuzmin.flyhi.core.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import com.alekskuzmin.flyhi.core.utils.IntentManager

class IntentManagerImpl(private val fragment: Fragment) : IntentManager {

    override fun openInBrowser(url: String?) {
        if (url.isNullOrEmpty()) {
            return
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.trim()))
        if (!canHandleIntent(intent)) {
            Log.e(javaClass.simpleName, "Couldn't handle intent for URL $url")
            return
        }
        fragment.startActivity(intent)
    }

    private fun canHandleIntent(intent: Intent): Boolean {
        val manager = fragment.requireActivity().packageManager
        val infos = manager.queryIntentActivities(intent, 0)
        return infos.size > 0
    }
}