package com.cyberbyte.mymovielibrary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyberbyte.mymovielibrary.MovieListener
import com.cyberbyte.mymovielibrary.R
import com.cyberbyte.mymovielibrary.databinding.ItemMovieBinding
import com.cyberbyte.mymovielibrary.models.Movie
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class MovieAdapter(private var movies: List<Movie>, private var movieListener: MovieListener) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
    MovieDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.bind(movieListener)
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    fun updateNotify(){
        notifyDataSetChanged()
    }

    companion object{
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImage(imageView: ShapeableImageView, image: String?) {
            if (image == null) {
                Glide.with(imageView.context).clear(imageView)
            }
            Glide.with(imageView.context)
                .load(image)
                .into(imageView)

        }
        @JvmStatic
        @BindingAdapter("setFavouriteCondition")
        fun setFavouriteCondition(imageView: ShapeableImageView, isFavourite: Boolean) {
            if (isFavourite) {
                imageView.setImageResource(R.drawable.ic_favorite)
            } else {
                imageView.setImageResource(R.drawable.ic_favorite_border)
            }
        }
        @JvmStatic
        @BindingAdapter("setTitle")
        fun setTitle(textView: TextView, title: String?) {
            if (title == null) {
                textView.text = "Нет заголовка"
            } else {
                textView.text = title
            }
        }
        @JvmStatic
        @BindingAdapter("setDescription")
        fun setDescription(textView: TextView, description: String?) {
            if (description == null) {
                textView.text = "Нет описания"
            } else {
                textView.text = description
            }
        }
        @JvmStatic
        @BindingAdapter("setRating")
        fun setRating(textView: MaterialTextView, rating: Float?) {
            if (rating == null) {
                textView.text = "Нет рейтинга"
            } else {
                textView.text = "Рейтинг на Кинопоиск: $rating"
            }
        }
        @JvmStatic
        @BindingAdapter("setReleaseDate")
        fun setReleaseDate(textView: TextView, year: Int?) {
            if (year == null) {
                textView.text = "Год не указан"
            } else {
                textView.text = "$year"
            }
        }
    }


    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings() // Ensures that the data is bound immediately
        }
        fun bind(listener: MovieListener){
            binding.listener = listener
            binding.executePendingBindings()
        }
    }
    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id // Assuming Movie has an id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
