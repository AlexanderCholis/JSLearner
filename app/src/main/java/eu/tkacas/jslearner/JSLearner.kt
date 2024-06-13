package eu.tkacas.jslearner

import android.app.Application
import eu.tkacas.jslearner.data.repositories.RoadmapRepository
import eu.tkacas.jslearner.data.remote.RoadmapDataSource

class JSLearner : Application() {
    lateinit var roadmapRepository: RoadmapRepository

    override fun onCreate() {
        super.onCreate()
        val roadmapDataSource = RoadmapDataSource()
        roadmapRepository = RoadmapRepository(roadmapDataSource)
    }
}
