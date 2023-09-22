package com.example.dailydietapplication.di

import com.example.dailydietapplication.data.api.MealAPI
import com.example.dailydietapplication.data.repository.MealRepositoryImpl
import com.example.dailydietapplication.domain.repository.MealRepository
import com.example.dailydietapplication.domain.usecase.GetMealsUseCase
import com.example.dailydietapplication.domain.usecase.RemoveMealUseCase
import com.example.dailydietapplication.domain.usecase.SetMealUseCase
import com.example.dailydietapplication.domain.usecase.UpdateMealUseCase
import com.example.dailydietapplication.utils.ConstVariables
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( ViewModelComponent::class)
object AppModule {
    @Provides
    fun providerRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().addInterceptor{ chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Cookie", "sessionId=bc1b10bd-ce42-49f3-97b2-59e38c09e81c")
                .build()

            chain.proceed(request)
        }.build()

        return Retrofit.Builder()
            .baseUrl(ConstVariables.BASE_URL)
            .client(httpClient)
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    @Provides
    fun providerMealAPI( retrofit: Retrofit ): MealAPI {
        return retrofit.create(MealAPI::class.java)
    }

    @Provides
    fun providerMealRepository( mealAPI: MealAPI ): MealRepository {
        return MealRepositoryImpl(mealAPI)
    }

    @Provides
    fun providerMealUseCase( mealRepository: MealRepository ): GetMealsUseCase {
        return GetMealsUseCase(mealRepository)
    }

    @Provides
    fun providerMealSetUseCase( mealRepository: MealRepository ): SetMealUseCase {
        return SetMealUseCase(mealRepository)
    }

    @Provides
    fun providerMealUpdateUseCase( mealRepository: MealRepository ): UpdateMealUseCase {
        return UpdateMealUseCase(mealRepository)
    }

    @Provides
    fun providerMealRemoveUseCase( mealRepository: MealRepository ): RemoveMealUseCase {
        return RemoveMealUseCase(mealRepository)
    }
}