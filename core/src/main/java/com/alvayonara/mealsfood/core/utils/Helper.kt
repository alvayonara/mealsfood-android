package com.alvayonara.mealsfood.core.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

object Helper {

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

    fun hideKeyboard(act: Activity) {
        val view = act.currentFocus
        if (view != null) {
            val inputMethodManager =
                act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}