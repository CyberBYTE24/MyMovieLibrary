package com.cyberbyte.mymovielibrary.ui.views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyberbyte.mymovielibrary.ui.adapters.MovieAdapter
import com.cyberbyte.mymovielibrary.MovieListener
import com.cyberbyte.mymovielibrary.R
import com.cyberbyte.mymovielibrary.ui.viewmodels.MoviesViewModel
import com.cyberbyte.mymovielibrary.databinding.FragmentMoviesBinding
import com.cyberbyte.mymovielibrary.models.Movie
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class MoviesFragment : Fragment(), DIAware, MovieListener {

    override val di: DI by closestDI()

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MoviesViewModel by instance()

    //Pagination variables
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1
    private val PAGE_SIZE = 20

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.viewModel = viewModel
        movieAdapter = MovieAdapter(emptyList(), this)
        binding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMovie.adapter = movieAdapter

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.updateMovies(movies)
        }
        binding.searchFab.setOnClickListener{
            findNavController().navigate(R.id.nav_search)
        }

        binding.recyclerViewMovie.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int){
                super.onScrolled(recyclerView, dx, dy);

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                        loadMoreItems()
                    }
                }
            }
        })

        viewModel.initMoviesList()

        return root
    }

    private fun loadMoreItems(){
        isLoading = true
        currentPage++
        viewModel.loadMovies(currentPage)
        movieAdapter.notifyDataSetChanged()
        isLoading = false
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
        Toast.makeText(this.context, "${if(movie.favourite) "Disliked" else "Liked"} movie: ${movie.title}", Toast.LENGTH_SHORT).show()
        viewModel.onFavouriteClicked(movie)

        movieAdapter.updateNotify()
    }
}