package fr.medicapp.medicapp.ui.components.mozilla

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoView

@Composable
fun Gecko() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            GeckoView(it).apply {
                this.releaseSession()
                this.setSession(
                    GeckoSession().apply {
                        this.open(GeckoRuntime.create(it))
                        this.loadUri("https://www.google.com")
                    }
                )
            }
        }
    )
}
