package com.codelytical.lingualytical

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.Log
import java.util.*

class LanguageContextWrapper(baseContext: Context, language: String) :
	ContextWrapper(baseContext) {

	companion object {
		fun wrap(context: Context, language: String): ContextWrapper {
			val configuration = context.resources.configuration
			val locale = Locale(language)
			Locale.setDefault(locale)

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				configuration.setLocale(locale)
			} else {
				configuration.locale = locale
			}

			try {
				return ContextWrapper(context.createConfigurationContext(configuration))
			} catch (ex: Resources.NotFoundException) {
				Log.e("LanguageContextWrapper", "Failed to wrap context with new configuration: ${ex.message}")
			}

			return ContextWrapper(context)
		}
	}
}
