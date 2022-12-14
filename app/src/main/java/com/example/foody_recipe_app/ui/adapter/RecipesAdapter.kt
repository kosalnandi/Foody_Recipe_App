package com.example.foody_recipe_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody_recipe_app.databinding.RecipesRowLayoutBinding
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.jsonModels.Result
import com.example.foody_recipe_app.ui.uitl.RecipesDiffUtil

class RecipesAdapter(): RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes = emptyList<Result>()

    inner class MyViewHolder(private val binding: RecipesRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

            fun bind(result: Result) {
                binding.result = result
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

       val binding = RecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
       return recipes.size
    }

    fun setData(newData: FoodRecipe) {
    val recipesDiffUtil = RecipesDiffUtil(recipes,newData.results)
    val diffCourses = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
    diffCourses.dispatchUpdatesTo(this)
    }

}