package com.alvayonara.mealsfood.core.di

import androidx.room.Room
import com.alvayonara.mealsfood.core.BuildConfig
import com.alvayonara.mealsfood.core.data.source.FoodRepository
import com.alvayonara.mealsfood.core.data.source.local.LocalDataSource
import com.alvayonara.mealsfood.core.data.source.local.room.FoodDatabase
import com.alvayonara.mealsfood.core.data.source.remote.RemoteDataSource
import com.alvayonara.mealsfood.core.data.source.remote.network.ApiService
import com.alvayonara.mealsfood.core.domain.repository.IFoodRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FoodDatabase>().foodDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FoodDatabase::class.java, "Food.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL_TMDB}${BuildConfig.TMDB_KEY}/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IFoodRepository> { FoodRepository(get(), get()) }
}