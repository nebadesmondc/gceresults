package com.dezzy.gceresults.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dezzy.gceresults.R

@Composable
fun CenterNumberInput(
    currentInput: String,
    onValueChanged: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = currentInput,
            onValueChange = {
                if (it.length <= 5) {
                    onValueChanged(it)
                }
            },
            label = { Text(text = stringResource(R.string.text_field_center_number)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        val remainingCharacters = 5 - currentInput.length
        Text(
            text = "$remainingCharacters "+ stringResource(R.string.text_field_characters_left),
            style = MaterialTheme.typography.bodySmall,
            color = if (remainingCharacters < 0) Color.Red else Color.Gray
        )
    }
}

@Composable
fun CenterNameInput(centerName: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = centerName,
        onValueChange = onValueChanged,
        label = { Text(stringResource(R.string.text_field_school_name)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NameInput(name: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onValueChanged,
        label = { Text(stringResource(R.string.text_field_name)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun YearInput(year: String, onValueChanged: (String) -> Unit) {
    TextField(
        value = year,
        onValueChange = { input ->
            if (input.length <= 4 && input.all { it.isDigit() }) {
                onValueChanged(input)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("Year eg. 2021") },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelDropdown(selectedLevel: String, onLevelChanged: (String) -> Unit) {
    val levels = listOf(
        "A-Level General",
        "A-Level Technical",
        "O-Level General",
        "O-Level Technical"
    )
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
        TextField(
            readOnly = true,
            value = selectedLevel,
            onValueChange = {},
            label = { Text(stringResource(R.string.text_field_select_level)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
            levels.forEach {level ->
                DropdownMenuItem(onClick = {
                    onLevelChanged(level)
                    expanded = false
                },
                    text = { Text(level) }
                )
            }
        }
    }
}

@Composable
fun FetchButton(onClick: () -> Unit, enabled: Boolean = false) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(stringResource(R.string.button_fetch_result))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TextFieldsPreview() {
    FetchButton({})
}