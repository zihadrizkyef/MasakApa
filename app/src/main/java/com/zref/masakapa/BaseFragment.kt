package com.zref.masakapa

import android.graphics.Color
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment: Fragment() {

    fun showErrorMessage(message: String, view: View) {
        val text = HtmlCompat.fromHtml(
            "<font color=\"#FFFFFF\">$message</font>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(Color.parseColor("#AA0000"))
        snackBar.show()
    }

}