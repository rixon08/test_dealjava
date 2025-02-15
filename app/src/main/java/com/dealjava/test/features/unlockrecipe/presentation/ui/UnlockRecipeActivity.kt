package com.dealjava.test.features.unlockrecipe.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dealjava.test.R
import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel
import com.dealjava.test.features.unlockrecipe.presentation.viewmodels.UnlockRecipeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnlockRecipeActivity : AppCompatActivity() {
    private lateinit var adapter: RecipeAdapter
    private val viewModel: UnlockRecipeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_unlock_recipe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvListRecipe: RecyclerView = findViewById(R.id.rvListRecipe)

        adapter = RecipeAdapter(listOf()) { recipe ->

        }
        rvListRecipe.layoutManager = LinearLayoutManager(this)
        rvListRecipe.adapter = adapter

        viewModel.unlockRecipes.observe(this) { unlockRecipes ->
            adapter.setData(unlockRecipes)
        }

        viewModel.getRecipe()
    }
}