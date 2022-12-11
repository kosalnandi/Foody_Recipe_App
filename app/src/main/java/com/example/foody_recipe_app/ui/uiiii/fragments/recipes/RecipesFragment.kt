package com.example.foody_recipe_app.ui.uiiii.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody_recipe_app.R
import com.example.foody_recipe_app.databinding.FragmentRecipesBinding
import com.example.foody_recipe_app.ui.viewModels.MainViewModel
import com.example.foody_recipe_app.ui.adapter.RecipesAdapter
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.API_KEY
import com.example.foody_recipe_app.ui.uitl.NetworkResult
import com.example.foody_recipe_app.ui.viewModels.RecipesViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    //binding fragmentRecipes xml
    private lateinit var binding: FragmentRecipesBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModels: RecipesViewModels
    private val mAdapter by lazy { RecipesAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModels = ViewModelProvider(requireActivity()).get(RecipesViewModels::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        //data binding with fragmentRecipe  and mainViewModel
        binding.mainViewModel = mainViewModel

        setUpRecyclerView()
        readDatabase()
        return binding.root


    }
    private fun setUpRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun readDatabase() {
       lifecycleScope.launch {
           mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
               if (database.isNotEmpty()) {
                   Log.d("RecipesFragment", "readDatabase called!!!")
                   mAdapter.setData(database[0].foodRecipe)
               }
               else {
                   requestApiData()
               }
           }
       }
    }

    private fun requestApiData() {
        Log.d("RecipesFragment", "request Api Data called!!!")
            mainViewModel.getRecipes(recipesViewModels.applyQueries())
            mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {mAdapter.setData(it) }
                    }
                    is NetworkResult.Error -> {
                        loadDataFromCache()
                        Toast.makeText(requireContext(),response.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Loading -> {
                        Toast.makeText(requireContext(),"loading",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner){ database ->
                val data = if(database.isNotEmpty())
                    database[0].foodRecipe
                else
                    FoodRecipe(emptyList())
                mAdapter.setData(data)

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding
    }

}
