package com.dezzy.gceresults.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dezzy.gceresults.ui.components.FetchButton
import com.dezzy.gceresults.ui.components.LevelDropdown
import com.dezzy.gceresults.ui.components.NameInput
import com.dezzy.gceresults.ui.components.YearInput
import com.dezzy.gceresults.ui.screens.search.models.SearchField
import com.dezzy.gceresults.ui.screens.search.viewmodel.SearchViewModel

@Composable
fun SearchNameOnly(
    viewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen(
        viewModel = viewModel,
        fields = listOf(
            SearchField.NameField(),
            SearchField.YearField()
        )
    )
}
