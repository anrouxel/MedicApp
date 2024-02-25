package fr.medicapp.medicapp.mozilla

import android.content.Context
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSessionSettings
import org.mozilla.geckoview.GeckoView

object GeckoManager {
    private var INSTANCE: GeckoRuntime? = null

    fun getInstances(context: Context): GeckoRuntime {
        return INSTANCE ?: synchronized(this) {
            val instance = GeckoRuntime.create(context)
            INSTANCE = instance
            instance
        }
    }

    fun geckoSessionSettings(): GeckoSessionSettings {
        return GeckoSessionSettings().apply {
            this.useTrackingProtection = true
            this.userAgentMode = GeckoSessionSettings.USER_AGENT_MODE_MOBILE
            this.allowJavascript = true
        }
    }

    fun newGeckoSession(context: Context, url: String): GeckoSession {
        return GeckoSession(geckoSessionSettings()).apply {
            this.open(getInstances(context))
            this.loadUri(url)
        }
    }

    fun newGeckoView(context: Context, url: String): GeckoView {
        return GeckoView(context).apply {
            this.setSession(newGeckoSession(context, url))
        }
    }
}