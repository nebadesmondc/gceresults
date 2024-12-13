package com.dezzy.gceresults.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dezzy.gceresults.ui.screens.search.SearchCenterName
import com.dezzy.gceresults.ui.screens.search.SearchCenterNumber
import com.dezzy.gceresults.ui.screens.search.SearchNameAndCenter
import com.dezzy.gceresults.ui.screens.search.SearchNameOnly
import kotlinx.coroutines.launch

@Composable
fun TabRow(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    val tabItems = navigationItems()
    val pagerState = rememberPagerState {
        tabItems.size
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        onTabSelected(pagerState.currentPage)
    }
    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex != pagerState.currentPage && !pagerState.isScrollInProgress) {
            pagerState.scrollToPage(selectedTabIndex)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabItems.forEachIndexed {index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(item.title) },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedContentColor = MaterialTheme.colorScheme.primary
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) { index ->
            when(index) {
                0 -> SearchNameAndCenter()
                1 -> SearchNameOnly()
                2 -> SearchCenterNumber()
                3 -> SearchCenterName()
            }
        }
    }
}
