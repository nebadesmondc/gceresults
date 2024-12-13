package com.dezzy.gceresults.ui.screens.search.viewmodel

sealed class SearchScreenIntent {
    data class UpdateInput(val key: String, val value: String) : SearchScreenIntent()
    data class UpdateSelectedLevel(val level: String) : SearchScreenIntent()
    object FetchResults : SearchScreenIntent()
}