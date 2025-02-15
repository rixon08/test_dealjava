package com.dealjava.test.features.unlockrecipe.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dealjava.test.core.constants.IngredientConstants
import com.dealjava.test.core.utils.SearchIngredient
import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel
import com.dealjava.test.features.unlockrecipe.domain.usecases.GetUnlockRecipUseCase
import kotlinx.coroutines.launch

class UnlockRecipeViewModel (
    val getUnlockRecipUseCase: GetUnlockRecipUseCase
) : ViewModel() {

    private val _unlockRecipes = MutableLiveData<List<RecipeModel>>()
    val unlockRecipes: LiveData<List<RecipeModel>> get() = _unlockRecipes

    fun getRecipe() {
        viewModelScope.launch {
            val resultData = getUnlockRecipUseCase.invoke().toList()
            if (resultData.isEmpty()){
                _unlockRecipes.value = listOf()
            } else {
                var listData: ArrayList<RecipeModel> = arrayListOf()
                for (recipe in resultData) {
                    val ing1 = SearchIngredient.searchIngredientByName(recipe.first.first)
                    val ing2 = SearchIngredient.searchIngredientByName(recipe.first.second)
                    if (ing1 != null && ing2 != null) {
                        listData.add(
                            RecipeModel(
                                ingredientModel1 = ing1,
                                ingredientModel2 = ing2,
                                name = recipe.second
                            )
                        )
                    }
                }
                _unlockRecipes.value = listData
            }

        }
    }
}