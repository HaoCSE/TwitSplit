package net.fitken.twitsplit.home

import com.base.imageloader.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun adapter(imageLoader: ImageLoader): TweetAdapter {
        return TweetAdapter(imageLoader)
    }
}

