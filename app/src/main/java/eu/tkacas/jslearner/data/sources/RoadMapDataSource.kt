package eu.tkacas.jslearner.data.sources

import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodeStatus

class RoadmapDataSource {
    val nodes = listOf(
        RoadMapNodeState(RoadMapNodeStatus.LOCKED, RoadMapNodePosition.FIRST, "Start Here"),
        RoadMapNodeState(RoadMapNodeStatus.IN_PROGRESS, RoadMapNodePosition.MIDDLE, "Module 1"),
        RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.MIDDLE, "Introduction"),
        RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.LAST, "Finish")
    )

    fun getRoadMapNodes(): List<RoadMapNodeState> {
        return nodes
    }
}
