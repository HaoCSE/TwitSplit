package net.fitken.twitsplit.home

import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun adapter(): TweetAdapter {
        return TweetAdapter()
    }
}

