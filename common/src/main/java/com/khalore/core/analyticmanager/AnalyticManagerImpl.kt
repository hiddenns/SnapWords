package com.khalore.core.analyticmanager

import android.os.Bundle
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticManagerImpl @Inject constructor(
    private val analytic: FirebaseAnalytics
) : AnalyticManager {

    override fun logEvent(event: String) {
        analytic.logEvent(event,  bundleOf())
    }

    override fun logEvent(event: String, bundle: Bundle) {
        analytic.logEvent(event, bundle)
    }

}

