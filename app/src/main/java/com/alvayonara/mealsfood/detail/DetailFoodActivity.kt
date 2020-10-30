package com.alvayonara.mealsfood.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.domain.model.Detail
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.ui.ViewModelFactory
import com.alvayonara.mealsfood.core.utils.ToolbarConfig
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_food.*

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var detailFoodViewModel: DetailFoodViewModel

    companion object {
        const val EXTRA_FOOD_DATA = "extra_food_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        detailFoodViewModel = ViewModelProvider(
            this,
            factory
        )[DetailFoodViewModel::class.java]

        initView()
    }

    private fun initView() {
        val foodData = intent.getParcelableExtra<Food>(EXTRA_FOOD_DATA)
        if (foodData != null) {
            populateFood(foodData)

            detailFoodViewModel.setSelectedFood(foodData.id)

            progress_bar_food_detail.visible()

            detailFoodViewModel.foodDetail.observe(this, {
                progress_bar_food_detail.gone()

                if (it != null) {
                    populateDetail(it)
                }
            })
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        with(supportActionBar) {
            title = ""
            this?.setDisplayHomeAsUpEnabled(true)
        }
        ToolbarConfig.setDefaultStatusBarColor(this)
    }

    private fun populateFood(food: Food?) {
        food?.let {
            Glide.with(this)
                .load(it.thumb)
                .into(iv_food_detail)

            tv_food_name_detail.text = it.name

            Log.v("ASW", it.isFavorite.toString())

            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)
            iv_food_favorite_button.setOnClickListener {
                statusFavorite = !statusFavorite
                detailFoodViewModel.setFavoriteFood(food, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun populateDetail(detail: List<Detail>?) {
        detail?.let {
            tv_food_category.text = it[0].category
            tv_area.text = it[0].area
            tv_tags.text = it[0].tags
            tv_food_instructions.text = it[0].instructions
            tv_youtube.text = it[0].youtube
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            iv_food_favorite_button.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            iv_food_favorite_button.setImageDrawable(
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