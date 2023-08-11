package com.mouredev.tenerifegg.ui.content

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.mouredev.tenerifegg.LogoGeneratorViewModel
import com.mouredev.tenerifegg.conf.Env
import com.mouredev.tenerifegg.ui.components.CharacterTextField

/**
 * Created by MoureDev by Brais Moure on 9/7/23.
 * www.mouredev.com
 */
@Composable
fun APIKeyRow(
    context: Context,
    viewModel: LogoGeneratorViewModel,
    apiKey: String,
    onAPIKeyChanged: (String) -> Unit) {

    if (Env.OPENAI_API_KEY.isEmpty()) {

        var currentAPIKey = apiKey
        if (apiKey.isEmpty()) {
            currentAPIKey = viewModel.getCustomAPIKey(context)
            onAPIKeyChanged(currentAPIKey)
        }

        Row {
            CharacterTextField("OpenAI API Key", currentAPIKey, onValueChange = {

                viewModel.setCustomAPIKey(context, if (it.length == 51) it else "")

                onAPIKeyChanged(it)
            })
        }
    }
}