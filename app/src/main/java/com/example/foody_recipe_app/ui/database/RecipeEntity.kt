package com.example.foody_recipe_app.ui.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.movieModels.JsonMovie
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipeEntity(
    var foodRecipe: JsonMovie
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}