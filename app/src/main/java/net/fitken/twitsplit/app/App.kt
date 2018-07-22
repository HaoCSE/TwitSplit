package net.fitken.twitsplit.app

import android.app.Activity
import android.app.Application
import android.app.Service
import com.base.injection.module.AppContextModule
import com.data.common.AppComponent
import com.data.common.DaggerAppComponent
import com.data.common.SharePreferenceManager

class App : Application() {
    private lateinit var component: AppComponent

    companion object {
        fun get(activity: Activity): App {
            return App::class.java.cast(activity.application)
        }

        fun get(service: Service): App {
            return App::class.java.cast(service.application)
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appContextModule(AppContextModule(this))
                .build()
    }

    fun getSharePreferences(): SharePreferenceManager {
        return component.getSharePreferences()
    }

    fun component(): AppComponent {
        return component
    }

}