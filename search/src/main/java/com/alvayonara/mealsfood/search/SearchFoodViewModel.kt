package com.alvayonara.mealsfood.search

import android.text.TextUtils
import android.widget.EditText
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.mealsfood.core.base.BaseViewModel
import com.alvayonara.mealsfood.core.domain.model.FoodRecentSearch
import com.alvayonara.mealsfood.core.domain.usecase.FoodUseCase
import com.alvayonara.mealsfood.core.utils.EditTextStream
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SearchFoodViewModel(private val foodUseCase: FoodUseCase) : BaseViewModel() {

    private val search = MutableLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    private val _isEditTextEmpty = MutableLiveData<Boolean>()
    val isEditTextEmpty = _isEditTextEmpty

    fun setSelectedSearch(search: String) {
        this.search.value = search
    }

    val searchFood = Transformations.switchMap(search) {
        LiveDataReactiveStreams.fromPublisher(foodUseCase.searchFood(it))
    }

    fun fromView(editText: EditText): Observable<String> {
        val subject = PublishSubject.create<String>()

        val searchStream = object : EditTextStream() {
            override fun onChanged(text: String) {
                if (!TextUtils.isEmpty(text)) {
                    _loading.postValue(true)
                    _isEditTextEmpty.postValue(false)
                } else {
                    _loading.postValue(false)
                    _isEditTextEmpty.postValue(true)
                }
                subject.onNext(text)
            }
        }
        editText.addTextChangedListener(searchStream)

        return subject
    }

    val recentSearchFood = LiveDataReactiveStreams.fromPublisher(foodUseCase.getRecentSearchFood())

    fun insertRecentSearchFood(search: FoodRecentSearch) =
        foodUseCase.insertRecentSearchFood(search)
}