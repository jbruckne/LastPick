package joebruckner.lastpick.network

import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import joebruckner.lastpick.enqueue
import joebruckner.lastpick.data.Request
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import java.util.*

class MovieManager(val bus: Bus, val language: String, val scope: Int) {
    val service = Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbService::class.java)
    val idStack = Stack<Int>()

    init {
        bus.register(this)
    }

    @Subscribe fun requestAvailable(request: Request) {
        if (idStack.isEmpty()) getNewIds(getRandomPage(0..scope))
        else getNewMovie(idStack.pop())
    }

    private fun getNewIds(page: Int) {
        service.getTopRatedMovies(page).enqueue { response, retrofit ->
            if (response.isSuccess && response.code() == 200) {
                response.body().getIds().forEach { idStack.push(it) }
                getNewMovie(idStack.pop())
            }
        }
    }

    private fun getNewMovie(id: Int) {
        service.getMovie(id).enqueue { response, retrofit ->
            if (response.isSuccess && response.code() == 200)
                bus.post(response.body())
        }
    }

    private fun getRandomPage(range: Range<Int>):
            Int = ((Math.random() * (range.end - range.start + 1)) + range.start).toInt()
}
