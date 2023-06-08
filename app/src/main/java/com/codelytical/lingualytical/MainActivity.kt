package com.codelytical.lingualytical

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : BaseActivity() {

	private lateinit var languageSelector: LanguageSelector
	private lateinit var changeLanguageButton: Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		languageSelector = LanguageSelector(this)

		changeLanguageButton = findViewById(R.id.changeLanguageButton)

		changeLanguageButton.setOnClickListener {
			showLanguageSelectionDialog()
		}
	}

	private fun showLanguageSelectionDialog() {
		val languages = arrayOf("English", "French", "Spanish")

		val dialogBuilder = AlertDialog.Builder(this)
		dialogBuilder.setTitle("Select Language")
		dialogBuilder.setItems(languages) { _, which ->
			val selectedLanguage = languages[which]
			languageSelector.setSelectedLanguage(getLanguageCode(selectedLanguage))
			recreate()
		}
		dialogBuilder.show()
	}

	private fun getLanguageCode(language: String): String {
		return when (language) {
			"English" -> "en"
			"French" -> "fr"
			"Spanish" -> "es"
			else -> "en" // Default language
		}
	}
}