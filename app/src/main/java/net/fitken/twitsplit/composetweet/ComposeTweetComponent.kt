package net.fitken.twitsplit.composetweet

import com.base.injection.scope.OutOfApplicationScope
import com.data.common.AppComponent
import dagger.Component

@Component(modules = [(ComposeTweetModule::class)], dependencies = [(AppComponent::class)])
@OutOfApplicationScope
interface ComposeTweetComponent {
    fun inject(i: ComposeTweetActivity)
}
