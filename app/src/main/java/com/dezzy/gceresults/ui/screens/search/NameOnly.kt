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
import com.dezzy.gceresults.ui.components.CenterNumberInput
import com.dezzy.gceresults.ui.components.FetchButton
import com.dezzy.gceresults.ui.components.LevelDropdown
import com.dezzy.gceresults.ui.components.NameInput
import com.dezzy.gceresults.ui.components.YearInput

@Composable
fun SearchNameOnly() {
    var name by remember { mutableStateOf("") }
    var selectedLevel by remember { mutableStateOf("Select Level") }
    var year by remember { mutableStateOf("") }

    fun checkEnabled(): Boolean {
        return name.isNotEmpty() && selectedLevel != "Select Level" && year.isNotEmpty()
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(16.dp))
        NameInput(name = name, onValueChanged = { name = it })
        Spacer(Modifier.height(16.dp))
        LevelDropdown(selectedLevel = selectedLevel, onLevelChanged = { selectedLevel = it })
        Spacer(Modifier.height(16.dp))
        YearInput(year = year, onValueChanged = { year = it })
        Spacer(Modifier.height(16.dp))
        FetchButton({}, checkEnabled())
    }
}