package com.example.aplikasi02

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasi02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	lateinit var binding: ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.progressBar
		setupwebView()

	}


	/**
	 * Sets up the WebView with a WebViewClient and a WebChromeClient.
	 * The WebViewClient is used to handle page navigation.
	 * The WebChromeClient is used to handle JavaScript dialogs, favicons, titles, and the progress.
	 */
	private fun setupwebView() {
		binding.webView.apply {
			webViewClient = object : WebViewClient() {
				override fun onPageFinished(view: WebView, url: String) {
					view.evaluateJavascript(
							"javascript:alert('welcome to ' + document.title);",
							null)
				}
			}
			// loading progress
			webChromeClient = object : WebChromeClient() {
				override fun onProgressChanged(view: WebView?, newProgress: Int) {
					// Update the progress of the ProgressBar.
					binding.progressBar.progress = newProgress
					// show the progress bar when page is loaded
					if (newProgress == 100 && binding.progressBar.visibility == View.GONE) {
						binding.progressBar.visibility = View.VISIBLE
					}
					// hide the progress bar when page is loaded
					if (newProgress == 100) {
						binding.progressBar.visibility = View.GONE
					}
				}
			}
			// Enable JavaScript in the WebView.
			settings.javaScriptEnabled = true
			loadUrl("https://www.google.com")
		}
	}
}