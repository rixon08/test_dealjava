package com.dealjava.test.core.utils

import com.dealjava.test.core.constants.IngredientConstants.Companion.listIngredient
import com.dealjava.test.core.models.IngredientModel

class SearchIngredient {
    companion object {
        fun searchIngredientByName(name: String): IngredientModel? {
            val index = listIngredient.indexOfFirst { it.name == name }
            return if (index >= 0) {
                listIngredient[index]
            } else {
                null
            }
        }
    }
}