package com.codelytical.lingualytical

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

abstract class BaseActivity : AppCompatActivity(), LanguageSelector.LanguageChangeListener {

	private lateinit var languageSelector: LanguageSelector

	override fun attachBaseContext(newBase: Context) {
		languageSelector = LanguageSelector(newBase)
		val selectedLanguage = languageSelector.getSelectedLanguage()
		val context = LanguageContextWrapper.wrap(newBase, selectedLanguage)
		super.attachBaseContext(context)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		languageSelector.setLanguageChangeListener(this)
		reloadResources()
	}

	override fun onLanguageChanged(language: String) {
		reloadResources()
	}

	private fun reloadResources() {
		val delegate = ResourceReloadDelegate(this)
		try {
			delegate.reloadResources()
		} catch (ex: Exception) {
			Log.e("BaseActivity", "Failed to reload resources: ${ex.message}")
		}
	}
}
