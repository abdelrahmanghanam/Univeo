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
import ghanam.com.univeo.singletons.CurrentUser
import ghanam.com.univeo.singletons.DBReader


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var universitiesAdapter: UniversitiesAdapter
    private lateinit var newsAdapter: NewsAdapter


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
        universitiesAdapter = UniversitiesAdapter(DBReader.universitiesHomeList)
        DBReader.readData(universitiesAdapter)
        binding.universitiesRecycler.adapter=universitiesAdapter
        binding.universitiesRecycler.layoutManager = LinearLayoutManager(context)

        //news adapter maker
        newsAdapter = NewsAdapter(DBReader.newsHomeList)
        binding.newsRecycler.adapter=newsAdapter
        binding.newsRecycler.layoutManager = LinearLayoutManager(context)
        binding.firstNameTextView.text= CurrentUser.firstName
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}