package com.mouredev.tenerifegg.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by MoureDev by Brais Moure on 8/7/23.
 * www.mouredev.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterTextField(label: String, text: String, onValueChange: (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = text.isEmpty()
    )
}