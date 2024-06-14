package eu.tkacas.jslearner

import android.app.Application
import eu.tkacas.jslearner.data.repository.RoadmapRepository
import eu.tkacas.jslearner.data.source.remote.RoadmapDataSource
import eu.tkacas.jslearner.di.AppModule
import eu.tkacas.jslearner.di.AppModuleImpl

class JSLearner : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}
