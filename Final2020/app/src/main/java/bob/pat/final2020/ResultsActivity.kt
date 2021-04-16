package bob.pat.final2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import bob.pat.final2020.databinding.ActivityResultsBinding


//Katelyn Patrick December 7th 2020

class ResultsActivity : AppCompatActivity() {

    // region properties
    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("UNCHECKED_CAST")
        val data = intent.getSerializableExtra(getString(R.string.user_data_key)) as ArrayList<Users>

        supportActionBar?.title = "${data.size} Results"

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMain.adapter = MainAdapter(data)

    }
}
//endregion