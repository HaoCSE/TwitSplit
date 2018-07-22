package net.fitken.twitsplit.home

import com.base.injection.scope.OutOfApplicationScope
import com.data.common.AppComponent
import dagger.Component

@Component(modules = [(HomeModule::class)], dependencies = [(AppComponent::class)])
@OutOfApplicationScope
interface HomeComponent {
    fun inject(i: HomeActivity)
}
