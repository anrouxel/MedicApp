package fr.medicapp.medicapp.ui.components.mozilla

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import fr.medicapp.medicapp.mozilla.GeckoManager

@Composable
fun Gecko(
    url: String
) {
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            GeckoManager.newGeckoView(context, url)
        }
    )
}
