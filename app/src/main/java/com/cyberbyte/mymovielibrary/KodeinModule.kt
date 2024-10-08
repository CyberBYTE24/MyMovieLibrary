package com.cyberbyte.mymovielibrary

import android.app.Application
import androidx.room.Room
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = DI.Module("appModule") {
    // Database
    bind<MovieDatabase>() with singleton {
        Room.databaseBuilder(instance<Application>(), MovieDatabase::class.java, "movies.db")
            .build()
    }

    // Retrofit
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/v1.4/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // MovieApi
    bind<MovieApiService>() with singleton {
        instance<Retrofit>().create(MovieApiService::class.java)
    }

    bind<MovieRemoteDataSource>() with singleton {
        MovieRemoteDataSource(instance())
    }
    bind<MovieDao>() with singleton {
        instance<MovieDatabase>().movieDao()
    }

    bind<MovieLocalDataSource>() with singleton {
        MovieLocalDataSource(instance())
    }

    // Repository
    bind<MovieRepository>() with singleton {
        MovieRepositoryImpl(instance(), instance())
    }



    // Use Cases
    bind<GetMoviesFromApiUseCase>() with singleton { GetMoviesFromApiUseCase(instance()) }
    bind<GetMovieByIdFromApiUseCase>() with singleton { GetMovieByIdFromApiUseCase(instance()) }
    bind<GetMoviesFromDbUseCase>() with singleton { GetMoviesFromDbUseCase(instance()) }
    bind<SaveMoviesToDbUseCase>() with singleton { SaveMoviesToDbUseCase(instance()) }
}