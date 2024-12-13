package com.dezzy.gceresults.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dezzy.gceresults.ui.components.CenterNameInput
import com.dezzy.gceresults.ui.components.CenterNumberInput
import com.dezzy.gceresults.ui.components.FetchButton
import com.dezzy.gceresults.ui.components.LevelDropdown
import com.dezzy.gceresults.ui.components.NameInput
import com.dezzy.gceresults.ui.components.YearInput
import com.dezzy.gceresults.ui.screens.search.models.SearchField
import com.dezzy.gceresults.ui.screens.search.viewmodel.SearchScreenIntent
import com.dezzy.gceresults.ui.screens.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    fields: List<SearchField>
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        fields.forEach { searchField ->
            when (searchField) {
                is SearchField.CenterNumberField -> CenterNumberInput(
                    currentInput = state.inputs[searchField.key] ?: "",
                    onValueChanged = { viewModel.processIntent(SearchScreenIntent.UpdateInput(searchField.key, it)) }
                )
                is SearchField.CenterNameField -> CenterNameInput(
                    centerName = state.inputs[searchField.key] ?: "",
                    onValueChanged = { viewModel.processIntent(SearchScreenIntent.UpdateInput(searchField.key, it)) }
                )
                is SearchField.NameField -> NameInput(
                    name = state.inputs[searchField.key] ?: "",
                    onValueChanged = { viewModel.processIntent(SearchScreenIntent.UpdateInput(searchField.key, it)) }
                )
                is SearchField.YearField -> YearInput(
                    year = state.inputs[searchField.key] ?: "",
                    onValueChanged = { viewModel.processIntent(SearchScreenIntent.UpdateInput(searchField.key, it)) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        LevelDropdown(
            selectedLevel = state.selectedLevel,
            onLevelChanged = { viewModel.processIntent(SearchScreenIntent.UpdateSelectedLevel(it)) }
        )

        Spacer(Modifier.height(16.dp))

        FetchButton(
            onClick = { viewModel.processIntent(SearchScreenIntent.FetchResults) },
            enabled = state.isFetchEnabled
        )

        Spacer(Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null){
            Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
        }
    }
}
