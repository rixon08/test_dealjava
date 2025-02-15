package com.dealjava.test.features.openpack.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dealjava.test.features.openpack.domain.usecases.SaveRecipeUseCase
import kotlinx.coroutines.launch

class OpenPackViewModel(
    private val saveRecipeUseCase: SaveRecipeUseCase
) : ViewModel() {
    fun saveRecipe(recipe: Pair<String, String>) {
        viewModelScope.launch {
            saveRecipeUseCase.invoke(recipe)
        }
    }
}