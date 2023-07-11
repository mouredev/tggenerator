package com.mouredev.tenerifegg.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by MoureDev by Brais Moure on 8/7/23.
 * www.mouredev.com
 */

@Composable
fun TitleText(text: String) {
    Text(text = text.uppercase(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
}