package com.mouredev.tenerifegg.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

/**
 * Created by MoureDev by Brais Moure on 8/7/23.
 * www.mouredev.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterTextField(label: String,
                       text: String,
                       onTextChanged: (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        isError = text.isEmpty(),
        singleLine = true
    )
}