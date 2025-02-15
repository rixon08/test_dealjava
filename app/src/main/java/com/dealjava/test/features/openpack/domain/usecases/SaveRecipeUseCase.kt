package com.dealjava.test.features.openpack.domain.usecases

import com.dealjava.test.features.openpack.domain.repositories.OpenPackRepository

class SaveRecipeUseCase(private val openRepository: OpenPackRepository) {
    suspend operator fun invoke(recipe: Pair<String, String>) = openRepository.saveRecipe(recipe)
}