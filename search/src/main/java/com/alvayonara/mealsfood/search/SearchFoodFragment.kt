package com.alvayonara.mealsfood.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiResponse
import com.alvayonara.mealsfood.core.domain.model.FoodRecentSearch
import com.alvayonara.mealsfood.core.ui.FoodAdapter
import com.alvayonara.mealsfood.core.ui.FoodRecentSearchAdapter
import com.alvayonara.mealsfood.core.utils.*
import com.alvayonara.mealsfood.core.utils.Helper.hideKeyboard
import com.alvayonara.mealsfood.core.utils.Helper.setToast
import com.alvayonara.mealsfood.di.searchFoodModule
import com.alvayonara.search.databinding.FragmentSearchFoodBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.view_search_food.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.*
import java.util.concurrent.TimeUnit

class SearchFoodFragment : Fragment(), IOnBackPressed {

    private val searchFoodViewModel: SearchFoodViewModel by viewModel()

    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodRecentSearchAdapter: FoodRecentSearchAdapter

    private var _binding: FragmentSearchFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(searchFoodModule)
        if (activity != null) {
            initView()
            initAdapter()
            initRecyclerView()
            initEditTextStream()
            subscribeVm()
        }
    }

    private fun initView() {
        binding.edtSearchFood.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || keyEvent.action == KeyEvent.ACTION_DOWN
                || keyEvent.action == KeyEvent.KEYCODE_ENTER
            ) {
                hideKeyboard(requireActivity())
                startSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun startSearch() {
        binding.edtSearchFood.clearFocus()
        val search = binding.edtSearchFood.text.toString()
        if (search.isNotEmpty()) insertToRecentSearch(search)
    }

    private fun initAdapter() {
        foodAdapter = FoodAdapter(FoodAdapter.TYPE_LIST).apply {
            onItemClick = {
                insertToRecentSearch(it.strMeal.orEmpty())
                val nav =
                    SearchFoodFragmentDirections.actionSearchFoodFragmentToDetailFoodFragment(it)
                navigate(nav)
            }
        }

        foodRecentSearchAdapter = FoodRecentSearchAdapter().apply {
            onItemClick = {
                hideKeyboard(requireActivity())
                setEditText(it.strMeal)
                insertToRecentSearch(it.strMeal)
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.rvSearchFoods) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }

        with(binding.viewSearchFoodLayout.rvRecentSearchFood) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodRecentSearchAdapter
        }
    }

    @SuppressLint("CheckResult")
    private fun initEditTextStream() {
        searchFoodViewModel.fromView(binding.edtSearchFood)
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotEmpty() }
            .map { text -> text.toLowerCase(Locale.ROOT).trim() }
            .distinctUntilChanged()
            .switchMap { s -> Observable.just(s) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                searchFoodViewModel.setSelectedSearch(query)
            }
    }

    private fun subscribeVm() {
        searchFoodViewModel.recentSearchFood.observe(viewLifecycleOwner, {
            foodRecentSearchAdapter.setRecentSearchFoods(it)
        })

        searchFoodViewModel.searchFood.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is ApiResponse.Success -> {
                        binding.progressBarSearchFood.gone()
                        foodAdapter.setFoods(it.data)
                    }
                    is ApiResponse.Error -> {
                        binding.progressBarSearchFood.gone()
                        setToast(it.errorMessage, requireActivity())
                    }
                    is ApiResponse.Empty -> {
                        binding.progressBarSearchFood.gone()
                        binding.rvSearchFoods.gone()
                        binding.viewSearchFoodNotFound.root.visible()
                    }
                }
            }
        })

        searchFoodViewModel.loading.observe(viewLifecycleOwner, {
            if (it)
                binding.progressBarSearchFood.visible()
            else
                binding.progressBarSearchFood.gone()
        })

        searchFoodViewModel.isEditTextEmpty.observe(viewLifecycleOwner, {
            binding.viewSearchFoodNotFound.root.gone()
            binding.rvSearchFoods.visible()
            foodAdapter.clearFoods()

            if (it)
                binding.viewSearchFoodLayout.root.visible()
            else
                binding.viewSearchFoodLayout.root.gone()
        })
    }

    private fun setEditText(search: String) {
        with(binding.edtSearchFood) {
            setText(search)
            clearFocus()
        }
    }

    private fun insertToRecentSearch(search: String) =
        searchFoodViewModel.insertRecentSearchFood(FoodRecentSearch(0, search))

    override fun onBackPressed(): Boolean = false

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard(requireActivity())
        binding.rvSearchFoods.adapter = null
        binding.viewSearchFoodLayout.rvRecentSearchFood.adapter = null
        _binding = null
    }
}