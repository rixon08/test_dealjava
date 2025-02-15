package com.dealjava.test.core.di

import com.dealjava.test.core.datasources.LocalDataStorage
import com.dealjava.test.features.openpack.data.repositories.OpenPackRepositoryImpl
import com.dealjava.test.features.openpack.domain.repositories.OpenPackRepository
import com.dealjava.test.features.openpack.domain.usecases.SaveRecipeUseCase
import com.dealjava.test.features.openpack.presentation.viewmodels.OpenPackViewModel
import com.dealjava.test.features.unlockrecipe.data.repositories.UnlockRecipeRepositoryImpl
import com.dealjava.test.features.unlockrecipe.domain.repositories.UnlockRecipeRepository
import com.dealjava.test.features.unlockrecipe.domain.usecases.GetUnlockRecipUseCase
import com.dealjava.test.features.unlockrecipe.presentation.viewmodels.UnlockRecipeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        LocalDataStorage(androidContext())
    }

    single<OpenPackRepository> { OpenPackRepositoryImpl(get()) }
    single<UnlockRecipeRepository> { UnlockRecipeRepositoryImpl(get()) }

    single { SaveRecipeUseCase(get()) }
    single { GetUnlockRecipUseCase(get()) }

    viewModel { OpenPackViewModel(get()) }
    viewModel { UnlockRecipeViewModel(get()) }
}