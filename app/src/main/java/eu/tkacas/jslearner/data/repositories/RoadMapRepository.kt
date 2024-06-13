package eu.tkacas.jslearner.data.repositories

import eu.tkacas.jslearner.domain.entity.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.remote.RoadmapDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoadmapRepository(private val dataSource: RoadmapDataSource) {
    suspend fun getRoadMapNodes(): List<RoadMapNodeState> = withContext(Dispatchers.IO) {
        dataSource.getRoadMapNodes()
    }
}