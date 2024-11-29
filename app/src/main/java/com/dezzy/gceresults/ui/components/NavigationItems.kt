package com.dezzy.gceresults.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dezzy.gceresults.R

@Composable
fun navigationItems(): List<NavigationItem> {
    return listOf(
//        NavigationItem(
//            title = stringResource(R.string.app_nav_items_result),
//        ),
        NavigationItem(
            title = stringResource(R.string.app_nav_items_search_name_center),
        ),
        NavigationItem(
            title = stringResource(R.string.app_nav_items_search_name),
        ),
        NavigationItem(
            title = stringResource(R.string.app_nav_items_search_center_number),
        ),
        NavigationItem(
            title = stringResource(R.string.app_nav_items_search_center_name),
        ),
        NavigationItem(
            title = stringResource(R.string.app_nav_items_search_papers_passed),
        ),
    )
}

data class NavigationItem(
    val title: String,
)