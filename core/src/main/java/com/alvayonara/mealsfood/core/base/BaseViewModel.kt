package com.alvayonara.mealsfood.core.base

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    protected fun setLog(message: String) = Timber.e(message)

    protected fun constructRawRequest(jsonObj: JSONObject): JsonObject {
        return JsonParser().let { parser ->
            parser.parse(jsonObj.toString()) as JsonObject
        }
    }
}