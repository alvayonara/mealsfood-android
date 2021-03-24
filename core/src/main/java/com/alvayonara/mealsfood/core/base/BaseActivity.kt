package com.alvayonara.mealsfood.core.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import timber.log.Timber

abstract class
BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadInjector()
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup()
    }

    abstract fun loadInjector()
    abstract fun setup()

    protected fun getBundle() = intent.extras

    protected fun showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    protected fun setLog(message: String) = Timber.e(message)

    protected fun hideKeyboard(act: Activity) {
        val view = act.currentFocus
        if (view != null) {
            val inputMethodManager =
                act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected open fun releaseData() {}
    protected open fun setupFormValidation() {}
    protected open fun setupDialog() {}
    protected open fun setupView() {}
    protected open fun setupRecyclerView() {}
    protected open fun subscribeViewModel() {}

    protected fun <T> LiveData<T>.onLiveDataResult(action: (T) -> Unit) {
        observe(this@BaseActivity) { data ->
            data?.let(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseData()
        _binding = null
    }
}