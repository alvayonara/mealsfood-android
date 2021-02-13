package com.alvayonara.mealsfood.core.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

object Helper {

    fun setDefaultStatusBarColor(act: Activity) {
        with(act.window) {
            // Set status bar color to white
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = ContextCompat.getColor(act, android.R.color.white)
        }
    }

    fun setLightStatusBar(act: Activity) {
        // set light status bar (android M or up)
        val view = act.findViewById<View>(android.R.id.content)
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
    }

    fun dpToPx(c: Context, dp: Int): Int {
        val r = c.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }

    fun setToast(message: String, context: Context) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun showKeyboard(act: Activity, editText: EditText) {
        (act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            showSoftInput(editText, 0)
        }
    }

    fun hideKeyboard(act: Activity) {
        val view = act.currentFocus
        if (view != null) {
            val inputMethodManager =
                act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}