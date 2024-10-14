package com.cyberbyte.mymovielibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyberbyte.mymovielibrary.databinding.ItemMovieBinding
import com.cyberbyte.mymovielibrary.models.Movie
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import org.w3c.dom.Text

class MovieAdapter(private var movies: List<Movie>) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    companion object{
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImage(imageView: ShapeableImageView, image: String?) {
            if (image == null) {
                return
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
                textView.text = "no title"
            } else {
                textView.text = title
            }
        }
        @JvmStatic
        @BindingAdapter("setDescription")
        fun setDescription(textView: TextView, description: String?) {
            if (description == null) {
                textView.text = "no description"
            } else {
                textView.text = description
            }
        }
        @JvmStatic
        @BindingAdapter("setRating")
        fun setRating(textView: MaterialTextView, rating: Float?) {
            if (rating == null) {
                textView.text = "no rating"
            } else {
                textView.text = "Rating: $rating"
            }
        }
        @JvmStatic
        @BindingAdapter("setReleaseDate")
        fun setReleaseDate(textView: TextView, year: Int?) {
            if (year == null) {
                textView.text = "no data"
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
