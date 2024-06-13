package eu.tkacas.jslearner.domain.entity.roadmap

data class RoadMapNodeState(
    val status: RoadMapNodeStatus,
    val position: RoadMapNodePosition,
    val message: String? = null
)
