package bob.pat.final2020

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import bob.pat.final2020.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Katelyn Patrick December 7th 2020

class MainActivity : AppCompatActivity() {

    // region properties

    private lateinit var binding: ActivityMainBinding

    private val baseUrl = "https://api.github.com/search/"
    private var searchString = ""

    private val minPage = 1
    private val maxPage = 100
    private val startPage = 30
    private val maxRepos = 1000
    private val maxFollowers = 10000

    //endregion


            // region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {

            fetchData()
        }

        binding.perPageNumberPicker.minValue = minPage
        binding.perPageNumberPicker.maxValue = maxPage
        binding.perPageNumberPicker.value = startPage

        binding.minReposEditText.filters  = arrayOf<InputFilter>(InputFilterMinMax(0, maxRepos))
        binding.minFollowersEditText.filters = arrayOf<InputFilter>(InputFilterMinMax(0, maxFollowers))

        binding.minReposEditText.setText("0")
        binding.minFollowersEditText.setText("0")


            // these methods all require 3 overrides
        binding.searchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.searchButton.isEnabled = binding.searchUser.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable) {
                binding.searchButton.isEnabled = s.isNotEmpty()
                binding.noResultsMessage.text = ""

            }
        })
    }

    //endregion


    // region fetch data
    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val restApi = retrofit.create(RestApi::class.java)

        if(TextUtils.isEmpty(binding.minFollowersEditText.text)){
            binding.minFollowersEditText.setText("0")
        }

        if(TextUtils.isEmpty(binding.minReposEditText.text)){
            binding.minReposEditText.setText("0")
        }

        val minNumberOfFollowers = binding.minFollowersEditText.text.toString().toInt()

        val minNumberOfRepos = binding.minReposEditText.text.toString().toInt()

        searchString = "${binding.searchUser.text} repos:>=$minNumberOfRepos followers:>=$minNumberOfFollowers"

        val call = restApi.getUserData(searchString, binding.perPageNumberPicker.value)

        call.enqueue(object: Callback<ResponseDataClass>{
            override fun onResponse(
                call: Call<ResponseDataClass>,
                response: Response<ResponseDataClass>
            ) {
                val responseBody = response.body()

                val users = responseBody?.items
                val length = users?.size ?: 0

                if(length > 0){
                    val intent = Intent(TheApp.context, ResultsActivity::class.java) // add intent
                    intent.putExtra(getString(R.string.user_data_key), users) // add data to Bundle
                    startActivity(intent) // start new activity and send the Bundle with the intent
                }   else {
                    binding.noResultsMessage.text = getString(R.string.no_results, binding.searchUser.text)
                    binding.searchButton.isEnabled = false
                }

            }

            override fun onFailure(call: Call<ResponseDataClass>, t: Throwable) {
                toast(t.message.toString())
            }

        })

    }

    //endregion

    // region options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(TheApp.context, AboutActivity::class.java) // add intent
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

//endregion