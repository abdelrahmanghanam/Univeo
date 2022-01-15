package ghanam.com.univeo.university

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeBaseActivity
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.FacultyAdapter
import ghanam.com.univeo.databinding.ActivityUniversityBinding
import ghanam.com.univeo.home.HomePageActivity
import ghanam.com.univeo.singletons.DBReader
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import ghanam.com.univeo.extensions.GeneralExt.toast


class UniversityActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityUniversityBinding
    private lateinit var facultiesAdapter: FacultyAdapter
    private lateinit var faculties:MutableList<HashMap<String, Any>>
    private val apiKey="AIzaSyACe5Tb8eubz3pquUNdt4kaBU0u490rO4g"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_university)

        // Inflate the layout for this fragment
        val currentUni= DBReader.currentUni
        binding.backButton.setOnClickListener {
            DBReader.currentUni=null
            val intent = Intent(this,  HomePageActivity::class.java)
            finish()
            startActivity(intent)
        }
        val longtude=currentUni!!.longtidue
        val latitude=currentUni.latitude
        val mapUrl="https://www.google.com/maps?q=$longtude,$latitude"
        binding.map.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            startActivity(browserIntent)
        }

        binding.youtubeVideo.initialize(apiKey, object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo(currentUni.videoID)
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                toast("video failed to load")
            }
        })


        faculties= currentUni.faculties.toMutableList()
        binding.cityTv.text=currentUni.city
        binding.briefText.text=currentUni.brief
        binding.rankTv.text=currentUni.rank



        //news adapter maker
        facultiesAdapter = FacultyAdapter(faculties)
        binding.facultiesRecycler.adapter=facultiesAdapter
        binding.facultiesRecycler.layoutManager = LinearLayoutManager(applicationContext)
    }



}