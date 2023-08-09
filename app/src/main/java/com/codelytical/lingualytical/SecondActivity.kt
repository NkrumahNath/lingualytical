package com.codelytical.lingualytical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codelytical.lingualytical.xtooltip.ClosePolicy
import com.codelytical.lingualytical.xtooltip.Tooltip

class SecondActivity : AppCompatActivity() {

	private var tooltip: Tooltip? = null
	private lateinit var button1: Button
	private lateinit var button2: Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)

		val metrics = resources.displayMetrics

		button1 = findViewById(R.id.button1)
		button2 = findViewById(R.id.button2)

		button1.setOnClickListener { button ->

			val text = "Hello there can we move"
			tooltip?.dismiss()

			Tooltip.Builder(this)
				.anchor(button, 0, 0, false)
				.closePolicy(ClosePolicy.TOUCH_ANYWHERE_CONSUME)
				.showDuration(0)
				.text(text)
				.create()
				.show(button, Tooltip.Gravity.TOP, false)
		}

	}

	override fun onDestroy() {
		super.onDestroy()
		tooltip?.dismiss()
	}

}