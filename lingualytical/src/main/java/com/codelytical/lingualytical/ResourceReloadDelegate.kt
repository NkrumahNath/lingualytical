package com.codelytical.lingualytical

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.Log
import java.util.*

class ResourceReloadDelegate(private val context: Context) {

	fun reloadResources() {
		val resources = context.resources
		val configuration = resources.configuration

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			// Update locale using setLocale
			val locale = Locale.getDefault()
			Locale.setDefault(locale)
			configuration.setLocale(locale)
		} else {
			// Update locale using locale field
			configuration.locale = Locale.getDefault()
		}

		try {
			resources.updateConfiguration(configuration, resources.displayMetrics)
		} catch (ex: Resources.NotFoundException) {
			Log.e("ResourceReloadDelegate", "Failed to update resources configuration: ${ex.message}")
		}
	}
}
