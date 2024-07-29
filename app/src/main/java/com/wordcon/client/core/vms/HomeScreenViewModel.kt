package com.wordcon.client.core.vms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wordcon.client.R
import com.wordcon.client.core.network.entities.GameCategory
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val _categories = mutableStateListOf<GameCategory>()
    val categories = _categories as List<GameCategory>
    private var isInitialized = false

    fun fetchCategories() {
        viewModelScope.launch {
            if (!isInitialized) {
                val categoriesList = listOf( // stub data
                    GameCategory(
                        id = 0,
                        name = R.string.cities,
                        image = R.drawable.moscow
                    ),
                    GameCategory(
                        id = 1,
                        name = R.string.animals,
                        image = R.drawable.capybara
                    ),
                    GameCategory(
                        id = 2,
                        name = R.string.food,
                        image = R.drawable.food
                    ),
                )
                _categories.addAll(categoriesList)
                isInitialized = true
            }
        }
    }
}