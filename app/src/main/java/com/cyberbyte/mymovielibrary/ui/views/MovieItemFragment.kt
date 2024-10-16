package com.cyberbyte.mymovielibrary.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cyberbyte.mymovielibrary.databinding.FragmentMovieItemBinding


class MovieItemFragment : Fragment() {

    private lateinit var binding: FragmentMovieItemBinding



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieItemBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.textViewName.text = arguments?.getString("title")
        binding.textViewDescription.text = arguments?.getString("description")
        binding.textViewRating.text = "Рейтинг на Кинопоиск: ${arguments?.getString("rating")}"
        binding.textViewDate.text = arguments?.getString("releaseDate")
        val image = arguments?.getString("posterUrl")
        if (image == null) {
            Glide.with(binding.imageViewMovieImage.context).clear(binding.imageViewMovieImage)
        }
        Glide.with(binding.imageViewMovieImage.context)
            .load(image)
            .into(binding.imageViewMovieImage)
        return root
    }
}