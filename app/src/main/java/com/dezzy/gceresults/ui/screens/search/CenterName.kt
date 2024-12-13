package com.dezzy.gceresults.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dezzy.gceresults.ui.screens.search.models.SearchField
import com.dezzy.gceresults.ui.screens.search.viewmodel.SearchViewModel

@Composable
fun SearchCenterName(
    viewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen(
        viewModel = viewModel,
        fields = listOf(
            SearchField.CenterNameField(),
            SearchField.YearField()
        )
    )
}
