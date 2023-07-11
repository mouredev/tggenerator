package com.mouredev.tenerifegg.ui.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mouredev.tenerifegg.LogoGeneratorViewModel
import com.mouredev.tenerifegg.ui.components.ActionButton
import com.mouredev.tenerifegg.ui.components.TitleText

/**
 * Created by MoureDev by Brais Moure on 9/7/23.
 * www.mouredev.com
 */
@Composable
fun GeneratorColumn(context: Context,
                            viewModel: LogoGeneratorViewModel,
                            team: String, games: String, reference: String,
                            masked: Boolean, image: String,
                            onMaskedChanged: (Boolean) -> Unit,
                            onImageChanged: (String) -> Unit) {

    val clipboard = LocalClipboardManager.current

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText("3. Genera el logo")

        ActionButton(
            "Generar",
            "Genera el logo",
            team.isNotEmpty() && games.isNotEmpty() && reference.isNotEmpty(),
            Icons.Filled.Draw
        ) {
            viewModel.generateLogo(context, masked, games, reference, onImageChanged)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Diseño Tenerife GG")

            Spacer(modifier = Modifier.width(8.dp))

            Switch(
                checked = masked,
                onCheckedChange = onMaskedChanged
            )
        }

        if (image.isNotEmpty()) {

            AsyncImage(
                model = image,
                contentDescription = "$team logo",
                modifier = Modifier.fillMaxSize()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                IconButton(
                    onClick = {
                        clipboard.setText(AnnotatedString(image))
                        Toast.makeText(context, "URL copiada", Toast.LENGTH_LONG).show()
                    }
                ) {
                    Icon(
                        Icons.Filled.ContentCopy,
                        contentDescription = "Copiar URL",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "MoureDev",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            }
        }

        if (viewModel.error) {
            Text("Error generando la imagen", color = Color.Red)
        }
    }
}