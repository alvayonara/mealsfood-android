package com.alvayonara.mealsfood.settings

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.alvayonara.mealsfood.BuildConfig
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.base.BaseFragment
import com.alvayonara.mealsfood.core.utils.IOnBackPressed
import com.alvayonara.mealsfood.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.dialog_about_apps.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(), IOnBackPressed {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

    override fun setup() {
        setupView()
    }

    override fun setupView() {
        binding.lytSettingsAbout.setOnClickListener {
            val dialog = Dialog(requireActivity()).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.dialog_about_apps)
                setCancelable(true)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

            val layoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window?.attributes)
                width = WindowManager.LayoutParams.WRAP_CONTENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
            }

            dialog.apply {
                "Version ${BuildConfig.VERSION_NAME}".also { tv_version.text = it }
                btn_about_developer.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(getString(R.string.linkedin_url))
                    }
                    startActivity(intent)
                }
                btn_close_dialog.setOnClickListener { dialog.dismiss() }
                show()
                window?.attributes = layoutParams
            }
        }
    }

    override fun onBackPressed(): Boolean = false
}