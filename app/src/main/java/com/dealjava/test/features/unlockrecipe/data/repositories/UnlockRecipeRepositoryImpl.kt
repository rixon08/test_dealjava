package com.dealjava.test.features.unlockrecipe.data.repositories

import com.dealjava.test.core.datasources.LocalDataStorage
import com.dealjava.test.features.unlockrecipe.domain.repositories.UnlockRecipeRepository

class UnlockRecipeRepositoryImpl (
    private val localDataStorage: LocalDataStorage
) : UnlockRecipeRepository {
    override suspend fun getUnlockRecipe(): Map<Pair<String, String>, String> {
        return localDataStorage.getUnlockRecipe()
    }
}