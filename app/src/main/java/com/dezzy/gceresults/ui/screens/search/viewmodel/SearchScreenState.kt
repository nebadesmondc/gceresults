package com.dezzy.gceresults.ui.screens.search.viewmodel

data class SearchScreenState(
    val inputs: Map<String, String> = emptyMap(),
    val selectedLevel: String = "Select Level",
    val isFetchEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val results: List<String> = emptyList(),
    val error: String? = null
)
