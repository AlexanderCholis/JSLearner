package eu.tkacas.jslearner.domain.model.roadmap

data class RoadMapNodeState(
    val status: RoadMapNodeStatus,
    val position: RoadMapNodePosition,
    val message: String? = null
)
