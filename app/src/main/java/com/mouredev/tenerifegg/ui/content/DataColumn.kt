package com.mouredev.tenerifegg.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mouredev.tenerifegg.ui.components.CharacterTextField
import com.mouredev.tenerifegg.ui.components.TitleText

/**
 * Created by MoureDev by Brais Moure on 9/7/23.
 * www.mouredev.com
 */
@Composable
fun DataColumn(
    team: String,
    games: String,
    elements: String,
    onTeamChanged: (String) -> Unit,
    onGamesChanged: (String) -> Unit,
    onElementsChange: (String) -> Unit) {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TitleText("1. Completa los datos")

        CharacterTextField("Nombre del equipo", team, onTeamChanged)

        CharacterTextField("¿A qué juegas?", games, onGamesChanged)

        CharacterTextField("Referencia principal", elements, onElementsChange)
    }
}