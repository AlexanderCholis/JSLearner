package eu.tkacas.jslearner.data.repositories

import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.sources.RoadmapDataSource

class RoadmapRepository(private val dataSource: RoadmapDataSource) {
    fun getRoadMapNodes(): List<RoadMapNodeState> = dataSource.getRoadMapNodes()
}
