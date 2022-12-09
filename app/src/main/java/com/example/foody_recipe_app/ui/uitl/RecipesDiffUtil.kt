package com.example.foody_recipe_app.ui.uitl

import androidx.recyclerview.widget.DiffUtil
import com.example.foody_recipe_app.ui.jsonModels.Result
import com.example.foody_recipe_app.ui.movieModels.Item

class RecipesDiffUtil(
    private val oldList: List<Item>,
    private val newList: List<Item>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return  newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}