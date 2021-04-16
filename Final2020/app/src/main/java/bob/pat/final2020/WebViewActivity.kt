package bob.pat.final2020

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bob.pat.final2020.databinding.ActivityWebViewBinding


//Katelyn Patrick December 7th 2020

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url =  intent.getStringExtra(getString(R.string.url_key))

        binding.webViewGitHub.settings.javaScriptEnabled = true
        binding.webViewGitHub.settings.loadWithOverviewMode = true
        binding.webViewGitHub.settings.useWideViewPort = true


        binding.webViewGitHub.loadUrl(url!!)
    }
}