package com.example.foody_recipe_app.ui.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id Asc "  )
    fun readRecipes(): Flow<List<RecipeEntity>> //doubt
}