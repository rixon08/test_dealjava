package com.dealjava.test.features.openpack.data.repositories

import com.dealjava.test.core.datasources.LocalDataStorage
import com.dealjava.test.features.openpack.domain.repositories.OpenPackRepository

class OpenPackRepositoryImpl (
    private val localDataStorage: LocalDataStorage
) : OpenPackRepository {

    override suspend fun saveRecipe(recipe: Pair<String, String>) {
        localDataStorage.saveRecipe(recipe)
    }
}