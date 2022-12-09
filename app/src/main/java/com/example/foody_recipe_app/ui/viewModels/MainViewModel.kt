package com.example.foody_recipe_app.ui.viewModels


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.foody_recipe_app.ui.data.Repository
import com.example.foody_recipe_app.ui.database.RecipeEntity
import com.example.foody_recipe_app.ui.movieModels.JsonMovie
import com.example.foody_recipe_app.ui.uitl.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    /**Room Database*/


    val readRecipes: LiveData<List<RecipeEntity>>
        get() = repository.local.readDatabase().asLiveData()

    private fun insertRecipe(recipeEntity: RecipeEntity) =
        viewModelScope.launch() {
            repository.local.insertRecipe(recipeEntity)
        }

    /** Retrofit*/
    var recipesResponse: MutableLiveData<NetworkResult<JsonMovie>> = MutableLiveData()

    fun getRecipes() = viewModelScope.launch {
        getRecipesSafeCall()
    }

    private suspend fun getRecipesSafeCall() {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes()
                recipesResponse.value = handleFoodRecipesResponse(response)

                val jsonMovie = recipesResponse.value!!.data
                if(jsonMovie != null) {
                    offlineCacheRecipes(jsonMovie)
                }
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error("Recipes not found.")
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun offlineCacheRecipes(jsonMovie: JsonMovie) {
        val recipeEntity = RecipeEntity(jsonMovie)
        insertRecipe(recipeEntity)
    }

    private fun handleFoodRecipesResponse(response: Response<JsonMovie>): NetworkResult<JsonMovie>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.items.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}