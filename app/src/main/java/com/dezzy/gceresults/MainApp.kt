package com.dezzy.gceresults

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dezzy.gceresults.ui.components.AppBar
import com.dezzy.gceresults.ui.components.TabRow
import com.dezzy.gceresults.ui.components.navigationItems
import kotlinx.coroutines.launch

@Composable
fun GceResultsApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val items = navigationItems()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        var expanded by remember {
            mutableStateOf(false)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    },
                    onMoreIconClicked = {
                        expanded = true
                    },
                    expanded = expanded,
                    onDismiss = {
                        expanded = false
                    },
                    onOptionSelected = {
                        expanded = false
                    }
                )
            }
        ) { innerPadding ->
            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(modifier = Modifier.height(16.dp))
                        items.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                label = {
                                    Text(text = item.title)
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                                    .width(250.dp)
                            )
                        }
                    }
                },
                drawerState = drawerState,
                modifier = Modifier.padding(innerPadding)
            ) {
                TabRow(selectedTabIndex = selectedItemIndex, onTabSelected = { index ->
                    selectedItemIndex = index
                })
            }

        }
    }
}
