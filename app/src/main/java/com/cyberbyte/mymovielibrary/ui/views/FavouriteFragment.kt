package com.cyberbyte.mymovielibrary.ui.views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberbyte.mymovielibrary.ui.adapters.MovieAdapter
import com.cyberbyte.mymovielibrary.MovieListener
import com.cyberbyte.mymovielibrary.R
import com.cyberbyte.mymovielibrary.databinding.FragmentFavouriteBinding
import com.cyberbyte.mymovielibrary.models.Movie
import com.cyberbyte.mymovielibrary.models.Poster
import com.cyberbyte.mymovielibrary.models.Rating
import com.cyberbyte.mymovielibrary.ui.viewmodels.FavouriteViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class FavouriteFragment : Fragment(), DIAware, MovieListener {

    override val di: DI by closestDI()

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: FavouriteViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.viewModel = viewModel
        movieAdapter = MovieAdapter(emptyList(), this)
        binding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMovie.adapter = movieAdapter

        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.updateMovies(movies.map{
                Movie(
                    id = it.id,
                    title = it.title,
                    alternativeName = it.title,
                    description = it.description,
                    releaseDate = it.releaseDate,
                    rating = Rating(kinopoisk = it.rating, imdb = 0.0f, filmCritics = 0.0f, russianFilmCritics = 0.0f),
                    poster = Poster(url = it.posterUrl, previewUrl = ""),
                    favourite = it.favourite
                )
            })
        }
        viewModel.initMoviesList()

        return root
    }

    override fun onMovieClicked(movie: Movie) {
        //Toast.makeText(this.context, "Clicked on movie: ${movie.title}", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("title", movie.title)
        bundle.putString("description", movie.description)
        bundle.putString("rating", movie.rating?.kinopoisk.toString())
        bundle.putString("releaseDate", movie.releaseDate.toString())
        bundle.putString("posterUrl", movie.poster?.url)
        findNavController().navigate(R.id.nav_movie_item, bundle)
    }

    override fun onFavouriteClicked(movie: Movie) {
        Toast.makeText(this.context, "Movie was ${if(movie.favourite) "disliked" else "liked"}: ${movie.title}", Toast.LENGTH_SHORT).show()
        viewModel.onFavouriteClicked(movie)
        movieAdapter.updateNotify()
    }
}