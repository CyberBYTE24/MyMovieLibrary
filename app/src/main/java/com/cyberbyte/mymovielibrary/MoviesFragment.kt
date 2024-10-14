package com.cyberbyte.mymovielibrary
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberbyte.mymovielibrary.databinding.FragmentMainBinding
import com.cyberbyte.mymovielibrary.useCases.GetMovieByIdFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromDbUseCase
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MoviesFragment : Fragment() {

    //private var context: Context? = requireActivity()

    //override val di by closestDI(requireContext())
    //private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase by instance()
    //private val getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase by instance()
    //private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase by instance()
    //private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase by instance()

    private lateinit var binding: FragmentMainBinding
    private lateinit var movieAdapter: MovieAdapter
    //private val viewModel: MovieViewModel by viewModels { MovieViewModelFactory(getMoviesFromApiUseCase, getMovieByIdFromApiUseCase, saveMoviesToDbUseCase, getMoviesFromDbUseCase) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = FragmentMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        movieAdapter = MovieAdapter(emptyList())

        binding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMovie.adapter = movieAdapter

        //viewModel.movies.observe(this) { movies ->
            //movieAdapter.updateMovies(movies)
        //}

        //viewModel.loadMovies()

    }
}