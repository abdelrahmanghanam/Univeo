package ghanam.com.univeo.university

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ghanam.com.univeo.databinding.FragmentUniversityBinding
import ghanam.com.univeo.home.HomePageActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ghanam.com.univeo.adapters.FacultyAdapter
import ghanam.com.univeo.dataclasses.FacultyGeneral
import ghanam.com.univeo.singletons.DBReader
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

import androidx.annotation.NonNull

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import android.R

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView








class UniversityFragment : Fragment() {

    lateinit var binding: FragmentUniversityBinding
    private lateinit var facultiesAdapter: FacultyAdapter
    private lateinit var faculties:MutableList<HashMap<String, Any>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val currentUni=DBReader.currentUni
        binding = FragmentUniversityBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {
            DBReader.currentUni=null
            val intent = Intent(getActivity(),  HomePageActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }
        val longtude=currentUni!!.longtidue
        val latitude=currentUni!!.latitude
        val mapUrl="https://www.google.com/maps?q=$longtude,$latitude"
        binding.map.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            startActivity(browserIntent)
        }

        val youTubePlayerView: YouTubePlayerView = binding.youtubeVideo
        lifecycle.addObserver(youTubePlayerView);
        binding.universityName.text=currentUni.name
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            val videoId = currentUni.videoID
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.play()
            }
        })




        faculties= currentUni.faculties.toMutableList()
        binding.cityTv.text=currentUni.city
        binding.briefText.text=currentUni.brief
        binding.rankTv.text=currentUni.rank



        //news adapter maker
        facultiesAdapter = FacultyAdapter(faculties)
        binding.facultiesRecycler.adapter=facultiesAdapter
        binding.facultiesRecycler.layoutManager = LinearLayoutManager(context)




        return binding.root
    }

}