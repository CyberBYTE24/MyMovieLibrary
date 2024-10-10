package com.cyberbyte.mymovielibrary

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cyberbyte.mymovielibrary.databinding.ActivityMainBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di by closestDI()
    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase by instance()
    private val getMoviesFromDbUseCase: GetMoviesFromDbUseCase by instance()
    private val saveMoviesToDbUseCase: SaveMoviesToDbUseCase by instance()

    lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}