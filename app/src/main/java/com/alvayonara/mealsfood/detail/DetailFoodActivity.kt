package com.alvayonara.mealsfood.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.data.source.Resource
import com.alvayonara.mealsfood.core.domain.model.Food
import com.alvayonara.mealsfood.core.ui.ViewModelFactory
import com.alvayonara.mealsfood.core.utils.ToolbarConfig
import com.alvayonara.mealsfood.core.utils.gone
import com.alvayonara.mealsfood.core.utils.visible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_food.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var detailFoodViewModel: DetailFoodViewModel

    companion object {
        const val EXTRA_FOOD_ID = "extra_food_id"
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
        val extras = intent.extras
        if (extras != null) {
            val foodId = extras.getString(EXTRA_FOOD_ID)
            if (foodId != null) {
                detailFoodViewModel.setSelectedFood(foodId)

                detailFoodViewModel.foodDetail.observe(this, {
                    if (it != null) {
                        when (it) {
                            is Resource.Loading -> progress_bar_food_detail.visible()
                            is Resource.Success -> {
                                progress_bar_food_detail.gone()
                                populateFood(it.data)
                            }
                            is Resource.Error -> {
                                progress_bar_dashboard.gone()
                                Toast.makeText(this, "An Error Occured", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setDefaultStatusBarColor(this)
    }

    private fun populateFood(food: List<Food>?) {
        food?.let {
            Glide.with(this)
                .load(it[0].thumb)
                .into(iv_food_detail)

            tv_food_name_detail.text = it[0].name
            tv_food_category.text = it[0].category
            tv_area.text = it[0].area
            tv_tags.text = it[0].tags
            tv_food_instructions.text = it[0].instructions
            tv_youtube.text = it[0].youtube

            Log.v("ASW", it[0].isFavorite.toString())

            var statusFavorite = it[0].isFavorite
            setStatusFavorite(statusFavorite)
            iv_food_favorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailFoodViewModel.setFavoriteFood(food[0], statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            iv_food_favorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            iv_food_favorite.setImageDrawable(
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