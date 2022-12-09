package com.example.foody_recipe_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody_recipe_app.databinding.RecipesRowLayoutBinding
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.movieModels.Item
import com.example.foody_recipe_app.ui.movieModels.JsonMovie
import com.example.foody_recipe_app.ui.uitl.RecipesDiffUtil

class RecipesAdapter(): RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var movies = emptyList<Item>()

    inner class MyViewHolder(private val binding: RecipesRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Item) {
                binding.item = item
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

       val binding = RecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentRecipe = movies[position]
        holder.bind(currentRecipe)

    }

    override fun getItemCount(): Int {
       return movies.size
    }

    fun setData(newData: JsonMovie) {
    val recipesDiffUtil = RecipesDiffUtil(movies,newData.items)
    val diffCourses = DiffUtil.calculateDiff(recipesDiffUtil)
        movies = newData.items
    diffCourses.dispatchUpdatesTo(this)
    }

}