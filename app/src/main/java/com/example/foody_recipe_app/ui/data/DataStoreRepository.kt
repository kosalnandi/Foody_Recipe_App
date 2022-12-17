package com.example.foody_recipe_app.ui.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.*
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.PREFERENCES_DIET_TYPE
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.PREFERENCES_NAME
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.PREFERENCE_MEAL_TYPE_ID
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context){

    private object PreferencesKey {
        val SelectedMealType = preferencesKey<String>(PREFERENCES_MEAL_TYPE)
        val SelectedMealTypeId = preferencesKey<Int>(PREFERENCE_MEAL_TYPE_ID)
        val SelectedDietType = preferencesKey<String>(PREFERENCES_DIET_TYPE)
        val SelectedDietTypeId = preferencesKey<Int>(PREFERENCES_DIET_TYPE_ID)


    }
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCES_NAME
    )

    //function for saving the values
    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preference->
            preference[PreferencesKey.SelectedMealType] = mealType
            preference[PreferencesKey.SelectedMealTypeId] = mealTypeId
            preference[PreferencesKey.SelectedDietType] = dietType
            preference[PreferencesKey.SelectedDietTypeId] = dietTypeId
        }
    }

    //doubt
    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType = preferences[PreferencesKey.SelectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferencesKey.SelectedMealTypeId] ?: 0
            val selectedDietType = preferences[PreferencesKey.SelectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[PreferencesKey.SelectedDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

}

//store the values in a class
data class MealAndDietType(
    val SelectedMealType: String,
    val SelectedMealTypeId: Int,
    val SelectedDietType: String,
    val SelectedDietTypeId: Int
)