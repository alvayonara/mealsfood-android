package com.alvayonara.mealsfood.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import timber.log.Timber

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setup()
        }
    }

    abstract fun setup()

    protected fun showToast(message: String) =
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()

    protected fun setLog(message: String) = Timber.e(message)

    protected open fun releaseData() {}
    protected open fun setupView() {}
    protected open fun setupRecyclerView() {}
    protected open fun subscribeViewModel() {}

    protected fun <T> LiveData<T>.onLiveDataResult(action: (T) -> Unit) {
        observe(this@BaseFragment) { data ->
            data?.let(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releaseData()
        _binding = null
    }
}