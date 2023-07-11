package com.mouredev.tenerifegg.ui.content

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mouredev.tenerifegg.LogoGeneratorViewModel
import com.mouredev.tenerifegg.ui.components.ActionButton
import com.mouredev.tenerifegg.ui.components.TitleText

/**
 * Created by MoureDev by Brais Moure on 9/7/23.
 * www.mouredev.com
 */
@Composable
fun InfoColumn(context: Context,
               viewModel: LogoGeneratorViewModel,
               recorderText: String,
               onRecorderTextChanged: (String) -> Unit) {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText("2. Añade información")

        ActionButton(recorderText, "Grabación", icon = Icons.Filled.Mic) {
            onRecorderTextChanged(if (viewModel.recording) "Iniciar grabación" else "Detener grabación")
            viewModel.recordAudio(context)
        }

        if (viewModel.info.isNotEmpty()) {

            ActionButton("Resumir", "Resumen", icon = Icons.Filled.Compress) {
                viewModel.createInfoSummary()
            }

            Text(viewModel.info)
        }
    }
}