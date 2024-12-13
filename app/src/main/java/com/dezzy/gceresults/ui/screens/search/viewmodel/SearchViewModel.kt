package com.dezzy.gceresults.ui.screens.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(SearchScreenState())
    val state: StateFlow<SearchScreenState> = _state

    fun processIntent(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.UpdateInput -> updateInput(intent.key, intent.value)
            is SearchScreenIntent.UpdateSelectedLevel -> updateSelectedLevel(intent.level)
            SearchScreenIntent.FetchResults -> fetchResults()
        }
    }

    private fun updateInput(key: String, value: String) {
        val updatedInputs = _state.value.inputs.toMutableMap().apply { this[key] = value }
        _state.value = _state.value.copy(
            inputs = updatedInputs,
            isFetchEnabled = validateInputs(updatedInputs, _state.value.selectedLevel)
        )
    }

    private fun updateSelectedLevel(level: String) {
        _state.value = _state.value.copy(
            selectedLevel = level,
            isFetchEnabled = validateInputs(_state.value.inputs, level)
        )
    }

    private fun validateInputs(inputs: Map<String, String>, selectedLevel: String): Boolean {
        return inputs.values.all { it.isNotEmpty() } && selectedLevel != "Select Level"
    }

    private fun fetchResults() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {  }
    }
}