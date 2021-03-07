package com.alvayonara.mealsfood.settings

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.alvayonara.mealsfood.BuildConfig
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.utils.IOnBackPressed
import com.alvayonara.mealsfood.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.dialog_about_apps.*

class SettingsFragment : Fragment(), IOnBackPressed {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) initView()
    }

    private fun initView() {
        binding.lytSettingsAbout.setOnClickListener {
            val dialog = Dialog(requireActivity()).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.dialog_about_apps)
                setCancelable(true)
                window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

            val layoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window!!.attributes)
                width = WindowManager.LayoutParams.WRAP_CONTENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
            }

            dialog.tv_version.text = "Version ${BuildConfig.VERSION_NAME}"
            dialog.btn_about_developer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://www.linkedin.com/in/alva-yonara-puramandya/")
                }
                startActivity(intent)
            }
            dialog.btn_close_dialog.setOnClickListener { dialog.dismiss() }
            dialog.show()
            dialog.window!!.attributes = layoutParams
        }
    }

    override fun onBackPressed(): Boolean = false

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}