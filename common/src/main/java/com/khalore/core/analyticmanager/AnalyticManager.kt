package com.khalore.core.analyticmanager

import android.os.Bundle

interface AnalyticManager {

    fun logEvent(event: String)

    fun logEvent(event: String, bundle: Bundle)

}
