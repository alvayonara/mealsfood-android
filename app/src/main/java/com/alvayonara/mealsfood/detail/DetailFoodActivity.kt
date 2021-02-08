package com.alvayonara.mealsfood.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.utils.Helper.setDefaultStatusBarColor
import com.alvayonara.mealsfood.core.utils.Helper.setLightStatusBar
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.visible
import com.alvayonara.mealsfood.databinding.ActivityDetailFoodBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFoodActivity : AppCompatActivity() {

    private val detailFoodViewModel: DetailFoodViewModel by viewModel()
    private lateinit var binding: ActivityDetailFoodBinding

    companion object {
        const val EXTRA_FOOD_DATA = "extra_food_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initView()
    }

    private fun initView() {
        val foodData = intent.getParcelableExtra<Food>(EXTRA_FOOD_DATA)
        if (foodData != null) {
            populateFood(foodData)

            detailFoodViewModel.setSelectedFood(foodData.id)
            binding.progressBarFoodDetail.visible()
            detailFoodViewModel.foodDetail.observe(this, {
                binding.progressBarFoodDetail.gone()
                if (it != null) populateDetail(it)
            })
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        with(supportActionBar) {
            title = ""
            this?.setDisplayHomeAsUpEnabled(true)
        }
        setDefaultStatusBarColor(this)
        setLightStatusBar(this)
    }

    private fun populateFood(food: Food?) {
        food?.let {
            Glide.with(this)
                .load(it.thumb)
                .into(binding.ivFoodDetail)

            binding.tvFoodNameDetail.text = it.name

            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)
            binding.ivFoodFavoriteButton.setOnClickListener {
                statusFavorite = !statusFavorite
                detailFoodViewModel.setFavoriteFood(food, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun populateDetail(detail: List<Detail>?) {
        detail?.let {
            it[0].let {
                with(binding) {
                    tvFoodCategory.text = it.category
                    tvArea.text = it.area
                    tvTags.text = it.tags
                    tvFoodInstructions.text = it.instructions
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.ivFoodFavoriteButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.ivFoodFavoriteButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}