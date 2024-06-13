package eu.tkacas.jslearner.data.repository

import eu.tkacas.jslearner.domain.entity.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.source.remote.RoadmapDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoadmapRepository(private val dataSource: RoadmapDataSource) {
    suspend fun getRoadMapNodes(): List<RoadMapNodeState> = withContext(Dispatchers.IO) {
        dataSource.getRoadMapNodes()
    }
}