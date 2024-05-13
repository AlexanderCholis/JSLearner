package eu.tkacas.jslearner.models.roadmap

data class RoadMapNodeState(
    val status: RoadMapNodeStatus,
    val position: RoadMapNodePosition,
    val message: String? = null
)
