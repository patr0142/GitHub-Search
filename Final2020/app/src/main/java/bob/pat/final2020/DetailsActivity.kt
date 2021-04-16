package bob.pat.final2020

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import bob.pat.final2020.databinding.ActivityDetailsBinding
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException


//Katelyn Patrick December 7th 2020

class DetailsActivity : AppCompatActivity() {

    // region properties
    private lateinit var binding: ActivityDetailsBinding

   //endregion

    // region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = intent.getStringExtra(CustomViewHolderClass.titleKey)

        val data = intent.getSerializableExtra(CustomViewHolderClass.objectKey) as Users

        // set underline text on TextView control
        val text = data.html_url
        val content = SpannableString(text)
        content.setSpan(UnderlineSpan(), 0, text.length, 0)
        binding.htmlURLTextView.text = content

        binding.htmlURLTextView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(getString(R.string.url_key), data.html_url)
            this.startActivity(intent)
        }

        fetchJson(data.url)
    }

    //endregion

    //region fetch json data

    private fun fetchJson(url: String) {

        // We are using okhttp client here, not Retrofit2
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                toast("Request Failed!")
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()

                val gson = GsonBuilder().create()
                val result = gson.fromJson(body, UserDetails::class.java)

                runOnUiThread {
                    Picasso.get().load(result.avatar_url).into(binding.avatarImageView)

                    binding.nameTextView.text = getString(R.string.user_name, result?.name
                            ?: "unknown")
                    binding.locationTextView.text = getString(R.string.location, result?.location
                            ?:"unknown")
                    binding.companyTextView.text = getString(R.string.Company, result?.company
                            ?:"unknown")
                    binding.followersTextView.text = getString(R.string.Followers, result?.followers
                            ?:"unknown")
                    binding.gistsTextView.text = getString(R.string.public_gists, result?.public_gists
                            ?:"unknown")
                    binding.reposTextview.text = getString(R.string.public_repos, result?.public_repos
                            ?:"unknown")
                    binding.updatedTextView.text = getString(R.string.latest_update, result?.updated_at?.substring(0,9)
                            ?:"unknown")
                    binding.createdTextView.text = getString(R.string.account_created, result?.created_at?.substring(0,9)
                            ?:"unknown")

                }
            }
        })
    }
//endregion


    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}