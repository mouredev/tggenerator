package com.mouredev.tenerifegg.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by MoureDev by Brais Moure on 8/7/23.
 * www.mouredev.com
 */
@Composable
fun ActionButton(text: String,
                 description: String,
                 enabled: Boolean = true,
                 icon: ImageVector,
                 onClick: () -> Unit) {

    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        Text(text)

        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

        Icon(
            icon,
            contentDescription = description,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )

    }
}