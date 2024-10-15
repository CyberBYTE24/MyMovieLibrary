package com.cyberbyte.mymovielibrary.ui.views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberbyte.mymovielibrary.ui.adapters.MovieAdapter
import com.cyberbyte.mymovielibrary.MovieListener
import com.cyberbyte.mymovielibrary.ui.viewmodels.MovieViewModel
import com.cyberbyte.mymovielibrary.databinding.FragmentMainBinding
import com.cyberbyte.mymovielibrary.models.Movie
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class MoviesFragment : Fragment(), DIAware, MovieListener {

    override val di: DI by closestDI()

    private lateinit var binding: FragmentMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by instance()

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

        movieAdapter.updateNotify()
    }

    override fun onFavouriteClicked(movie: Movie) {
        Toast.makeText(this.context, "Liked movie: ${movie.title}", Toast.LENGTH_SHORT).show()
        viewModel.onFavouriteClicked(movie)

        movieAdapter.updateNotify()
    }
}