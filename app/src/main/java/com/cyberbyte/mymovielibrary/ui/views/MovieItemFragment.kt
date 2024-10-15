package com.cyberbyte.mymovielibrary.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberbyte.mymovielibrary.R
import com.cyberbyte.mymovielibrary.databinding.FragmentMainBinding
import com.cyberbyte.mymovielibrary.ui.adapters.MovieAdapter


class MovieItemFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding;



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val root: View = binding.root

        //binding.viewModel = viewModel
        //movieAdapter = MovieAdapter(emptyList(), this)
        binding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
        //binding.recyclerViewMovie.adapter = movieAdapter

        //viewModel.movies.observe(viewLifecycleOwner) { movies ->
        //    movieAdapter.updateMovies(movies)
        //}

        //viewModel.loadMovies()

        return root
    }
}