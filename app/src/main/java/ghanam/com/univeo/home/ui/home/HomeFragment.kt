package ghanam.com.univeo.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.NewsAdapter
import ghanam.com.univeo.adapters.UniversitiesAdapter
import ghanam.com.univeo.databinding.FragmentHomeBinding
import ghanam.com.univeo.dataclasses.NewsGeneral
import ghanam.com.univeo.dataclasses.UniversityGeneral



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var universitiesAdapter: UniversitiesAdapter
    private val universities = mutableListOf(
        UniversityGeneral("Cairo University","35","Cairo",2,"https://amayei.nyc3.digitaloceanspaces.com/2019/05/sisi-on-may-5-2019.png"),
        UniversityGeneral("Cairo University","35","Cairo",2,"https://amayei.nyc3.digitaloceanspaces.com/2019/05/sisi-on-may-5-2019.png"),
        UniversityGeneral("Cairo University","35","Cairo",2,"https://amayei.nyc3.digitaloceanspaces.com/2019/05/sisi-on-may-5-2019.png")
    )

    private lateinit var newsAdapter: NewsAdapter
    private val news = mutableListOf(
        NewsGeneral("presidint said that we will achieve goal","http://www.google.com","https://amayei.nyc3.digitaloceanspaces.com/2019/05/sisi-on-may-5-2019.png"),
        NewsGeneral("presidint said that we will achieve goal","http://www.google.com","https://amayei.nyc3.digitaloceanspaces.com/2019/05/sisi-on-may-5-2019.png"),
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val latestNewsButton= binding.latestNewsBtn
        val universitiesRecommendationButton= binding.uniRecommendBtn

        latestNewsButton.setOnClickListener {
            latestNewsButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.design_orange))
            universitiesRecommendationButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.inactive_color))
            binding.universitiesRecycler.visibility=View.GONE
            binding.newsRecycler.visibility=View.VISIBLE
//            binding.newsRecycler.zoom(200L,0L)
        }
        universitiesRecommendationButton.setOnClickListener {
            latestNewsButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.inactive_color))
            universitiesRecommendationButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.design_orange))
            binding.newsRecycler.visibility=View.GONE
            binding.universitiesRecycler.visibility=View.VISIBLE
//            binding.universitiesRecycler.zoom(200L,0L)

        }

        //university adapter maker
        universitiesAdapter = UniversitiesAdapter(universities)
        binding.universitiesRecycler.adapter=universitiesAdapter
        binding.universitiesRecycler.layoutManager = LinearLayoutManager(context)

        //news adapter maker
        newsAdapter = NewsAdapter(news)
        binding.newsRecycler.adapter=newsAdapter
        binding.newsRecycler.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}