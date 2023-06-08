package com.codelytical.lingualytical

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class LanguageSelector(private val context: Context) {

	companion object {
		private const val PREFERENCE_KEY = "selected_language"
		private const val DEFAULT_LANGUAGE = "en"
	}

	interface LanguageChangeListener {
		fun onLanguageChanged(language: String)
	}

	private var languageChangeListener: LanguageChangeListener? = null

	fun setLanguageChangeListener(listener: LanguageChangeListener) {
		languageChangeListener = listener
	}

	fun getSelectedLanguage(): String {
		val sharedPreferences: SharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(context)
		return sharedPreferences.getString(PREFERENCE_KEY, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
	}

	fun setSelectedLanguage(language: String) {
		val sharedPreferences: SharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(context)
		val editor = sharedPreferences.edit()
		editor.putString(PREFERENCE_KEY, language)
		editor.apply()

		languageChangeListener?.onLanguageChanged(language)
	}
}

