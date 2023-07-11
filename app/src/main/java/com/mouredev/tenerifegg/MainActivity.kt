package com.mouredev.tenerifegg

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.mouredev.tenerifegg.ui.content.APIKeyRow
import com.mouredev.tenerifegg.ui.content.DataColumn
import com.mouredev.tenerifegg.ui.content.GeneratorColumn
import com.mouredev.tenerifegg.ui.content.InfoColumn
import com.mouredev.tenerifegg.ui.theme.TenerifeGGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se puede mejorar la implementación de solicitud de permisos
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        setContent {
            TenerifeGGTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content() {

    val context = LocalContext.current
    val viewModel = LogoGeneratorViewModel()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_stat_name),
                            contentDescription = "Icono",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Tenerife GG(enerator)",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary)
            )
        }
    ) { padding ->
        if (viewModel.loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
                .alpha(if (viewModel.loading) 0.5f else 1f)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            DataForm(context, viewModel)
        }
    }

}

@Composable
private fun DataForm(context: Context, viewModel: LogoGeneratorViewModel) {

    var apiKey by remember { mutableStateOf("") }

    var team by remember { mutableStateOf("") }
    var games by remember { mutableStateOf("") }
    var reference by remember { mutableStateOf("") }

    var recorderText by remember { mutableStateOf("Iniciar grabación") }

    val masked = remember { mutableStateOf(false) }
    var image by remember { mutableStateOf("") }

    // API Key
    APIKeyRow(context, viewModel, apiKey) {
        apiKey = it
    }

    if (!viewModel.apiError) {

        // 1
        DataColumn(team, games, reference,
            onTeamChanged = { team = it},
            onGamesChanged = { games = it},
            onReferenceChanged = { reference = it}
        )

        // 2
        InfoColumn(context, viewModel, recorderText, onRecorderTextChanged = { recorderText = it })


        // 3
        GeneratorColumn(context, viewModel, team, games, reference, masked.value, image,
            onMaskedChanged = { masked.value = it },
            onImageChanged = { image = it }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    TenerifeGGTheme {
        Content()
    }
}