package com.example.foody_recipe_app.ui.database

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverters


@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipesDao
}