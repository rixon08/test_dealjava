package com.dealjava.test.features.unlockrecipe.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.dealjava.test.R
import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel

class RecipeAdapter (private var recipes: List<RecipeModel>,
    private val onItemClick: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRecipeName: TextView = itemView.findViewById(R.id.tvRecipeName)
        private val lottieAnimationView: LottieAnimationView = itemView.findViewById(R.id.lottieAnimationView)


        fun bind(recipe: RecipeModel) {
            tvRecipeName.text = recipe.name
            lottieAnimationView.setAnimation(recipe.lottieSrc)
            lottieAnimationView.playAnimation()
            itemView.setOnClickListener { onItemClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun setData(newList: List<RecipeModel>) {
        recipes = newList
        notifyDataSetChanged()
    }
}