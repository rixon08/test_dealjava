package com.dealjava.test.features.unlockrecipe.domain.usecases

import com.dealjava.test.features.openpack.domain.repositories.OpenPackRepository
import com.dealjava.test.features.unlockrecipe.domain.repositories.UnlockRecipeRepository

class GetUnlockRecipUseCase(private val unlockRecipeRepository: UnlockRecipeRepository) {
    suspend operator fun invoke(): Map<Pair<String, String>, String> = unlockRecipeRepository.getUnlockRecipe()
}