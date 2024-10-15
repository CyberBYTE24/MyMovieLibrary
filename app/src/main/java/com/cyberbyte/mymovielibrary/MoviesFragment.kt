package com.cyberbyte.mymovielibrary
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberbyte.mymovielibrary.databinding.FragmentMainBinding
import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.useCases.GetMovieByIdFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromApiUseCase
import com.cyberbyte.mymovielibrary.useCases.GetMoviesFromDbUseCase
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class MoviesFragment() : Fragment(), DIAware, MovieListener {

    override val di: DI by closestDI()

    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase by instance()
    private val getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase by instance()
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase by instance()
    private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase by instance()

    private lateinit var binding: FragmentMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModels { MovieViewModelFactory(getMoviesFromApiUseCase, getMovieByIdFromApiUseCase, saveMoviesToDbUseCase, getMoviesFromDbUseCase) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.viewModel = viewModel
        movieAdapter = MovieAdapter(emptyList(), this)
        binding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMovie.adapter = movieAdapter

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.updateMovies(movies)
        }

        viewModel.loadMovies()

        return root
    }

    override fun onMovieClicked(movie: Movie) {
        Toast.makeText(this.context, "Clicked on movie: ${movie.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onFavouriteClicked(movie: Movie) {
        Toast.makeText(this.context, "Liked movie: ${movie.title}", Toast.LENGTH_SHORT).show()
    }
}