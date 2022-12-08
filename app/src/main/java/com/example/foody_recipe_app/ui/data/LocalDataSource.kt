package com.example.foody_recipe_app.ui.data


import com.example.foody_recipe_app.ui.database.RecipeEntity
import com.example.foody_recipe_app.ui.database.RecipesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase(): Flow<List<RecipeEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipe(recipeEntity: RecipeEntity) {
        recipesDao.insertRecipes(recipeEntity)
    }
}